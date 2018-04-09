package cmutti.model.heroes;

import cmutti.model.ACharacter;
import cmutti.model.artifacts.weapons.AWeapon;
import lombok.Getter;

@Getter
public abstract class AHero extends ACharacter {
	protected int neededXp = 1;

	// Artifacts
	protected AWeapon weapon = null;
	// protected Armor armor = null;
	// protected Helm helm = null;

	protected void levelUp() {
		super.levelUp();

		// Do computations after levelUp
		neededXp = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
		xp = 0;
	}
}
