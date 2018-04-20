package cmutti.model.monsters;

import cmutti.controller.Swingy;
import cmutti.model.monsters.bulbasaur.*;
import cmutti.model.monsters.legendary.Mew;
import java.util.ArrayList;

public class MonsterFactory {
	public enum Tier {
		Common,
		Rare,
		Epic
	}
	// Used to separate tiers
	public final static int newbieLvl = 5;
	public final static int rookieLvl = 10;

	// Variable used each time we generate a monster list
	static ArrayList<AMonster> monsterList;
	static ArrayList<Class<? extends AMonster>> commonMonsters;
	static ArrayList<Class<? extends AMonster>> rareMonsters;
	static ArrayList<Class<? extends AMonster>> epicMonsters;
	static int heroLvl;
	static int monstersNbr;
	static int maxMonsters;
	static int minMonsters;
	static int percentRare;
	static int percentEpic;
	static int mapSize;
	static int posX;
	static int posY;
	static int smallerBound;
	static int biggerBound;
	static boolean[][] hasMonster;	// fast way to check if monster at given pos has already been added
	static boolean hasMewTwo;
	static boolean hasMew;

	public static ArrayList<AMonster> generateMonsterList(int heroLevel, int size) {
		heroLvl = heroLevel;
		mapSize = size;
		monsterList = new ArrayList<AMonster>();

		// only display fleeing Mew if hero is lvl 1
		if (heroLvl == 1) {
			setLegendaryPosition();
			monsterList.add(new Mew(1, posY, posX));

			return monsterList;
		}

		monstersNbr = (mapSize * mapSize);
		percentRare = 10;
		percentEpic = 0;
		hasMewTwo = false;
		hasMew = false;
		if (heroLvl < newbieLvl) {
			minMonsters = (int)(0.05 * monstersNbr);
			maxMonsters = (int)(0.07 * monstersNbr);
			percentRare = 10;
			percentEpic = 0;
		}
		else if (heroLvl < rookieLvl) {
			minMonsters = (int)(0.07 * monstersNbr);
			maxMonsters = (int)(0.09 * monstersNbr);
			percentRare = 30;
			percentEpic = 10;
		}
		else {
			minMonsters = (int)(0.09 * monstersNbr);
			maxMonsters = (int)(0.11 * monstersNbr);
			percentRare = 50;
			percentEpic = 20;
			hasMewTwo = true;
			if (heroLvl >= 15 && Swingy.getInstance().rand.nextInt(3) == 0) {
				hasMew = true;
				hasMewTwo = false;
			}
		}
		generateMonstersTypes();
		monstersNbr = Swingy.getInstance().rand.nextInt((maxMonsters - minMonsters) + 1) + minMonsters;
		percentRare = percentRare * monstersNbr / 100 ;
		percentEpic = percentEpic * monstersNbr / 100;
		System.out.println(minMonsters + "-" + maxMonsters + " => " + monstersNbr);

		hasMonster = new boolean[mapSize][mapSize];
		hasMonster[mapSize / 2][mapSize / 2] = true; // Hero start pos

		// Legendary
		if (hasMew) {
			setLegendaryPosition();
			monsterList.add(new Mew(heroLvl + 3, posY, posX));
		}
		else if (hasMewTwo) {
			setLegendaryPosition();
			// TODO
			monsterList.add(new Mew(heroLvl + 3, posY, posX));
		}

		// Epic
		smallerBound = (mapSize / 2) - 8;
		biggerBound = (mapSize / 2) + 8;
		for (int i = percentEpic; i > 0; i--) {
			AMonster newMonster = buildMonster(epicMonsters, 4, Tier.Epic);
			monsterList.add(newMonster);
			hasMonster[posY][posX] = true;
		}

		// Rare
		smallerBound = (mapSize / 2) - 5;
		biggerBound = (mapSize / 2) + 5;
		for (int i = percentRare; i > 0; i--) {
			AMonster newMonster = buildMonster(rareMonsters, 2, Tier.Rare);
			monsterList.add(newMonster);
			hasMonster[posY][posX] = true;
		}

		// Common
		smallerBound = (mapSize / 2) - 2;
		biggerBound = (mapSize / 2) + 2;
		while (monsterList.size() < monstersNbr) {
			AMonster newMonster = buildMonster(commonMonsters, 0, Tier.Common);
			monsterList.add(newMonster);
			hasMonster[posY][posX] = true;
		}

		return monsterList;
	}

	private static AMonster buildMonster(ArrayList<Class<? extends AMonster>> monsterTypes, int bonusLvl, Tier tier) {
		int idx = Swingy.getInstance().rand.nextInt(monsterTypes.size());
		AMonster newMonster = null;
        try
        {
			// TODO: drop artefact
			setMonsterPosition();
			newMonster = monsterTypes.get(idx).getConstructor(int.class, int.class, int.class).newInstance(heroLvl + bonusLvl, posY, posX);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return newMonster;
	}

	private static void generateMonstersTypes() {
		commonMonsters = new ArrayList<Class<? extends AMonster>>();
		rareMonsters = new ArrayList<Class<? extends AMonster>>();
		epicMonsters = new ArrayList<Class<? extends AMonster>>();

		// TODO: config classes
		if (heroLvl < newbieLvl) {
			// Common
			commonMonsters.add(Bulbasaur.class);

			// Rare
			rareMonsters.add(Ivysaur.class);

			// Epic
			epicMonsters.add(Venusaur.class);
		}
		else if (heroLvl < rookieLvl) {
			// Common
			commonMonsters.add(Bulbasaur.class);
			commonMonsters.add(Ivysaur.class);

			// Rare
			rareMonsters.add(Ivysaur.class);

			// Epic
			epicMonsters.add(Venusaur.class);
		}
		else {
			// Common
			commonMonsters.add(Ivysaur.class);

			// Rare
			rareMonsters.add(Ivysaur.class);
			rareMonsters.add(Venusaur.class);

			// Epic
			epicMonsters.add(Venusaur.class);
		}
	}

	private static void setLegendaryPosition() {
		int pos = Swingy.getInstance().rand.nextInt(4);

		if (pos == 0) { // North
			posX = mapSize / 2;
			posY = 1;
		}
		else if (pos == 1) { // South
			posX = mapSize / 2;
			posY = mapSize - 2;
		}
		else if (pos == 2) { // West
			posY = mapSize / 2;
			posX = 1;
		}
		else { // East
			posY = mapSize / 2;
			posX = mapSize - 2;
		}
	}

	private static void setMonsterPosition() {
		do {
			do {
				posX = Swingy.getInstance().rand.nextInt(mapSize);
				posY = Swingy.getInstance().rand.nextInt(mapSize);
			} while ((posY <= biggerBound && posY >= smallerBound) && (posX <= biggerBound && posX >= smallerBound));
		} while (hasMonster[posY][posX] == true);
	}
}
