package cmutti.model.monsters;

import cmutti.controller.Swingy;
import cmutti.model.monsters.legendary.Mew;
import java.util.ArrayList;

public class MonsterFactory {
	// Variable used each time we generate a monster list
	static ArrayList<AMonster> monsterList;
	static int monstersNbr;
	static int maxMonsters;
	static int minMonsters;
	static int percentHard;
	static int percentSuperHard;
	static int mapSize;
	static int posX;
	static int posY;
	static boolean hasMewTwo;
	static boolean hasMew;

	public static ArrayList<AMonster> generateMonsterList(int heroLvl, int size) {
		mapSize = size;
		monsterList = new ArrayList<AMonster>();

		// only display fleeing Mew if hero is lvl 1
		if (heroLvl == 1) {
			setLegendaryPosition();
			monsterList.add(new Mew(1, posY, posX));

			return monsterList;
		}

		monstersNbr = (mapSize * mapSize);
		percentHard = 10;
		percentSuperHard = 0;
		hasMewTwo = false;
		hasMew = false;
		if (heroLvl < 5) {
			monstersNbr = (int)(0.1 * monstersNbr);
			minMonsters = (int)(0.7 * monstersNbr);
			maxMonsters = (int)(1.2 * monstersNbr);
			percentHard = 10;
			percentSuperHard = 0;
		}
		else if (heroLvl < 10) {
			monstersNbr = (int)(0.15 * monstersNbr);
			minMonsters = (int)(0.8 * monstersNbr);
			maxMonsters = (int)(1.3 * monstersNbr);
			percentHard = 30;
			percentSuperHard = 10;
		}
		else {
			monstersNbr = (int)(0.2 * monstersNbr);
			minMonsters = monstersNbr;
			maxMonsters = (int)(1.5 * monstersNbr);
			percentHard = 50;
			percentSuperHard = 10;
			hasMewTwo = true;
			if (heroLvl >= 15 && Swingy.getInstance().rand.nextInt(3) == 0) {
				hasMew = true;
				hasMewTwo = false;
			}
		}
		monstersNbr = Swingy.getInstance().rand.nextInt((maxMonsters - minMonsters) + 1) + minMonsters;
		percentHard = monstersNbr * 100 / percentHard;
		percentSuperHard = monstersNbr * 100 / percentSuperHard;
		System.out.println(minMonsters + "-" + maxMonsters + " => " + monstersNbr);

		// TODO: populate
		boolean[][] hasMonster = new boolean[mapSize][mapSize];
		// while (monsterList.size() < monstersNbr) {
		// 	monsterList.Add(new Bulbasaur(1));
		// }
		return monsterList;
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
}
