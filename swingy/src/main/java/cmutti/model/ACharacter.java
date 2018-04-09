package cmutti.model;

import lombok.Getter;

public abstract class ACharacter extends AMapElement {
	@Getter protected int level = 0;

	// Base stats
	@Getter protected int xp = 0;
	@Getter protected int attack = 0;
	@Getter protected int defense = 0;
	@Getter protected int hp = 0;
	@Getter protected int maxHp = 0;
	@Getter protected int agility = 0;

	// Stat growth when level up
	protected int g_xp = 0;
	protected int g_attack = 1;
	protected int g_defense = 1;
	protected int g_hp = 1;
	protected int g_agility = 0;

	protected void levelUp() {
		levelUp(level + 1);
	}

	protected void levelUp(int newLvl) {
		int steps = newLvl - level;
		if (steps <= 0)
			return;

		if (newLvl != 1) {
			xp = xp + g_xp * steps;
			attack = attack + g_attack * steps;
			defense = defense + g_defense * steps;
			maxHp = maxHp + g_hp * steps;
			hp = maxHp;
			agility = agility + g_agility * steps;
		}

		level = newLvl;
	}
}
