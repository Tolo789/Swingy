package cmutti.model.monsters.tauros;

import cmutti.model.monsters.AStrongMonster;

public class Tauros extends AStrongMonster {

	public Tauros(int level, int posY, int posX, int artifactDropChance) {
		super("Tauros", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/tauros/Tauros.png";
	}

	// Monster modif stats
	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 2;
	}
}
