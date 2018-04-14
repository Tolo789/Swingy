package cmutti.model.monsters;

import cmutti.model.monsters.legendary.Mew;
import cmutti.controller.Swingy;

public class MonsterFactory {
	public static AMonster[] generateMonsterList(int heroLvl, int mapSize) {
		if (heroLvl == 1) { // only display Mew if hero is lvl 1
			AMonster[] monsterList = new AMonster[1];
			// TODO: populate
			monsterList[0] = new Mew(1);
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
		AMonster[] monsterList = new AMonster[monstersNbr];
		// TODO: populate
		return monsterList;
	}
}
