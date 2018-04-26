package cmutti.model.landscape;

import cmutti.controller.Swingy;
import cmutti.model.AMapElement;
import java.util.ArrayList;

public class LandscapeFactory {
	static ArrayList<Class<? extends AMapElement>> landscapeClasses;
	static int posY;
	static int posX;
	static {
		landscapeClasses = new ArrayList<Class<? extends AMapElement>>();
		landscapeClasses.add(Stone.class);
		landscapeClasses.add(Tree.class);
	}

	public static AMapElement[][] addLandscape(AMapElement[][] mapElems) {
		int obstaclesNbr = mapElems.length * mapElems.length;
		int minObstacles = (int)(0.02 * obstaclesNbr);
		int maxObstacles = (int)(0.04 * obstaclesNbr);
		obstaclesNbr = Swingy.getInstance().rand.nextInt((maxObstacles - minObstacles) + 1) + minObstacles;

		for (int i = obstaclesNbr; i > 0; i--) {
			// Find suitable position
			do {
				posY = Swingy.getInstance().rand.nextInt(mapElems.length);
				posX = Swingy.getInstance().rand.nextInt(mapElems.length);
			} while (mapElems[posY][posX] != null);

			mapElems[posY][posX] = generateObstacle(mapElems);
		}

		return mapElems;
	}

	private static AMapElement generateObstacle(AMapElement[][] mapElems) {
		AMapElement newElem = null;
		int idx = Swingy.getInstance().rand.nextInt(landscapeClasses.size());

        try
        {
			newElem = landscapeClasses.get(idx).getConstructor(int.class, int.class).newInstance(posY, posX);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return newElem;
	}
}
