package cmutti.model.monsters.legendary;

import cmutti.model.monsters.AMonster;

public class Mew extends AMonster {

	public Mew(int level, int posY, int posX) {
		super("Mew", level, posY, posX);

		if (level == 1)
			mood = MonsterMood.Flee;
		else
			mood = MonsterMood.Still;
	}

	protected String getSpritePath() {
		return "sprites/monsters/legendary/Mew.png";
	}

	// Mew stats
	public int getBaseXp() {
		return 1000;
	}

	public int getBaseHp() {
		return 20;
	}

	public int getBaseAttack() {
		return 10;
	}

	public int getBaseDefense() {
		return 10;
	}

	public int getBaseAgility() {
		return 3;
	}

	public int getGrowthXp() {
		return 500;
	}

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
