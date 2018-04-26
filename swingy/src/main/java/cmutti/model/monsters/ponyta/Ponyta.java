package cmutti.model.monsters.ponyta;

import cmutti.model.monsters.AMediumMonster;

public class Ponyta extends AMediumMonster {

	public Ponyta(int level, int posY, int posX, int artifactDropChance) {
		super("Ponyta", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/ponyta/Ponyta.png";
	}

	// Monster modif stats
	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 1;
	}
}
