package cmutti.model;

import java.awt.Image;

public abstract class AMapElement {
	protected int posX = 0;
	protected int posY = 0;
	protected String name = "";
	protected Image image = null;

	public String getName() {
		return name;
	}
}
