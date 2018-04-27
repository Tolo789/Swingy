package cmutti.model.artifacts.armors;

public class Belt extends AArmor {
	public Belt(int level) {
		super("Belt", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/armors/Belt.png";
	}
}
