package cmutti.model.monsters.ponyta;

import cmutti.model.monsters.AMonster;

public class Rapidash extends AMonster {

	public Rapidash(int level, int posY, int posX) {
		super("Rapidash", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/ponyta/Rapidash.png";
	}

	// Monster modif stats
	public int getBaseXp() {
		return 250;
	}

	public int getGrowthXp() {
		return 60;
	}

	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 2;
	}
}
