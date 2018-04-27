package cmutti.model.artifacts.helms;

public class VikingHelm extends AHelm {
	public VikingHelm(int level) {
		super("Viking Helm", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/helms/VikingHelm.png";
	}

	public int getBaseHp() {
		return 5;
	}
}
