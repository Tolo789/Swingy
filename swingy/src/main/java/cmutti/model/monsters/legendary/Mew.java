package cmutti.model.monsters.legendary;

import cmutti.model.monsters.AMonster;

public class Mew extends AMonster {
	static {
		spritePath = "sprites/monsters/mew.png";
		legendary = true;
	}

	public Mew(int level) {
		super("Mew", level);

		if (level == 1)
			mood = MonsterMood.Flee;
		else
			mood = MonsterMood.Still;
	}

	// Mew stats
	public int getBaseXp() {
		return 1000;
	}

	public int getBaseHp() {
		return 25;
	}

	public int getBaseAttack() {
		return 15;
	}

	public int getBaseDefense() {
		return 15;
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
