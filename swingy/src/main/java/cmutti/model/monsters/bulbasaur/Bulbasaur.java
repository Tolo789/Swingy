package cmutti.model.monsters.bulbasaur;

import cmutti.model.monsters.AMonster;

public class Bulbasaur extends AMonster {

	public Bulbasaur(int level, int posY, int posX) {
		super("Bulbasaur", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/bulbasaur/Bulbasaur.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}
}
