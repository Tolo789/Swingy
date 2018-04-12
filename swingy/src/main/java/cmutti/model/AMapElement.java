package cmutti.model;

import lombok.Getter;

@Getter
public abstract class AMapElement {
	public static String spritePath = "sprites/test.png";
	public boolean needGrass = true; // elems which fill the square completely can set this to false

	protected String name = "";
	protected int posX = 0;
	protected int posY = 0;
	protected int direction = 0;

	protected AMapElement(String name, int posY, int posX) {
		this.name = name;
		this.posY = posY;
		this.posX = posX;
	}
}
