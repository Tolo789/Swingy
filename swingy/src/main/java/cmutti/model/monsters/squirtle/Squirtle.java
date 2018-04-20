package cmutti.model.monsters.squirtle;

import cmutti.model.monsters.AMonster;

public class Squirtle extends AMonster {

	public Squirtle(int level, int posY, int posX) {
		super("Squirtle", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/squirtle/Squirtle.png";
	}

	// Monster modif stats
	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
