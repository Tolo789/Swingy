package cmutti.model.artifacts.helms;

public class SantaHat extends AHelm {
	public SantaHat(int level) {
		super("Santa Hat", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/helms/SantaHat.png";
	}
}
