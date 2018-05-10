package cmutti.model.artifacts.weapons;

import javax.persistence.*;

@Entity
@DiscriminatorValue("fishingrope")
public class FishingRope extends AWeapon {
	FishingRope() {
		this(1);
	}

	public FishingRope(int level) {
		super("Fishing Rope", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/weapons/FishingRope.png";
	}
}
