package cmutti.model.artifacts.helms;

public class CowboyHat extends AHelm {
	public CowboyHat(int level) {
		super("Cowboy Hat", level);
	}

	protected String getSpritePath() {
		return "sprites/test.png";
	}

	public int getBaseHp() {
		return 2;
	}
}
