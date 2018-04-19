package cmutti.model.heroes;

public class KarateMan extends AHero {

	public KarateMan(String name, int level) {
		super(name, level);
	}

	protected String getSpritePath() {
		return "sprites/heroes/karateMan/walkSheet.png";
	}

	protected String getMainImg() {
		return "sprites/heroes/karateMan/mainImg.png";
	}

	// Only change growth stats which are differnt for this class
	public int getGrowthAttack() {
		return super.getGrowthAttack() + 2;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
