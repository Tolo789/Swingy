package cmutti.model.artifacts.weapons;

public class Pan extends AWeapon {
	public Pan(int level) {
		super("Pan", level);
	}

	protected String getSpritePath() {
		return "sprites/test.png";
	}
}
