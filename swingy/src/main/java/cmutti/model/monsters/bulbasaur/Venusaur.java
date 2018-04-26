package cmutti.model.monsters.bulbasaur;

import cmutti.model.monsters.AStrongMonster;

public class Venusaur extends AStrongMonster {

	public Venusaur(int level, int posY, int posX, int artifactDropChance) {
		super("Venusaur", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/bulbasaur/Venusaur.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 2;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
