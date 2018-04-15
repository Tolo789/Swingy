package cmutti.model.monsters.legendary;

import cmutti.model.monsters.AMonster;

public class Mew extends AMonster {
	// Mew stats
	static {
		spritePath = "sprites/test.png";
		legendary = true;

		// Base stats
		bXp = 1000;
		bAttack = 15;
		bDefense = 15;
		bHp = 25;
		bAgility = 3;

		// Growth stats
		gXp = 500;
		gAttack = 3;
		gDefense = 2;
		gHp = 3;
		gAgility = 3;
	}

	public Mew(int level) {
		super("Mew", level);
	}

	protected void configImgWidth() {
		imgWidth = img.getWidth();
	}

	protected void configImgHeight() {
		imgHeight = img.getHeight();
	}

	protected int getSpriteStep() {
		// Basic MapElems have only one img
		return 0;
	}

	protected int getSpriteLine() {
		// Basic MapElems have only one img
		return 0;
	}
}
