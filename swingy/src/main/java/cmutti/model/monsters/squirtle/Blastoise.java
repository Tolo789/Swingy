package cmutti.model.monsters.squirtle;

import cmutti.model.monsters.AStrongMonster;

public class Blastoise extends AStrongMonster {

	public Blastoise(int level, int posY, int posX, int artifactDropChance) {
		super("Blastoise", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/squirtle/Blastoise.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 2;
	}
}
