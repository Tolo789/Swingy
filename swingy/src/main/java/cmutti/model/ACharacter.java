package cmutti.model;

public abstract class ACharacter extends AMapElement {
	protected int level = 0;

	// Base stats
	protected int experience = 0;
	protected int attack = 0;
	protected int defense = 0;
	protected int hp = 0;
	protected int agility = 0;

	// Stat growth when level up
	protected int g_experience = 0;
	protected int g_attack = 1;
	protected int g_defense = 1;
	protected int g_hp = 1;
	protected int g_agility = 0;

	protected void levelUp(int newLvl) {
		int steps = newLvl - level;
		if (steps <= 0)
			return;
		experience = experience + g_experience * steps;
		attack = attack + g_attack * steps;
		defense = defense + g_defense * steps;
		hp = hp + g_hp * steps;
		agility = agility + g_agility * steps;
	}
}
