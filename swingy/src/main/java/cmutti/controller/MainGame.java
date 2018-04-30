package cmutti.controller;

import cmutti.model.ACharacter;
import cmutti.model.AMapElement;
import cmutti.model.artifacts.AArtifact;
import cmutti.model.artifacts.ArtifactBuilder;
import cmutti.model.artifacts.armors.AArmor;
import cmutti.model.artifacts.helms.AHelm;
import cmutti.model.artifacts.weapons.AWeapon;
import cmutti.model.heroes.AHero;
import cmutti.model.landscape.LandscapeFactory;
import cmutti.model.landscape.Stone;
import cmutti.model.monsters.AMonster;
import cmutti.model.monsters.MonsterFactory;
import cmutti.view.gui.FrameGUI;
import java.util.ArrayList;
import java.util.Iterator;

public class MainGame {
	private enum GameState {
		Loading,
		WaitingDirectionChoice,
		WaitingFightChoice,
		WaitingArtifactChoice
	}

	public final static String NORTH = "North";
	public final static String SOUTH = "South";
	public final static String WEST = "West";
	public final static String EAST = "East";
	public static String[] directions = new String[]{NORTH, SOUTH, WEST, EAST};

	// Call main controller with shorter code
	private Swingy swingy = Swingy.getInstance();

	// Game vars
	AHero hero;
	AMapElement elem;	// tmp MapElem, can be monster or landscape
	int tmpX;	// used when fleeing monster
	int tmpY;	// used when fleeing monster
	ArrayList<AMonster> monsterList = new ArrayList<AMonster>();	// all the monsters of the level
	AMapElement[][] mapElems; // easy way to find elem instead of iterating over list
	int mapSize;
	int mapXp;	// calculated on creation of map
	GameState gameState = GameState.Loading; // used to know if accepting user input
	boolean didFight = false;	// tells if a fight happened this turn
	AArtifact artifact = null;

	MainGame(AHero hero) {
		this.hero = hero;

		newLevel();
	}

	private void newLevel() {
		// Create map
		mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
		mapElems = new AMapElement[mapSize][mapSize];
		mapXp = hero.getLevel() * 1000;
		if (hero.getPassive() == AHero.HeroPassive.EfficientTraveller)
			mapXp = (int)(1.5 * mapXp);
		swingy.displayMessage("You entered in a new zone! (Size: " + mapSize + ")");
		swingy.displayMessage(""); // add empty line to better see start of level

		// Add hero to center
		hero.setPosition(mapSize / 2, mapSize / 2);
		mapElems[hero.getPosY()][hero.getPosX()] = hero;

		// Get monster list
		monsterList = MonsterFactory.generateMonsterList(hero.getLevel(), mapSize);
		for (AMonster monster : monsterList) {
			mapElems[monster.getPosY()][monster.getPosX()] = monster;
		}

		// Add landscape after first level
		if (hero.getLevel() > 1)
			mapElems = LandscapeFactory.addLandscape(mapElems);

		// TODO: be sure that landscape doesnt blocks path to exit

		endTurn();
	}

	private void endTurn() {
		if (hero.getPassive() == AHero.HeroPassive.AutoHealing && didFight) {
			int heal = hero.getMaxHp() / 8;
			if (hero.heal(heal)) {
				swingy.displayMessage("Healing yourself for " + heal + " hp");
				swingy.displayMessage(""); // add empty line to better see end of turn
			}
		}
		didFight = false;

		swingy.updateMap(mapElems);
		swingy.updateHero();
		swingy.showDirectionChoices();
		gameState = GameState.WaitingDirectionChoice;
	}

	// Call to move in a direction
	public void directionChosen(String direction) {
		System.out.println("Received direction " + direction);
		if (gameState != GameState.WaitingDirectionChoice) // Prevent spam click of button
			return;
		System.out.println("Executing..");
		swingy.stopDirectionChoices();
		gameState = GameState.Loading;

		if (direction.equals(NORTH)) {
			if (hero.getPosY() == 0) {
				onLevelFinished();
				return;
			}
			tryMovePlayer(direction);
		}
		else if (direction.equals(SOUTH)) {
			if (hero.getPosY() == mapElems.length - 1) {
				onLevelFinished();
				return;
			}
			tryMovePlayer(direction);
		}
		else if (direction.equals(WEST)) {
			if (hero.getPosX() == 0) {
				onLevelFinished();
				return;
			}
			tryMovePlayer(direction);
		}
		else { // East
			if (hero.getPosX() == mapElems.length - 1) {
				onLevelFinished();
				return;
			}
			tryMovePlayer(direction);
		}
	}

