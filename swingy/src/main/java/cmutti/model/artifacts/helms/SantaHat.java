package cmutti.model.artifacts.helms;

import javax.persistence.*;

@Entity
@DiscriminatorValue("santahat")
public class SantaHat extends AHelm {
	SantaHat() {
		this(1);
	}

	public SantaHat(int level) {
		super("Santa Hat", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/helms/SantaHat.png";
	}
}
