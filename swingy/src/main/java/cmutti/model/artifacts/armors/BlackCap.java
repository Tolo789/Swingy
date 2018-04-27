package cmutti.model.artifacts.armors;

public class BlackCap extends AArmor {
	public BlackCap(int level) {
		super("Black Cap", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/armors/BlackCap.png";
	}

	public int getBaseDefense() {
		return 2;
	}
}
