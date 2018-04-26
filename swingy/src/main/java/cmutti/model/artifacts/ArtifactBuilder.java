package cmutti.model.artifacts;

import cmutti.controller.Swingy;
import cmutti.model.artifacts.weapons.FishingRope;
import cmutti.model.artifacts.weapons.Guitar;
import cmutti.model.artifacts.weapons.Pan;
import cmutti.model.monsters.AMonster;
import java.util.ArrayList;

public class ArtifactBuilder {
	static ArrayList<Class<? extends AArtifact>> commonArtifacts;
	static ArrayList<Class<? extends AArtifact>> rareArtifacts;
	static ArrayList<Class<? extends AArtifact>> epicArtifacts;
	static {
		commonArtifacts = new ArrayList<Class<? extends AArtifact>>();
		commonArtifacts.add(FishingRope.class);

		rareArtifacts = new ArrayList<Class<? extends AArtifact>>();
		rareArtifacts.add(Guitar.class);

		epicArtifacts = new ArrayList<Class<? extends AArtifact>>();
		epicArtifacts.add(Pan.class);
	}

	public static AArtifact getDroppedArtifact(AMonster monster) {
		AArtifact artifact = null;

		// TODO: put percent back to 100
		if (Swingy.getInstance().rand.nextInt(1) < monster.getArtifactDropChance()) {
			int randomNbr = Swingy.getInstance().rand.nextInt(100);
			if (randomNbr < monster.getEpicArtifactChance()) {
				artifact = buildArtifact(epicArtifacts, monster.getLevel());
			}
			else if (randomNbr < monster.getRareArtifactChance()) {
				artifact = buildArtifact(rareArtifacts, monster.getLevel());
			}
			else {
				artifact = buildArtifact(commonArtifacts, monster.getLevel());
			}
		}

		return artifact;
	}

	private static AArtifact buildArtifact(ArrayList<Class<? extends AArtifact>> artifactList, int monsterLvl) {
		int idx = Swingy.getInstance().rand.nextInt(artifactList.size());
		AArtifact newArtifact = null;
        try
        {
			newArtifact = artifactList.get(idx).getConstructor(int.class).newInstance(monsterLvl);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return newArtifact;
	}
}
