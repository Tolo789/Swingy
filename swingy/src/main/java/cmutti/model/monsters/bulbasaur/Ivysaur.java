package cmutti.model.monsters.bulbasaur;

import cmutti.model.monsters.AMonster;

public class Ivysaur extends AMonster {

	public Ivysaur(int level, int posY, int posX) {
		super("Ivysaur", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/legendary/Mewtwo.png";
		// return "sprites/monsters/bulbasaur/Ivysaur.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
