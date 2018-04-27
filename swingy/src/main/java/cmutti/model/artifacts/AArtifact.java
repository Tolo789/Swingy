package cmutti.model.artifacts;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.Getter;


@Getter
public abstract class AArtifact {
	protected BufferedImage img = null;
	protected String name = "";
	protected int level = 0;
	protected int attack = 0;
	protected int defense = 0;
	protected int hp = 0;

	protected AArtifact(String name, int level) {
		try {
			img = ImageIO.read(new File(getSpritePath()));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.level = level;

		attack = getBaseAttack() + getGrowthAttack() * level;
		defense = getBaseDefense() + getGrowthDefense() * level;
		hp = getBaseHp() + getGrowthHp() * level;
	}

	public abstract String getPresentation();
	public abstract String getBonusPresentation();

	protected String getSpritePath() {
		return "sprites/test.png";
	}

	public int getBaseHp() {
		return 0;
	}

	public int getBaseAttack() {
		return 0;
	}

	public int getBaseDefense() {
		return 0;
	}

	public int getGrowthHp() {
		return 0;
	}

	public int getGrowthAttack() {
		return 0;
	}

	public int getGrowthDefense() {
		return 0;
	}
}
