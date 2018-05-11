package cmutti.model.monsters;

import cmutti.controller.Swingy;
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
		Flee
	}

	private static ArrayList<String> directions = new ArrayList<String>();
	static {
		directions.add(Swingy.getInstance().getMainGame().directions[0]);
		directions.add(Swingy.getInstance().getMainGame().directions[1]);
		directions.add(Swingy.getInstance().getMainGame().directions[2]);
		directions.add(Swingy.getInstance().getMainGame().directions[3]);
	}

	protected MonsterMood mood = MonsterMood.Roam;
	private int roamPause = getRoamPause(); // how many turn wait at maximum before movinfg
	private AMapElement elem;
	private int artifactDropChance = 0;

	protected AMonster(String name, int level, int posY, int posX, int artifactDropChance) {
		super(name, level, posY, posX);
		this.artifactDropChance = artifactDropChance;
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
		return 40;
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

	// Artifacts drop chance by rarity
	public int getRareArtifactChance() {
		return 20;
	}

	public int getEpicArtifactChance() {
		return 5;
	}

	// When roaming a monster can detect hero if he's near enough
	public int getDetectionRadius() {
		// By default all monsters are pacific
		// this means that they will not start searching for you to start a fight
		// however thay can still bump into you
		return 0;
	}

	public int getRoamPause() {
		return Swingy.getInstance().rand.nextInt(3);
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
		else if (mood == MonsterMood.Flee)
			doFlee(hero, mapElems);
		// Do nothing if mood == Still
	}

	private void doRoam(AHero hero, AMapElement[][] mapElems) {
		// Do nothing if in pause
		if (roamPause > 0) {
			roamPause--;
			return;
		}
		roamPause = getRoamPause();

		ArrayList<String> tmpDirections = new ArrayList<String>(directions);

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
				}
				else {
					moved = true;
				}
			}
		}
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

	public String getStatResume() {
		String str = "";
		str = "MaxHp: " + maxHp;
		str += ", Atk: " + attack;
		str += ", Def: " + defense;
		str += ", Agi: " + agility;
		return str;
	}
}
