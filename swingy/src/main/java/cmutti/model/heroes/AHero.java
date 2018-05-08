package cmutti.model.heroes;

import cmutti.model.ACharacter;
import cmutti.model.artifacts.armors.AArmor;
import cmutti.model.artifacts.helms.AHelm;
import cmutti.model.artifacts.weapons.AWeapon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "ahero")
public abstract class AHero extends ACharacter {
	public enum HeroPassive {
		None,
		StonesBreaker,
		AutoHealing,
		EfficientTraveller,
		ArtfulDodger
	}

	@Transient
	private BufferedImage mainSprite = null;

	// Xp vars
	protected int neededXp;
	protected int tmpXp = 0;

	// Artifacts
	@Embedded
	protected AArmor armor = null;
	@Embedded
	protected AHelm helm = null;
	@Embedded
	protected AWeapon weapon = null;

	AHero() {
		this("Hero", 1);
	}

	protected AHero(String name, int level) {
		super(name, level, 0, 0); // Do not mind x-y pos since thay will be changed when level starts

		try {
			this.mainSprite = ImageIO.read(new File(getMainImg()));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Config of heroes
	protected abstract String getMainImg();
	public abstract HeroPassive getPassive();
	public abstract String getClassDescription();

	// Define "standard" hero stats
	public int getBaseXp() {
		return 0;
	}

	public int getBaseHp() {
		return 30;
	}

	public int getBaseAttack() {
		return 20;
	}

	public int getBaseDefense() {
		return 20;
	}

	public int getBaseAgility() {
		return 5;
	}

	public int getGrowthXp() {
		return 0;
	}

	public int getGrowthHp() {
		return 3;
	}

	public int getGrowthAttack() {
		return 2;
	}

	public int getGrowthDefense() {
		return 2;
	}

	public int getGrowthAgility() {
		return 1;
	}

	// Update neededXp after base levelUp
	public void levelUp(int newLvl) {
		super.levelUp(newLvl);

		// Add artifacts bonus stats
		if (armor != null) {
			defense += armor.getDefense();
		}
		if (helm != null) {
			maxHp += helm.getHp();
			hp = maxHp;
		}
		if (weapon != null) {
			attack += weapon.getAttack();
		}

		// Update after levelUp
		neededXp = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
		xp = tmpXp;
	}

	public boolean gainXp(int gain) {
		xp += gain;

		if (xp >= neededXp) {
			tmpXp = xp - neededXp;
			this.levelUp();
			return true;
		}
		return false;
	}

	public String getGrowthString() {
		String str = "";
		str = "MaxHp +" + getGrowthHp();
		str += ", Atk +" + getGrowthAttack();
		str += ", Def +" + getGrowthDefense();
		str += ", Agi +" + getGrowthAgility();
		return str;
	}

	public boolean heal(int amount) {
		if (hp == 0 || hp == maxHp)
			return false;

		hp += amount;
		if (hp > maxHp) {
			hp = maxHp;
		}

		return true;
	}

	public void equipArmor(AArmor newArmor) {
		if (armor != null) {
			defense -= armor.getDefense();
		}
		armor = newArmor;
		defense += armor.getDefense();
	}

	public void equipHelm(AHelm newHelm) {
		if (helm != null) {
			maxHp -= helm.getHp();
		}
		helm = newHelm;
		maxHp += helm.getHp();

		if (hp > maxHp)
			hp = maxHp;
	}

	public void equipWeapon(AWeapon newWeapon) {
		if (weapon != null) {
			attack -= weapon.getAttack();
		}
		weapon = newWeapon;
		attack += weapon.getAttack();
	}
}
