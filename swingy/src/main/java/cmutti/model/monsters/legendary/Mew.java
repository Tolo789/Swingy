package cmutti.model.monsters.legendary;

import cmutti.model.monsters.ALegendaryMonster;

public class Mew extends ALegendaryMonster {

	public Mew(int level, int posY, int posX, int artifactDropChance) {
		super("Mew", level, posY, posX, artifactDropChance);

		if (level == 1)
			mood = MonsterMood.Flee;
		else
			mood = MonsterMood.Still;
	}

	protected String getSpritePath() {
		return "sprites/monsters/legendary/Mew.png";
	}

	// Mew stats
	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}

	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 2;
	}
}
