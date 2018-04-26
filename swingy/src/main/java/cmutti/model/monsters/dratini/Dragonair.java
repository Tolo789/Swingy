package cmutti.model.monsters.dratini;

import cmutti.model.monsters.AMediumMonster;

public class Dragonair extends AMediumMonster {

	public Dragonair(int level, int posY, int posX, int artifactDropChance) {
		super("Dragonair", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/dratini/Dragonair.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}
}
