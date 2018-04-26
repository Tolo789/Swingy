package cmutti.controller;

import cmutti.model.ACharacter;
import cmutti.model.AMapElement;
import cmutti.model.heroes.AHero;
import cmutti.model.heroes.AHero;
import cmutti.model.monsters.AMonster;
import cmutti.model.monsters.MonsterFactory;
import cmutti.view.gui.FrameGUI;
import java.util.ArrayList;
import java.util.Iterator;


public class MainGame {
	private enum GameState {
		Loading,
		WaitingDirectionChoice,
		WaitingFightChoice
	}

	public static String[] directions = new String[]{"North", "South", "West", "East"};

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

	MainGame(AHero hero) {
		this.hero = hero;

		newLevel();
	}

	private void newLevel() {
		// Create map
		mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
		mapXp = hero.getLevel() * 1000;
		swingy.displayMessage("You entered in a new zone! (Size: " + mapSize + ")");
		swingy.displayMessage(""); // add empty line to better see start of level

		// Add hero to center
		hero.setPosition(mapSize / 2, mapSize / 2);
		// Get monster list
		monsterList = MonsterFactory.generateMonsterList(hero.getLevel(), mapSize);

		// TODO: Add landscape
		// TODO: be sure that landscape doesnt blocks path to exit

		updateUI();
	}

	private void updateUI() {
		// Recreate each time since old elem position must be overridden
		mapElems = new AMapElement[mapSize][mapSize];

		// Hero
		mapElems[hero.getPosY()][hero.getPosX()] = hero;

		// Monsters
		for (AMonster monster : monsterList) {
			mapElems[monster.getPosY()][monster.getPosX()] = monster;
		}

		// TODO: Landscape

		swingy.updateMap(mapElems);
		swingy.updateHero();
		swingy.showDirectionChoices();
		gameState = GameState.WaitingDirectionChoice;
	}

	// Call to move in a direction
	public void directionChosen(String direction) {
		if (gameState != GameState.WaitingDirectionChoice) // Prevent spam click of button
			return;
		gameState = GameState.Loading;

		if (direction == "North") {
			if (hero.getPosY() == 0) {
				onLevelFinished();
				return;
			}
			tryMovePlayer(direction);
		}
		else if (direction == "South") {
			if (hero.getPosY() == mapElems.length - 1) {
				onLevelFinished();
				return;
			}
			tryMovePlayer(direction);
		}
		else if (direction == "West") {
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
			hero.setPosition(tmpY, tmpX);
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
				monsterList.remove((AMonster)elem);
			}
		}
		else {
			if (swingy.rand.nextInt(2) == 0) { // 50% of chanches not menaging to flee
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
		swingy.displayMessage("Fight against " + monster.getName() + " lvl. " + monster.getLevel() + " started ! (" + monster.getStatResume() + ")");
		boolean fightEnded = false;

		ACharacter attacker = (heroStarts) ? hero : monster;
		ACharacter defender = (heroStarts) ? monster : hero;
		ACharacter tmpChar;
		int damage;
		int agiDelta = Math.abs(hero.getAgility() - monster.getAgility());
		boolean agiTrigger;
		while (!fightEnded) {
			if (agiDelta != 0 && swingy.rand.nextInt(100) < agiDelta)
				agiTrigger = true;
			else
				agiTrigger = false;

			if (agiTrigger && attacker.getAgility() < defender.getAgility()) {
				swingy.displayMessage(defender.getName() + " evaded " + attacker.getName() + "'s attack..!");
			}
			else {
				// Formula inspired by original pokemon damage formula
				damage = (int) ((2f * attacker.getLevel() / 5 + 2) * attacker.getAttack() / defender.getDefense() + 2);
				if (agiTrigger) {
					damage *= 2;
					System.out.print(attacker.getName() + " critically strikes..! ");
				}
				else {
					System.out.print(attacker.getName() + " attacks.. ");
				}

				defender.getDamage(damage);
				if (defender.getHp() == 0) {
					swingy.displayMessage(defender.getName() + " is KO !");
					fightEnded = true;
				}
				else {
					swingy.displayMessage(defender.getName() + "'s hp down to " + defender.getHp());
				}
			}

			tmpChar = attacker;
			attacker = defender;
			defender = tmpChar;
		}

		if (hero.getHp() == 0) {
			onHeroDeath();
			return false;
		}

		// TODO: monster drop artefact
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
			else
				updateMapWithCharacterMove((ACharacter)monster);
		}

		// Remove dead monsters
		if (toRemove.size() > 0) {
			monsterList.removeAll(toRemove);
		}
		updateUI();
	}

	private void onHeroDeath() {
		// TODO: manage lose
		swingy.displayMessage(""); // add empty line to better see death
		swingy.displayMessage("You are dead !!");
	}
}
