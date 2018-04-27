package cmutti.model.artifacts.weapons;

public class Pan extends AWeapon {
	public Pan(int level) {
		super("Pan", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/weapons/Pan.png";
	}

	public int getBaseAttack() {
		return 5;
	}
}
