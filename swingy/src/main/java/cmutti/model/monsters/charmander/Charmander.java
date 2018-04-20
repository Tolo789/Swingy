package cmutti.model.monsters.charmander;

import cmutti.model.monsters.AMonster;

public class Charmander extends AMonster {

	public Charmander(int level, int posY, int posX) {
		super("Charmander", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/charmander/Charmander.png";
	}

	// Monster modif stats
	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}
}
