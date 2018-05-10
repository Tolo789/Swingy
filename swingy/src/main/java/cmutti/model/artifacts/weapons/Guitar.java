package cmutti.model.artifacts.weapons;

import javax.persistence.*;

@Entity
@DiscriminatorValue("guitar")
public class Guitar extends AWeapon {
	Guitar() {
		this(1);
	}

	public Guitar(int level) {
		super("Guitar", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/weapons/Guitar.png";
	}

	public int getBaseAttack() {
		return 2;
	}
}
