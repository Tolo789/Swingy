package cmutti.model.monsters.squirtle;

import cmutti.model.monsters.AMonster;

public class Blastoise extends AMonster {

	public Blastoise(int level, int posY, int posX) {
		super("Blastoise", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/squirtle/Blastoise.png";
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

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 2;
	}
}
