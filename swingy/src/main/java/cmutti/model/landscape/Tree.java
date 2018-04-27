package cmutti.model.landscape;

import cmutti.model.AMapElement;

public class Tree extends AMapElement {

	public Tree(int posY, int posX) {
		super("tree", posY, posX);
	}

	protected String getSpritePath() {
		return "sprites/landscape/Tree.png";
	}
}
