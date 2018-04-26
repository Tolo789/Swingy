package cmutti.model.heroes;

public class Healer extends AHero {

	public Healer(String name, int level) {
		super(name, level);
	}

	protected String getSpritePath() {
		return "sprites/heroes/healer/walkSheet.png";
	}

	protected String getMainImg() {
		return "sprites/heroes/healer/mainImg.png";
	}

	public HeroPassive getPassive() {
		return HeroPassive.AutoHealing;
	}

	// Only change growth stats which are differnt for this class
	public int getGrowthHp() {
		return super.getGrowthHp() + 2;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 1;
	}
}
