package cmutti.model.artifacts.helms;

import javax.persistence.*;

@Entity
@DiscriminatorValue("cowboyhat")
public class CowboyHat extends AHelm {
	CowboyHat() {
		this(1);
	}

	public CowboyHat(int level) {
		super("Cowboy Hat", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/helms/CowboyHat.png";
	}

	public int getBaseHp() {
		return 2;
	}
}
