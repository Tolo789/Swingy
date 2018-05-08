package cmutti.model.heroes;

import javax.persistence.*;

@Entity
@DiscriminatorValue("karategirl")
public class KarateGirl extends AHero {

	KarateGirl() {
		this("KarateGirl", 1);
	}

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

	public String getClassDescription() {
		String description = "KarateGirls have an incredible Agility and a good Attack too.";
		description += " Their super-human reflexes enables them to flee more easily from enemies and even to dodge some of their sudden attacks.";
		return description;
	}

	// Only change growth stats which are differnt for this class
	public int getGrowthAttack() {
		return super.getGrowthAttack() + 1;
	}

	public int getGrowthAgility() {
		return super.getGrowthAgility() + 2;
	}
}
