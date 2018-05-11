package cmutti.model;

import cmutti.controller.MainGame;
import java.awt.image.BufferedImage;
import javax.persistence.*;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "acharacter")
public abstract class ACharacter extends AMapElement {
	// Instance values
	@Min(1)
	protected int level = 0;

	// In-game stats
	@Min(0)
	protected int xp = 0;
	@Min(0)
	protected int attack = 0;
	@Min(0)
	protected int defense = 0;
	@Min(1)
	protected int hp = 0;
	@Min(1)
	protected int maxHp = 0;
	@Min(0)
	protected int agility = 0;

	// Vars to simulate walking in GUI map
	@Transient
	protected int steps = 0;			// how many steps have been done in the same direction
	@Transient
	protected String direction = MainGame.SOUTH;

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

	public void setPosition(int posY, int posX) {
		this.posY = posY;
		this.posX = posX;
	}

	public void moveTowards(String dir) {
		if (dir != direction)
			direction = dir;
		steps++;

		if (dir.equals(MainGame.NORTH))
			posY--;
		else if (dir.equals(MainGame.SOUTH))
			posY++;
		else if (dir.equals(MainGame.WEST))
			posX--;
		else if (dir.equals(MainGame.EAST))
			posX++;
	}

	public void fullHeal() {
		hp = maxHp;
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
		if (direction.equals(MainGame.NORTH))
			return 0;
		if (direction.equals(MainGame.SOUTH))
			return 1;
		if (direction.equals(MainGame.WEST))
			return 2;
		return 3;
	}
}
