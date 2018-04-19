package cmutti.model.monsters;

import cmutti.controller.Swingy;
import cmutti.model.AArtifact;
import cmutti.model.ACharacter;
import cmutti.model.AMapElement;
import cmutti.model.artifacts.weapons.AWeapon;
import cmutti.model.heroes.AHero;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import lombok.Getter;

@Getter
public abstract class AMonster extends ACharacter {
	protected enum MonsterMood {
		Still,
		Roam,
		Search,
		Flee
	}
	@Getter protected static boolean legendary = false;
	private static ArrayList<String> directions = new ArrayList<String>();
	static {
		directions.add(Swingy.getInstance().getMainGame().directions[0]);
		directions.add(Swingy.getInstance().getMainGame().directions[1]);
		directions.add(Swingy.getInstance().getMainGame().directions[2]);
		directions.add(Swingy.getInstance().getMainGame().directions[3]);
	}

	protected MonsterMood mood = MonsterMood.Roam;
	private AMapElement elem;

	// Artifacts that can be droppped
	protected AArtifact weapon = null;

	protected AMonster(String name, int level) {
		super(name, level, 0, 0); // Do not mind x-y pos since thay will be changed when level starts
	}

	// Define "standard" monster stats
	public int getBaseXp() {
		return 100;
	}

	public int getBaseHp() {
		return 10;
	}

	public int getBaseAttack() {
		return 5;
	}

	public int getBaseDefense() {
		return 5;
	}

	public int getBaseAgility() {
		return 1;
	}

	public int getGrowthXp() {
		return 30;
	}

	public int getGrowthHp() {
		return 2;
	}

	public int getGrowthAttack() {
		return 1;
	}

	public int getGrowthDefense() {
		return 1;
	}

	public int getGrowthAgility() {
		return 1;
	}

	// When roaming a monster can detect hero if he's near enough
	public int getDetectionRadius() {
		return 2;
	}

	public void setPosition(int posY, int posX) {
		this.posY = posY;
		this.posX = posX;

		direction = "South";
		steps = 0;
	}

	// Do different action based on mood of the monster
	public void doAction(AHero hero, AMapElement[][] mapElems) {
		// I'd like to use pointer over functions but in Java7 the code would be far uglier than an if-forest
		if (mood == MonsterMood.Roam)
			doRoam(hero, mapElems);
		else if (mood == MonsterMood.Search)
			doSearch(hero, mapElems);
		else if (mood == MonsterMood.Flee)
			doFlee(hero, mapElems);
		// Do nothing if mood == Still
	}

	private void doRoam(AHero hero, AMapElement[][] mapElems) {
		// Change behaviour if hero is in detection zone
		if (hero.getPosX() <= posX + getDetectionRadius() && hero.getPosX() >= posX - getDetectionRadius()) {
			if (hero.getPosY() <= posY + getDetectionRadius() && hero.getPosY() >= posY - getDetectionRadius()) {
				mood = MonsterMood.Search;
				doSearch(hero, mapElems);
				return;
			}
		}

		ArrayList<String> tmpDirections = directions;

		String tryDir;
		int tmpX;
		int tmpY;
		boolean moved = false;
		while (!moved && tmpDirections.size() > 0) {
			tmpX = posX;
			tmpY = posY;
			tryDir = tmpDirections.get(Swingy.getInstance().rand.nextInt(tmpDirections.size()));
			moveTowards(tryDir);

			if (posX < 0 || posY < 0 || posX >= mapElems.length || posY >= mapElems.length) {
				tmpDirections.remove(tryDir);
				posX = tmpX;
				posY = tmpY;
			}
			else {
				elem = mapElems[posY][posX];
				if (elem != null && !(elem instanceof AHero)) {
					tmpDirections.remove(tryDir);
					posX = tmpX;
					posY = tmpY;
					return;
				}
				moved = true;
			}
		}
	}

	private void doSearch(AHero hero, AMapElement[][] mapElems) {
		// TODO
	}

	private void doFlee(AHero hero, AMapElement[][] mapElems) {
		// Get nearest escape, since only mew lv.1 has this behaviour no need to implement pathFinding nor check collisions
		if (posX == mapElems.length / 2) {
			if (posY < mapElems.length - posY)
				moveTowards("North");
			else
				moveTowards("South");
		}
		else {
			if (posX < mapElems.length - posX)
				moveTowards("West");
			else
				moveTowards("East");
		}
	}

	protected void configImgWidth() {
		// 2 sprites for each direction
		imgWidth = img.getWidth() / 2;
	}

	protected int getSpriteStep() {
		// Toggle between left and right foot
		return steps % 2;
	}
}
