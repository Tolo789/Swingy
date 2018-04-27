package cmutti.model.artifacts.helms;

public class StrawHat extends AHelm {
	public StrawHat(int level) {
		super("Straw Hat", level);
	}

	protected String getSpritePath() {
		return "sprites/test.png";
	}

	public int getBaseHp() {
		return 5;
	}
}
