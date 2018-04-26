package cmutti.model.artifacts.weapons;

public class Guitar extends AWeapon {
	public Guitar(int level) {
		super("Guitar", level);
	}

	protected String getSpritePath() {
		return "sprites/test.png";
	}
}