	private void tryMovePlayer(String direction) {
		tmpX = hero.getPosX();
		tmpY = hero.getPosY();
		hero.moveTowards(direction);

		// Check if moving in given direction trigger some encounter
		elem = mapElems[hero.getPosY()][hero.getPosX()];
		if (elem != null) {
			if (elem instanceof AMonster) {
				// Ask player if fight or flee, then wait for respone
				swingy.displayMessage("You encountered a " + elem.getName() + " lvl." + ((AMonster)elem).getLevel());
				swingy.showFightChoices();
				gameState = GameState.WaitingDirectionChoice;
				return;
			}

			// set hero back to prev pos if he is moving towards landscape elem
			if (hero.getPassive() == AHero.HeroPassive.StonesBreaker && elem instanceof Stone) {
				swingy.displayMessage("There is a " + elem.getName() + " in front of you.. You broke it with your powerful punch !");
			}
			else {
				swingy.displayMessage("There is a " + elem.getName() + " in front of you, cannot go " + direction + "..\n");
				hero.setPosition(tmpY, tmpX);
			}
		}

		updateMapWithHeroMove();
	}

	public void fightDecision(boolean doFight) {
		if (gameState != GameState.WaitingDirectionChoice)	// Prevent spam click of button
			return;
		gameState = GameState.Loading;

		if (doFight) {
			if (!simulateFight((AMonster)elem, true)) {
				onHeroDeath();
				return;
			}
			else {
				// Artiafact drop, can only happen if hero has willingly started a fight
				artifact = ArtifactBuilder.getDroppedArtifact((AMonster)elem);
				monsterList.remove((AMonster)elem);

				if (artifact != null) {
					// Same line for equipping any artifact
					swingy.displayMessage("Found a " + artifact.getPresentation());

					// Equip logic
					if (artifact instanceof AArmor) {
						if (hero.getArmor() == null) {
							hero.equipArmor((AArmor)artifact);
						}
						else {
							swingy.showArtifactChoices("armor");
							gameState = GameState.WaitingArtifactChoice;
							return;
						}
					}
					else if (artifact instanceof AHelm) {
						if (hero.getHelm() == null) {
							hero.equipHelm((AHelm)artifact);
						}
						else {
							swingy.showArtifactChoices("helm");
							gameState = GameState.WaitingArtifactChoice;
							return;
						}
					}
					else if (artifact instanceof AWeapon) {
						if (hero.getWeapon() == null) {
							hero.equipWeapon((AWeapon)artifact);
						}
						else {
							swingy.showArtifactChoices("weapon");
							gameState = GameState.WaitingArtifactChoice;
							return;
						}
					}

					// Same line for equipping any artifact
					swingy.displayMessage(artifact.getName() + " equipped !\n");
				}
			}
		}
		else {
			int escapeChance = (hero.getPassive() == AHero.HeroPassive.ArtfulDodger) ? 4 : 2;

			if (swingy.rand.nextInt(escapeChance) == 0) { // 25%/50% of chanches not menaging to flee (depending on escapeChance)
				swingy.displayMessage("You couldn't escape the fight..!");
				if (!simulateFight((AMonster)elem, false)) {
					onHeroDeath();
					return;
				}
				else {
					monsterList.remove((AMonster)elem);
				}
			}
			else {
				swingy.displayMessage("You managed to flee..!\n");
				// If hero manages to flee then he goes back to prev location
				hero.setPosition(tmpY, tmpX);
			}
		}

		updateMapWithHeroMove();
	}

	private void updateMapWithHeroMove() {
		updateMapWithCharacterMove((ACharacter)hero);

		// After hero moves it's time for all monster to move
		monstersUpdate();
	}

	public void artifactDecision(boolean doChange) {
		if (gameState != GameState.WaitingArtifactChoice)	// Prevent spam click of button
			return;
		gameState = GameState.Loading;

		if (doChange) {
			if (artifact instanceof AArmor)
				hero.equipArmor((AArmor)artifact);
			else if (artifact instanceof AHelm)
				hero.equipHelm((AHelm)artifact);
			else if (artifact instanceof AWeapon)
				hero.equipWeapon((AWeapon)artifact);

			swingy.displayMessage(artifact.getName() + " equipped !");
		}
		swingy.displayMessage("");

		updateMapWithHeroMove();
	}

