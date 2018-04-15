package cmutti.controller;

import cmutti.model.ACharacter;
import cmutti.model.AMapElement;
import cmutti.model.heroes.AHero;
import cmutti.model.monsters.AMonster;
import cmutti.model.monsters.MonsterFactory;
import cmutti.view.gui.FrameGUI;

import cmutti.model.heroes.AHero;
import cmutti.model.monsters.MonsterFactory;

public class MainGame {
	private enum GameState {
		Loading,
		WaitingMove,
		WaitingCombat
	}

	public static String[] directions = new String[]{"North", "South", "West", "East"};
	// Game vars
	AHero hero;
	int tmpX;	// used when fleeing monster
	int tmpY;	// used when fleeing monster
	AMonster[] monsterList;	// all the monsters of the level
	int mapSize;
	AMapElement[][] mapElems; // easy way to find elem instead of iterating over list
	GameState gameState = GameState.Loading; // used to know if accepting user input

	// UIs
	FrameGUI guiFrame;

	MainGame(AHero hero, FrameGUI guiFrame) {
		this.hero = hero;
		this.guiFrame = guiFrame;

		guiFrame.StartMainPanel(hero);

		newLevel();
	}

	private void newLevel() {
		// Create map
		mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
		System.out.println("MapSize: " + mapSize);

		// Add hero to center
		hero.setPosition(mapSize / 2, mapSize / 2);
		// Get monster list
		monsterList = MonsterFactory.generateMonsterList(hero.getLevel(), mapSize);

		// TODO: Add landscape

		updateMapElems();
		gameState = GameState.WaitingMove;
	}

	private void updateMapElems() {
		// Recreate each time since old elem position must be overridden
		mapElems = new AMapElement[mapSize][mapSize];

		// Hero
		mapElems[hero.getPosY()][hero.getPosX()] = hero;

		// Monsters
		for (AMonster monster : monsterList) {
			mapElems[monster.getPosY()][monster.getPosX()] = monster;
		}

		// TODO: Landscape

		guiFrame.updateMap(mapElems);
	}

	// Call to move in a direction
	public void directionChosen(String direction) {
		if (gameState != GameState.WaitingMove) // Prevent spam click of button
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
		else if (direction == "East") {
			if (hero.getPosX() == mapElems.length - 1) {
				onLevelFinished();
				return;
			}
			tryMovePlayer(direction);
		}

		// TODO: update monster position
		updateMapElems();
		gameState = GameState.WaitingMove;
	}

	private void tryMovePlayer(String direction) {
		int tmpX = hero.getPosX();
		int tmpY = hero.getPosY();
		hero.moveTowards(direction);

		AMapElement elem = mapElems[hero.getPosY()][hero.getPosX()];
		if (elem != null) {
			if (elem instanceof AMonster) {
				// TODO: Ask player if fight or flee
				startFight((AMonster)elem, true);
				return;
			}
			// set hero back to prev pos if he is moving towards landscape elem
			hero.setPosition(tmpY, tmpX);
		}
	}

	private void onLevelFinished() {
		System.out.println("Level Won !");
		// TODO: reward xp for finishing level
		newLevel();
		return;
	}

	private void startFight(AMonster monster, boolean heroStarts) {
		System.out.println("Fight against " + monster.getName() + " lvl. " + monster.getLevel() + " started !");
		boolean fightEnded = false;

		ACharacter attacker = (heroStarts) ? hero : monster;
		ACharacter defender = (heroStarts) ? monster : hero;
		ACharacter tmpChar;
		int damage;
		int agiDelta = Math.abs(hero.getAgility() - monster.getAgility());
		boolean agiTrigger;
		while (!fightEnded) {
			if (agiDelta != 0 && Swingy.getInstance().rand.nextInt(100) < agiDelta)
				agiTrigger = true;
			else
				agiTrigger = false;

			if (agiTrigger && attacker.getAgility() < defender.getAgility()) {
				System.out.println(defender.getName() + " evaded " + attacker.getName() + "'s attack..!");
			}
			else {
				// Formula inspired by original pokemon damage formula
				damage = ((2 * attacker.getLevel()) + 2) * (attacker.getAttack() / defender.getDefense()) + 2;
				if (agiTrigger) {
					damage *= 2;
					System.out.print(attacker.getName() + " critically strikes..! ");
				}
				else {
					System.out.print(attacker.getName() + " attacks.. ");
				}

				defender.getDamage(damage);
				if (defender.getHp() == 0) {
					System.out.println(defender.getName() + " is KO !");
					// TODO: end fight
					fightEnded = true;
				}
				else {
					System.out.println(defender.getName() + "'s hp down to " + defender.getHp());
				}
			}

			tmpChar = attacker;
			attacker = defender;
			defender = tmpChar;
		}
	}
}
