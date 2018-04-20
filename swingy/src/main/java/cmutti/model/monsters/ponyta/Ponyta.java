package cmutti.model.monsters.ponyta;

import cmutti.model.monsters.AMonster;

public class Ponyta extends AMonster {

	public Ponyta(int level, int posY, int posX) {
		super("Ponyta", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/ponyta/Ponyta.png";
	}

	// Monster modif stats
	public int getBaseXp() {
		return 180;
	}

	public int getGrowthXp() {
		return 60;
	}

	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 1;
	}
}
