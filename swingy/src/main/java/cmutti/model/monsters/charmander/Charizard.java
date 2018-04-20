package cmutti.model.monsters.charmander;

import cmutti.model.monsters.AMonster;

public class Charizard extends AMonster {

	public Charizard(int level, int posY, int posX) {
		super("Charizard", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/charmander/Charizard.png";
	}

	// Monster modif stats
	public int getBaseXp() {
		return 300;
	}

	public int getGrowthXp() {
		return 90;
	}

	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthAttack() {
		return super.getGrowthAttack() + 2;
	}
}
