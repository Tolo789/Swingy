package cmutti.model.heroes;

public class KarateGirl extends AHero {

	public KarateGirl(String name, int level) {
		super(name, level);
	}

	protected String getSpritePath() {
		return "sprites/heroes/karateGirl/walkSheet.png";
	}

	protected String getMainImg() {
		return "sprites/heroes/karateGirl/mainImg.png";
	}

	public HeroPassive getPassive() {
		return HeroPassive.ArtfulDodger;
	}

	// Only change growth stats which are differnt for this class
	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 2;
	}
}
