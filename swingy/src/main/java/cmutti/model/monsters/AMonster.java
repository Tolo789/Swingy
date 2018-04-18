package cmutti.model.monsters;

import cmutti.model.AArtifact;
import cmutti.model.ACharacter;
import cmutti.model.AMapElement;
import cmutti.model.artifacts.weapons.AWeapon;
import cmutti.model.heroes.AHero;
import java.awt.image.BufferedImage;
import lombok.Getter;

@Getter
public abstract class AMonster extends ACharacter {
	@Getter protected static boolean legendary = false;

	// Artifacts that can be droppped
	protected AArtifact weapon = null;

	protected AMonster(String name, int level) {
		super(name, level, 0, 0); // Do not mind x-y pos since thay will be changed when level starts
	}

	// Define "standard" monster stats
	public int getBaseXp() {
		return 100;
	}

	public int getBaseHp() {
		return 10;
	}

	public int getBaseAttack() {
		return 5;
	}

	public int getBaseDefense() {
		return 5;
	}

	public int getBaseAgility() {
		return 1;
	}

	public int getGrowthXp() {
		return 30;
	}

	public int getGrowthHp() {
		return 2;
	}

	public int getGrowthAttack() {
		return 1;
	}

	public int getGrowthDefense() {
		return 1;
	}

	public int getGrowthAgility() {
		return 1;
	}

	public void setPosition(int posY, int posX) {
		this.posY = posY;
		this.posX = posX;

		direction = "South";
		steps = 0;
	}

	public void doAction(AHero hero, AMapElement[][] mapElems) {
		// TODO
	}

	protected void configImgWidth() {
		// 2 sprites for each direction
		imgWidth = img.getWidth() / 2;
	}

	protected int getSpriteStep() {
		// Toggle between left and right foot
		return steps % 2;
	}
}
