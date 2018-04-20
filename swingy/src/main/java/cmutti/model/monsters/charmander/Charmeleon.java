package cmutti.model.monsters.charmander;

import cmutti.model.monsters.AMonster;

public class Charmeleon extends AMonster {

	public Charmeleon(int level, int posY, int posX) {
		super("Charmeleon", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/charmander/Charmeleon.png";
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
