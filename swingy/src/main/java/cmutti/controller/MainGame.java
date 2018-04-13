package cmutti.controller;

import cmutti.model.AMapElement;
import cmutti.model.heroes.AHero;
import cmutti.view.gui.FrameGUI;

public class MainGame {
	private enum GameState {
		Loading,
		WaitingMove,
		WaitingCombat
	}

	public static String[] directions = new String[]{"North", "South", "West", "East"};
	// Game vars
	AHero hero;
  int mapSize;
  AMapElement[][] mapElems;
	GameState gameState = GameState.Loading;

	// UIs
	FrameGUI guiFrame;

  MainGame(AHero hero, FrameGUI guiFrame) {
    this.hero = hero;
    this.guiFrame = guiFrame;

    guiFrame.StartMainPanel(hero);

    newLevel();
  }

  private void newLevel() {
    generateLevel();

    guiFrame.updateMap(mapElems);
		gameState = GameState.WaitingMove;
  }

  private void generateLevel() {
    mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
    System.out.println("MapSize: " + mapSize);

    mapElems = new AMapElement[mapSize][mapSize];

    // Add hero to center
    hero.setPosition(mapSize / 2, mapSize / 2);
    mapElems[mapSize / 2][mapSize / 2] = hero;

    // TODO: add landscape

    // TODO: Add monsters
    // for (int y = 0; y < mapSize; y++) {
    //     for (int x = 0; x < mapSize; x++) {
    //
    //     }
    // }
  }

	// Call to move in a direction
	public void directionChosen(String direction) {
		if (gameState != GameState.WaitingMove) // Prevent spam click of button
			return;
		gameState = GameState.Loading;

		boolean moved = false;
		int heroX = hero.getPosX();
		int heroY = hero.getPosY();

		if (direction == "North") {
			if (heroY == 0) {
				onLevelFinished();
				return;
			}

			moved = true;
		}
		else if (direction == "South") {
			if (heroY == mapElems.length - 1) {
				onLevelFinished();
				return;
			}

			moved = true;
		}
		else if (direction == "West") {
			if (heroX == 0) {
				onLevelFinished();
				return;
			}

			moved = true;
		}
		else if (direction == "East") {
			if (heroX == mapElems.length - 1) {
				onLevelFinished();
				return;
			}

			moved = true;
		}

		if (moved) {
			hero.moveTowards(direction);
			mapElems[hero.getPosY()][hero.getPosX()] = hero;
			mapElems[heroY][heroX] = null;
	    guiFrame.updateMap(mapElems);
		}

		gameState = GameState.WaitingMove;
	}

	private void onLevelFinished() {
		System.out.println("Level Won !");
		newLevel();
		return;
	}
}
