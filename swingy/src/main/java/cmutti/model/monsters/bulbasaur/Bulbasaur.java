package cmutti.model.monsters.bulbasaur;

import cmutti.model.monsters.AMonster;

public class Bulbasaur extends AMonster {
	static {
		spritePath = "sprites/monsters/mewtwo.png";
	}

	public Bulbasaur(int level, int posY, int posX) {
		super("Bulbasaur", level, posY, posX);
	}

	// Bulbasaur stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}
}
