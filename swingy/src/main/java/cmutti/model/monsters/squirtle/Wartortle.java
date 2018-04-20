package cmutti.model.monsters.squirtle;

import cmutti.model.monsters.AMonster;

public class Wartortle extends AMonster {

	public Wartortle(int level, int posY, int posX) {
		super("Wartortle", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/squirtle/Wartortle.png";
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

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
