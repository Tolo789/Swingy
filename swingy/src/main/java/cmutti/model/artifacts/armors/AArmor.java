package cmutti.model.artifacts.armors;

import cmutti.model.artifacts.AArtifact;
import javax.persistence.Embeddable;

@Embeddable
public abstract class AArmor extends AArtifact {
	protected AArmor(String name, int level) {
		super(name, level);
	}

	public String getPresentation() {
		return name + " lv." + level + " (Bonus Defense: " + defense + ")";
	}

	public String getBonusPresentation() {
		return "Defense +" + defense;
	}

	public int getGrowthDefense() {
		return 1;
	}
}
