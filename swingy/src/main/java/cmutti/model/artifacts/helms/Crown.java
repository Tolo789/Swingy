package cmutti.model.artifacts.helms;

public class Crown extends AHelm {
	public Crown(int level) {
		super("Crown", level);
	}

	protected String getSpritePath() {
		return "sprites/test.png";
	}

	public int getBaseHp() {
		return 2;
	}
}
