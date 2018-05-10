package cmutti.model.artifacts.weapons;

import cmutti.model.artifacts.AArtifact;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "aweapon")
public abstract class AWeapon extends AArtifact {
	protected AWeapon(String name, int level) {
		super(name, level);
	}

	public String getPresentation() {
		return name + " lv." + level + " (Bonus Attack: " + attack + ")";
	}

	public String getBonusPresentation() {
		return "Attack +" + attack;
	}

	public int getGrowthAttack() {
		return 1;
	}
}
