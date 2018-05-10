package cmutti.model.artifacts.helms;

import cmutti.model.artifacts.AArtifact;
import javax.persistence.*;

@Entity
@DiscriminatorValue("ahelm")
public abstract class AHelm extends AArtifact {
	protected AHelm(String name, int level) {
		super(name, level);
	}

	public String getPresentation() {
		return name + " lv." + level + " (Bonus Hp: " + hp + ")";
	}

	public String getBonusPresentation() {
		return "Hp +" + hp;
	}

	public int getGrowthHp() {
		return 1;
	}
}
