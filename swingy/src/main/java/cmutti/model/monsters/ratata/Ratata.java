package cmutti.model.monsters.ratata;

import cmutti.model.monsters.AMonster;

public class Ratata extends AMonster {

	public Ratata(int level, int posY, int posX) {
		super("Ratata", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/ratata/Ratata.png";
	}

	// Monster modif stats
	public int getGrowthAgility() {
		return super.getGrowthAgility() + 1;
	}
}
