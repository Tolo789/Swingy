package cmutti.model.heroes;

public class Backpacker extends AHero {

	public Backpacker(String name, int level) {
		super(name, level);
	}

	protected String getSpritePath() {
		return "sprites/heroes/backpacker/walkSheet.png";
	}

	protected String getMainImg() {
		return "sprites/heroes/backpacker/mainImg.png";
	}

	public HeroPassive getPassive() {
		return HeroPassive.EfficientTraveller;
	}

	public String getClassDescription() {
		String description = "Backpackers get more Xp than average when they finish a level.";
		description += " Moreover their great Defense and enhanced Hp enable them to survive longer.";
		return description;
	}

	// Only change growth stats which are differnt for this class
	public int getGrowthDefense() {
		return super.getGrowthDefense() + 2;
	}

	public int getGrowthHp() {
		return super.getGrowthHp() + 1;
	}
}
