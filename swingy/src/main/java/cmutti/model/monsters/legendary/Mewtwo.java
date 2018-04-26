package cmutti.model.monsters.legendary;

import cmutti.model.monsters.ALegendaryMonster;

public class Mewtwo extends ALegendaryMonster {

	public Mewtwo(int level, int posY, int posX, int artifactDropChance) {
		super("Mewtwo", level, posY, posX, artifactDropChance);

		mood = MonsterMood.Still;
	}

	protected String getSpritePath() {
		return "sprites/monsters/legendary/Mewtwo.png";
	}

	// Mewtwo stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthAttack() {
		return super.getGrowthAttack() + 2;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 1;
	}
}
