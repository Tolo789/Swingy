package cmutti.model.artifacts.weapons;

import javax.persistence.*;

@Entity
@DiscriminatorValue("pan")
public class Pan extends AWeapon {
	Pan() {
		this(1);
	}

	public Pan(int level) {
		super("Pan", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/weapons/Pan.png";
	}

	public int getBaseAttack() {
		return 5;
	}
}
