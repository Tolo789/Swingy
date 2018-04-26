package cmutti.model.monsters.ponyta;

import cmutti.model.monsters.AStrongMonster;

public class Rapidash extends AStrongMonster {

	public Rapidash(int level, int posY, int posX, int artifactDropChance) {
		super("Rapidash", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/ponyta/Rapidash.png";
	}

	// Monster modif stats
	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 2;
	}
}
