package cmutti.model.monsters.ratata;

import cmutti.model.monsters.AMonster;

public class Raticate extends AMonster {

	public Raticate(int level, int posY, int posX) {
		super("Raticate", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/ratata/Raticate.png";
	}

	// Monster modif stats
	public int getBaseXp() {
		return 200;
	}

	public int getGrowthXp() {
		return 60;
	}

	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 1;
	}
}
