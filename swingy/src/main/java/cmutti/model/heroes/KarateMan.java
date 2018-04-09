package cmutti.model.heroes;

public class KarateMan extends AHero {

	public KarateMan(String name) {
		this.name = name;
		// Redefine base stats
		attack = 20;
		defense = 10;
		hp = 30;
		agility = 5;

		// Redefine stat growth
		g_attack = 4;
		g_defense = 2;
		g_hp = 3;
		g_agility = 1;
	}
}
