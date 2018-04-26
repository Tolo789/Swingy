package cmutti.model.monsters.squirtle;

import cmutti.model.monsters.AMonster;

public class Squirtle extends AMonster {

	public Squirtle(int level, int posY, int posX, int artifactDropChance) {
		super("Squirtle", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/squirtle/Squirtle.png";
	}

	// Monster modif stats
	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
