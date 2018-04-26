package cmutti.model.monsters.charmander;

import cmutti.model.monsters.AMediumMonster;

public class Charmeleon extends AMediumMonster {

	public Charmeleon(int level, int posY, int posX, int artifactDropChance) {
		super("Charmeleon", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/charmander/Charmeleon.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}
}