	private void updateMapWithCharacterMove(ACharacter character) {
		mapElems[tmpY][tmpX] = null;
		mapElems[character.getPosY()][character.getPosX()] = character;
	}

	private void onLevelFinished() {
		swingy.displayMessage("Level Finished !   You gained " + mapXp + " xp");
		if (hero.gainXp(mapXp)) {
			swingy.displayMessage("Level-Up ! " + hero.getGrowthString());
		}
		swingy.displayMessage(""); // add empty line to better see end of level

		newLevel();
	}

	private boolean simulateFight(AMonster monster, boolean heroStarts) {
		didFight = true;
		swingy.displayMessage("Fight against " + monster.getName() + " lvl. " + monster.getLevel() + " started ! (" + monster.getStatResume() + ")");
		boolean fightEnded = false;

		ACharacter attacker = (heroStarts) ? hero : monster;
		ACharacter defender = (heroStarts) ? monster : hero;
		ACharacter tmpChar;
		int damage;
		int agiDelta = Math.abs(hero.getAgility() - monster.getAgility());
		boolean agiTrigger;
		String str;
		while (!fightEnded) {
			if (agiDelta != 0 && swingy.rand.nextInt(100) < agiDelta)
				agiTrigger = true;
			else
				agiTrigger = false;

			if (agiTrigger && attacker.getAgility() < defender.getAgility()) {
				swingy.displayMessage(defender.getName() + " evaded " + attacker.getName() + "'s attack..!");
			}
			else {
				str = "";
				// Formula inspired by original pokemon damage formula
				damage = (int) ((2f * attacker.getLevel() / 5 + 2) * attacker.getAttack() / defender.getDefense() + 2);
				if (agiTrigger) {
					damage *= 2;
					str = attacker.getName() + " critically strikes..! ";
				}
				else {
					str = attacker.getName() + " attacks.. ";
				}

				defender.getDamage(damage);
				if (defender.getHp() == 0) {
					swingy.displayMessage(str + defender.getName() + " is KO !");
					fightEnded = true;
				}
				else {
					swingy.displayMessage(str + defender.getName() + "'s hp down to " + defender.getHp());
				}
			}

			tmpChar = attacker;
			attacker = defender;
			defender = tmpChar;
		}

		if (hero.getHp() == 0) {
			return false;
		}

		swingy.displayMessage("You gained " + monster.getXp() + " xp");
		if (hero.gainXp(monster.getXp())) {
			swingy.displayMessage("Level-Up ! " + hero.getGrowthString());
		}
		swingy.displayMessage(""); // add empty line to better see end of fight
		return true;
	}

	private void monstersUpdate() {
		ArrayList<AMonster> toRemove = new ArrayList<AMonster>();
		for (AMonster monster : monsterList) {
			tmpX = monster.getPosX();
			tmpY = monster.getPosY();
			monster.doAction(hero, mapElems);

			if (monster.getPosX() < 0 || monster.getPosY() < 0 || monster.getPosX() >= mapElems.length || monster.getPosY() >= mapElems.length) { // Fleeing monster
				mapElems[tmpY][tmpX] = null;
				toRemove.add(monster);
			}
			else if (monster.getPosX() == hero.getPosX() && monster.getPosY() == hero.getPosY()) {
				boolean dodged = false;
				if (hero.getPassive() == AHero.HeroPassive.ArtfulDodger && swingy.rand.nextInt(2) == 0)
					dodged = true;

				if (!dodged) {
					swingy.displayMessage("A " + monster.getName() + " lvl." + monster.getLevel() + " suddenly attacks you..!");
					if (simulateFight(monster, false)) {
						mapElems[tmpY][tmpX] = null;
						toRemove.add(monster);
					}
					else {
						onHeroDeath();
						return;
					}
				}
				else {
					swingy.displayMessage("You dodged an attack from a " + monster.getName() + " lvl." + monster.getLevel() + "..!\n");
					monster.setPosition(tmpY, tmpX);
				}
			}
			else
				updateMapWithCharacterMove((ACharacter)monster);
		}

		// Remove dead monsters
		if (toRemove.size() > 0) {
			monsterList.removeAll(toRemove);
		}
		endTurn();
	}

	private void onHeroDeath() {
		// TODO: manage lose
		swingy.displayMessage(""); // add empty line to better see death
		swingy.displayMessage("You are dead !!");
	}
}
