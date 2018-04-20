package cmutti.model.monsters.dratini;

import cmutti.model.monsters.AMonster;

public class Dragonair extends AMonster {

	public Dragonair(int level, int posY, int posX) {
		super("Dragonair", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/dratini/Dragonair.png";
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

	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}
}
