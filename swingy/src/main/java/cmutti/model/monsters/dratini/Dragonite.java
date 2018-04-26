package cmutti.model.monsters.dratini;

import cmutti.model.monsters.AHardMonster;

public class Dragonite extends AHardMonster {

	public Dragonite(int level, int posY, int posX, int artifactDropChance) {
		super("Dragonite", level, posY, posX, artifactDropChance);
	}

	protected String getSpritePath() {
		return "sprites/monsters/dratini/Dragonite.png";
	}

	// Monster modif stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthAttack() {
		return super.getGrowthAttack() + 2;
	}

	public int getGrowthDefense() {
		return super.getGrowthHp() + 1;
	}
}
