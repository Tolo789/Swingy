package cmutti.model.artifacts.armors;

import javax.persistence.*;

@Entity
@DiscriminatorValue("belt")
public class Belt extends AArmor {
	Belt() {
		this(1);
	}

	public Belt(int level) {
		super("Belt", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/armors/Belt.png";
	}
}
