package cmutti.model.monsters.ratata;

import cmutti.model.monsters.AMediumMonster;

public class Raticate extends AMediumMonster {

	public Raticate(int level, int posY, int posX, int artifactDropChance) {
		super("Raticate", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/ratata/Raticate.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 1;
	}
}
