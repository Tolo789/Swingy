package cmutti.model.monsters.charmander;

import cmutti.model.monsters.AStrongMonster;

public class Charizard extends AStrongMonster {

	public Charizard(int level, int posY, int posX, int artifactDropChance) {
		super("Charizard", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/charmander/Charizard.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthAttack() {
		return super.getGrowthAttack() + 2;
	}
}
