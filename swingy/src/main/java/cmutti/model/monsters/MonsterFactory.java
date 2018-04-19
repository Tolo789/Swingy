package cmutti.model.monsters;

import cmutti.controller.Swingy;
import cmutti.model.monsters.bulbasaur.Bulbasaur;
import cmutti.model.monsters.legendary.Mew;
import java.util.ArrayList;

public class MonsterFactory {
	private static ArrayList<Class<? extends AMonster>> commonMonsters;


	// Variable used each time we generate a monster list
	static ArrayList<AMonster> monsterList;
	static int heroLvl;
	static int monstersNbr;
	static int maxMonsters;
	static int minMonsters;
	static int percentMedium;
	static int percentHard;
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
		percentMedium = 10;
		percentHard = 0;
		hasMewTwo = false;
		hasMew = false;
		if (heroLvl < 5) {
			minMonsters = (int)(0.05 * monstersNbr);
			maxMonsters = (int)(0.07 * monstersNbr);
			percentMedium = 10;
			percentHard = 0;
		}
		else if (heroLvl < 10) {
			minMonsters = (int)(0.07 * monstersNbr);
			maxMonsters = (int)(0.09 * monstersNbr);
			percentMedium = 30;
			percentHard = 10;
		}
		else {
			minMonsters = (int)(0.09 * monstersNbr);
			maxMonsters = (int)(0.11 * monstersNbr);
			percentMedium = 50;
			percentHard = 20;
			hasMewTwo = true;
			if (heroLvl >= 15 && Swingy.getInstance().rand.nextInt(3) == 0) {
				hasMew = true;
				hasMewTwo = false;
			}
		}
		generateMonstersTypes();
		monstersNbr = Swingy.getInstance().rand.nextInt((maxMonsters - minMonsters) + 1) + minMonsters;
		percentMedium = percentMedium * monstersNbr / 100 ;
		percentHard = percentHard * monstersNbr / 100;
		System.out.println(minMonsters + "-" + maxMonsters + " => " + monstersNbr);

		// TODO: populate
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

		// Hard
		smallerBound = (mapSize / 2) - 8;
		biggerBound = (mapSize / 2) + 8;
		// for (int i = percentHard; i > 0; i--) {
		//
		// }

		// Medium
		smallerBound = (mapSize / 2) - 5;
		biggerBound = (mapSize / 2) + 5;
		// for (int i = percentMedium; i > 0; i--) {
		//
		// }

		// Common
		smallerBound = (mapSize / 2) - 2;
		biggerBound = (mapSize / 2) + 2;
		while (monsterList.size() < monstersNbr) {
			setMonsterPosition();
			AMonster newMonster = buildCommonMonster();
			monsterList.add(newMonster);
			hasMonster[posY][posX] = true;
		}

		return monsterList;
	}

	private static AMonster buildCommonMonster() {
		int idx = Swingy.getInstance().rand.nextInt(commonMonsters.size());
		AMonster newMonster = null;
        try
        {
			newMonster = commonMonsters.get(idx).getConstructor(int.class, int.class, int.class).newInstance(heroLvl, posY, posX);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return newMonster;
	}

	private static void generateMonstersTypes() {
		commonMonsters = new ArrayList<Class<? extends AMonster>>();
		if (heroLvl < 5) {
			commonMonsters.add(Bulbasaur.class);
		}
		else {
			commonMonsters.add(Mew.class);
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
