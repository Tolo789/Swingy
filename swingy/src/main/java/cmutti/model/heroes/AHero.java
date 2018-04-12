package cmutti.model.heroes;

import cmutti.model.ACharacter;
import cmutti.model.artifacts.weapons.AWeapon;
import lombok.Getter;

@Getter
public abstract class AHero extends ACharacter {
	// Define "standard" hero stats
	static {
			// Base stats
			bXp = 0;
			bAttack = 20;
			bDefense = 20;
			bHp = 30;
			bAgility = 5;

			// Growth stats
			gXp = 0;
			gAttack = 2;
			gDefense = 2;
			gHp = 3;
			gAgility = 1;

	}
	public static String mainImg = "";

	// Xp vars
	protected int neededXp;
	protected int tmpXp = 0;

	// Artifacts
	protected AWeapon weapon = null;
	// protected Armor armor = null;
	// protected Helm helm = null;

	protected AHero(String name, int level) {
		super(name, level, 0, 0); // Do not mind x-y pos since thay will be changed when level starts
	}

	// Update neededXp after base levelUp
	public void levelUp(int newLvl) {
		super.levelUp(newLvl);

		// Update after levelUp
		neededXp = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
		xp = tmpXp;
		tmpXp = 0;
	}

	public void setPosition(int posY, int posX) {
		this.posY = posY;
		this.posX = posX;
	}
}
