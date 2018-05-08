package cmutti.model.heroes;

import javax.persistence.*;

@Entity
@DiscriminatorValue("karateman")
public class KarateMan extends AHero {

	KarateMan() {
		this("KarateMan", 1);
	}

	public KarateMan(String name, int level) {
		super(name, level);
	}

	protected String getSpritePath() {
		return "sprites/heroes/karateMan/walkSheet.png";
	}

	protected String getMainImg() {
		return "sprites/heroes/karateMan/mainImg.png";
	}

	public HeroPassive getPassive() {
		return HeroPassive.StonesBreaker;
	}

	public String getClassDescription() {
		String description = "KarateMen are very confident on their unmatched Attack and strong Defense.";
		description += " Moreover, they are so strong and determined that they can break rocks that are on their way.";
		return description;
	}

	// Only change growth stats which are differnt for this class
	public int getGrowthAttack() {
		return super.getGrowthAttack() + 2;
	}

	public int getGrowthDefense() {
		return super.getGrowthDefense() + 1;
	}
}
