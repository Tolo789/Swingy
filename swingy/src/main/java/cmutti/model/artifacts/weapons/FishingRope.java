package cmutti.model.artifacts.weapons;

public class FishingRope extends AWeapon {
	public FishingRope(int level) {
		super("Fishing Rope", level);
	}

	protected String getSpritePath() {
		return "sprites/test.png";
	}
}
