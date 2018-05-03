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

	public String getClassDescription() {
		String description = "Healers heal themselves for a little of their MaxHp after a fight.";
		description += " Thanks to their huge Hp and enhanced Agility, even medium monster are not a threat.";
		return description;
	}

	// Only change growth stats which are differnt for this class
	public int getGrowthHp() {
		return super.getGrowthHp() + 2;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 1;
	}
}
