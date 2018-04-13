package cmutti.model;

import java.awt.image.BufferedImage;
import lombok.Getter;

public abstract class ACharacter extends AMapElement {
	// static class vars, will be overridden by each child class
	// Base stats (at lvl 1)
	protected static int bXp = 0;
	protected static int bAttack = 1;
	protected static int bDefense = 1;
	protected static int bHp = 1;
	protected static int bAgility = 0;

	// Stat growth when level up
	protected static int gXp = 0;
	protected static int gAttack = 1;
	protected static int gDefense = 1;
	protected static int gHp = 1;
	protected static int gAgility = 0;


	// Instance values
	@Getter protected int level = 0;

	// In-game stats
	@Getter protected int xp = 0;
	@Getter protected int attack = 0;
	@Getter protected int defense = 0;
	@Getter protected int hp = 0;
	@Getter protected int maxHp = 0;
	@Getter protected int agility = 0;

	// Vars to simulate walking in GUI map
	protected int steps = 0;			// how many steps have been done in the same direction
	protected String direction = "South";

	protected ACharacter(String name, int level, int posY, int posX) {
		super(name, posY, posX);

		levelUp(level);
	}

	// Controllers will call this func to levelUp
	public void levelUp() {
		levelUp(level + 1);
	}

	// Called by self or when constructing instance
	protected void levelUp(int newLvl) {
		if (newLvl <= 0)
			return;

		xp = bXp + gXp * newLvl;
		attack = bAttack + gAttack * newLvl;
		defense = bDefense + gDefense * newLvl;
		maxHp = bHp + gHp * newLvl;
		hp = maxHp;
		agility = bAgility + gAgility * newLvl;

		level = newLvl;
	}

	public void moveTowards(String dir) {
		if (dir != direction)
			direction = dir;
		steps++;

		if (dir == "North")
			posY--;
		else if (dir == "South")
			posY++;
		else if (dir == "West")
			posX--;
		else if (dir == "East")
			posX++;
	}

	protected int getSpriteStep() {
		// 3 sprites for each direction, must put middle steps between left/right steps
		if (steps % 2 == 0)
			return 0;
		if (steps % 4 == 1)
			return 1;
		return 2;
	}

	// Characters all have sprites for different directions
	protected int getSpriteLine() {
		if (direction == "North")
			return 0;
		if (direction == "South")
			return 1;
		if (direction == "West")
			return 2;
		return 3;
	}
}
