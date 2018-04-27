package cmutti.model.artifacts.helms;

public class ChefHat extends AHelm {
	public ChefHat(int level) {
		super("Chef Hat", level);
	}

	protected String getSpritePath() {
		return "sprites/test.png";
	}
}
