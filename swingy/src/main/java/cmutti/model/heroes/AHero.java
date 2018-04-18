package cmutti.model.heroes;

import cmutti.model.ACharacter;
import cmutti.model.artifacts.weapons.AWeapon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.Getter;

@Getter
public abstract class AHero extends ACharacter {
	protected static String mainImg = "";

	private BufferedImage mainSprite = null;

	// Xp vars
	protected int neededXp;
	protected int tmpXp = 0;

	// Artifacts
	protected AWeapon weapon = null;
	// protected Armor armor = null;
	// protected Helm helm = null;

	protected AHero(String name, int level) {
		super(name, level, 0, 0); // Do not mind x-y pos since thay will be changed when level starts

		try {
			this.mainSprite = ImageIO.read(new File(mainImg));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	public void setPosition(int posY, int posX) {
		this.posY = posY;
		this.posX = posX;

		direction = "South";
		steps = 0;
	}

	public String getGrowthString() {
		String str = "";
		str = "MaxHp: " + maxHp + " (+" + getGrowthHp() + ")";
		str += ", Atk: " + attack + " (+" + getGrowthAttack() + ")";
		str += ", Def: " + defense + " (+" + getGrowthDefense() + ")";
		str += ", Agi: " + agility + " (+" + getGrowthAgility() + ")";
		return str;
	}
}
