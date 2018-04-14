package cmutti.model.monsters;

import cmutti.model.AArtifact;
import cmutti.model.ACharacter;
import cmutti.model.artifacts.weapons.AWeapon;
import java.awt.image.BufferedImage;
import lombok.Getter;

@Getter
public abstract class AMonster extends ACharacter {
	// Define "standard" monster stats
	static {
			// Base stats
			bXp = 100;
			bAttack = 10;
			bDefense = 10;
			bHp = 20;
			bAgility = 3;

			// Growth stats
			gXp = 30;
			gAttack = 1;
			gDefense = 1;
			gHp = 2;
			gAgility = 1;

	}
	@Getter protected static boolean legendary = false;

	// Artifacts that can be droppped
	protected AArtifact weapon = null;

	protected AMonster(String name, int level) {
		super(name, level, 0, 0); // Do not mind x-y pos since thay will be changed when level starts
	}

	public void setPosition(int posY, int posX) {
		this.posY = posY;
		this.posX = posX;

		direction = "South";
		steps = 0;
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
