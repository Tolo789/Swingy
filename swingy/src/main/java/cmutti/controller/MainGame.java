package cmutti.controller;

import cmutti.model.AMapElement;
import cmutti.model.heroes.AHero;
import cmutti.view.gui.FrameGUI;

public class MainGame {
	// Game vars
	AHero hero;
  int mapSize;
  AMapElement[][] mapElems;

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
	public void directionChosen(int dirIdx) {
		System.out.println(dirIdx);
		//hero.levelUp();
	}
}
