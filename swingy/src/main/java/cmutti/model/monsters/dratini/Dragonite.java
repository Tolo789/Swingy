package cmutti.model.monsters.dratini;

import cmutti.model.monsters.AMonster;

public class Dragonite extends AMonster {

	public Dragonite(int level, int posY, int posX) {
		super("Dragonite", level, posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/monsters/dratini/Dragonite.png";
	}

	// Monster modif stats
	public int getBaseXp() {
		return 420;
	}

	public int getBaseHp() {
		return 15;
	}

	public int getBaseAttack() {
		return 7;
	}

	public int getBaseDefense() {
		return 7;
	}

	public int getBaseAgility() {
		return 3;
	}

	public int getGrowthXp() {
		return 110;
	}

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
