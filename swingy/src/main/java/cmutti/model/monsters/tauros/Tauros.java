package cmutti.model.monsters.tauros;

import cmutti.model.monsters.AMonster;

public class Tauros extends AMonster {

	public Tauros(int level, int posY, int posX) {
		super("Tauros", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/tauros/Tauros.png";
	}

	// Monster modif stats
	public int getBaseXp() {
		return 250;
	}

	public int getGrowthXp() {
		return 60;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 2;
	}
}
