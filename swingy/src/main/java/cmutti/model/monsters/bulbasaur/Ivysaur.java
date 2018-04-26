package cmutti.model.monsters.bulbasaur;

import cmutti.model.monsters.AMediumMonster;

public class Ivysaur extends AMediumMonster {

	public Ivysaur(int level, int posY, int posX, int artifactDropChance) {
		super("Ivysaur", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/bulbasaur/Ivysaur.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
