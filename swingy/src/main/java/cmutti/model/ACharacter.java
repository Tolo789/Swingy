package cmutti.model;

import java.awt.image.BufferedImage;
import lombok.Getter;

public abstract class ACharacter extends AMapElement {
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

	// Getters to define base and growth stats
	public abstract int getBaseXp();
	public abstract int getBaseHp();
	public abstract int getBaseAttack();
	public abstract int getBaseDefense();
	public abstract int getBaseAgility();
	public abstract int getGrowthXp();
	public abstract int getGrowthHp();
	public abstract int getGrowthAttack();
	public abstract int getGrowthDefense();
	public abstract int getGrowthAgility();

	// Controllers will call this func to levelUp
	public void levelUp() {
		levelUp(level + 1);
	}

	// Called by self or when constructing instance
	protected void levelUp(int newLvl) {
		if (newLvl <= 0)
			return;

		xp = getBaseXp() + getGrowthXp() * newLvl;
		attack = getBaseAttack() + getGrowthAttack() * newLvl;
		defense = getBaseDefense() + getGrowthDefense() * newLvl;
		maxHp = getBaseHp() + getGrowthHp() * newLvl;
		hp = maxHp;
		agility = getBaseAgility() + getGrowthAgility() * newLvl;

		level = newLvl;
	}

	public void getDamage(int damage) {
		if (damage < 0)	// Avoid errors
			return;

		this.hp -= damage;
		if (this.hp < 0)
			this.hp = 0;
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

	protected void configImgWidth() {
		// 3 sprites for each direction
		imgWidth = img.getWidth() / 3;
	}

	protected void configImgHeight() {
		// Characters all have sprites for different directions
		imgHeight = img.getHeight() / 4;
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
