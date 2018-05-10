package cmutti.model.artifacts.armors;

import javax.persistence.*;

@Entity
@DiscriminatorValue("blackcap")
public class BlackCap extends AArmor {
	BlackCap() {
		this(1);
	}

	public BlackCap(int level) {
		super("Black Cap", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/armors/BlackCap.png";
	}

	public int getBaseDefense() {
		return 2;
	}
}
