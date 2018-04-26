package cmutti.model.monsters.dratini;

import cmutti.model.monsters.AMonster;

public class Dratini extends AMonster {

	public Dratini(int level, int posY, int posX, int artifactDropChance) {
		super("Dratini", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/dratini/Dratini.png";
	}

	// Monster modif stats
	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}
}
