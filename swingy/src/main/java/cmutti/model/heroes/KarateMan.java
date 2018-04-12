package cmutti.model.heroes;

public class KarateMan extends AHero {
	static {
		mainImg = "sprites/heroes/karateMan/mainImg.png";

		// Only change growth stats which are differnt for this class
		gAttack = gAttack + 2;
		gDefense = gDefense + 1;
	}

	public KarateMan(String name, int level) {
		super(name, level);
	}
}
