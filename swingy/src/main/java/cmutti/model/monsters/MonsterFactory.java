package cmutti.model.monsters;

import cmutti.controller.Swingy;
import cmutti.model.monsters.legendary.Mew;
import java.util.ArrayList;

import cmutti.controller.Swingy;

public class MonsterFactory {
	public static ArrayList<AMonster> generateMonsterList(int heroLvl, int mapSize) {
		ArrayList<AMonster> monsterList = new ArrayList<AMonster>();
		if (heroLvl == 1) { // only display Mew if hero is lvl 1
			monsterList.add(setLegendaryPosition(new Mew(1), mapSize));

			return monsterList;
		}

		int monstersNbr = (mapSize * mapSize);
		int maxMonsters;
		int minMonsters;
		int percentHard = 10;
		int percentSuperHard = 0;
		boolean hasMewTwo = false;
		boolean hasMew = false;
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
		System.out.println(minMonsters + "-" + maxMonsters + " => " + monstersNbr);
		// TODO: populate
		return monsterList;
	}

	private static AMonster setLegendaryPosition(AMonster monster, int mapSize) {
		int pos = Swingy.getInstance().rand.nextInt(4);
		int posX;
		int posY;

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


		monster.setPosition(posY, posX);
		return monster;
	}
}
