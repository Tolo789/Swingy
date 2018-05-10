package cmutti.model.artifacts.helms;

import javax.persistence.*;

@Entity
@DiscriminatorValue("crown")
public class Crown extends AHelm {
	Crown() {
		this(1);
	}

	public Crown(int level) {
		super("Crown", level);
	}

	protected String getSpritePath() {
		return "sprites/artifacts/helms/Crown.png";
	}

	public int getBaseHp() {
		return 2;
	}
}
