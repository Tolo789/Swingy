package cmutti.model.artifacts.helms;

import javax.persistence.*;

@Entity
@DiscriminatorValue("vikinghelm")
public class VikingHelm extends AHelm {
	VikingHelm() {
		this(1);
	}

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
