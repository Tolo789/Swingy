package cmutti.model.heroes;

public class KarateMan extends AHero {
	static {
		spritePath = "sprites/heroes/karateMan/walkSheet.png";
		mainImg = "sprites/heroes/karateMan/mainImg.png";
	}

	public KarateMan(String name, int level) {
		super(name, level);
	}

	// Only change growth stats which are differnt for this class
	public int getGrowthAttack() {
		return super.getGrowthAttack() + 2;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
