package cmutti.model.artifacts.helms;

import javax.persistence.*;

@Entity
@DiscriminatorValue("strawhat")
public class StrawHat extends AHelm {
	StrawHat() {
		this(1);
	}

	public StrawHat(int level) {
		super("Straw Hat", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/helms/StrawHat.png";
	}

	public int getBaseHp() {
		return 5;
	}
}
