package cmutti.model.artifacts.armors;

public class HawaianSkirt extends AArmor {
	public HawaianSkirt(int level) {
		super("Hawaian Skirt", level);
	}

	protected String getSpritePath() {
		return "sprites/test.png";
	}

	public int getBaseDefense() {
		return 5;
	}
}
