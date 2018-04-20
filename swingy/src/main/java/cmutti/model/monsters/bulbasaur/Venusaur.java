package cmutti.model.monsters.bulbasaur;

import cmutti.model.monsters.AMonster;

public class Venusaur extends AMonster {

	public Venusaur(int level, int posY, int posX) {
		super("Venusaur", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/legendary/Mewtwo.png";
		// return "sprites/monsters/bulbasaur/Venusaur.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 2;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
