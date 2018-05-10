package cmutti.model.artifacts.helms;

import javax.persistence.*;

@Entity
@DiscriminatorValue("chefhat")
public class ChefHat extends AHelm {
	ChefHat() {
		this(1);
	}

	public ChefHat(int level) {
		super("Chef Hat", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/helms/ChefHat.png";
	}
}
