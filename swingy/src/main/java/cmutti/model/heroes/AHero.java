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
		return "MaxHp: +" + gHp + ", Atk: +" + gAttack + ", Def: +" + gDefense + ", Agi: +" + gAgility;
	}
}
