package cmutti.model.artifacts.armors;

import javax.persistence.*;

@Entity
@DiscriminatorValue("hawaianskirt")
public class HawaianSkirt extends AArmor {
	HawaianSkirt() {
		this(1);
	}

	public HawaianSkirt(int level) {
		super("Hawaian Skirt", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/armors/HawaianSkirt.png";
	}

	public int getBaseDefense() {
		return 5;
	}
}
