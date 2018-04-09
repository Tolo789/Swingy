package cmutti.model;

import lombok.Getter;

@Getter
public abstract class AMapElement {
	public static String spritePath = "sprites/test.png";

	protected String name = "";
	protected int posX = 0;
	protected int posY = 0;
}
