package cmutti.model.monsters.squirtle;

import cmutti.model.monsters.AMediumMonster;

public class Wartortle extends AMediumMonster {

	public Wartortle(int level, int posY, int posX, int artifactDropChance) {
		super("Wartortle", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/squirtle/Wartortle.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
