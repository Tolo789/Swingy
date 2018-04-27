package cmutti.model.landscape;

import cmutti.model.AMapElement;

public class Stone extends AMapElement {

	public Stone(int posY, int posX) {
		super("stone", posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/landscape/Stone.png";
	}
}
