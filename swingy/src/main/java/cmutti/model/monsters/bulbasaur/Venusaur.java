package cmutti.model.monsters.bulbasaur;

import cmutti.model.monsters.AMonster;

public class Venusaur extends AMonster {

	public Venusaur(int level, int posY, int posX) {
		super("Venusaur", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/bulbasaur/Venusaur.png";
	}

	// Monster modif stats
	public int getBaseXp() {
		return 300;
	}

	public int getGrowthXp() {
		return 90;
	}

	public int getGrowthHp() {
		return super.getGrowthHp() + 2;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
