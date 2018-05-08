package cmutti.model;

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
@Table(name = "heroes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "class", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "amapelem")
public abstract class AMapElement {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int id;

	public boolean needGrass = true; // elems which fill the square completely can set this to false

	protected String name = "";
	@Transient
	protected int posX = 0;
	@Transient
	protected int posY = 0;

	// GUI-only vars
	@Transient
	protected BufferedImage img = null;
	protected int imgWidth = 0;
	protected int imgHeight = 0;

	protected AMapElement(String name, int posY, int posX) {
		try {
			img = ImageIO.read(new File(getSpritePath()));
			configImgWidth();
			configImgHeight();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.posY = posY;
		this.posX = posX;
	}

	protected String getSpritePath() {
		return "sprites/test.png";
	}

	protected void configImgWidth() {
		imgWidth = img.getWidth();
	}

	protected void configImgHeight() {
		imgHeight = img.getHeight();
	}

	public BufferedImage getSprite() {
		return img.getSubimage(getSpriteStep() * imgWidth, getSpriteLine() * imgHeight, imgWidth, imgHeight);
	}

	protected int getSpriteStep() {
		// Basic MapElems have only one img
		return 0;
	}

	protected int getSpriteLine() {
		// Basic MapElems have only one img
		return 0;
	}
}
