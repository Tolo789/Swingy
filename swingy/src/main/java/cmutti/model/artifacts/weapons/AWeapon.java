package cmutti.model.artifacts.weapons;

import cmutti.model.artifacts.AArtifact;

public abstract class AWeapon extends AArtifact {
	protected AWeapon(String name, int level) {
		super(name, level);
	}

	public String getPresentation() {
		return name + " lv." + level + " (Bonus Attack: " + attack + ")";
	}

	public int getGrowthAttack() {
		return 1;
	}
}
