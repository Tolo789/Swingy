package cmutti.model.artifacts;

import cmutti.controller.Swingy;
import cmutti.model.artifacts.weapons.*;
import cmutti.model.artifacts.helms.*;
import cmutti.model.artifacts.armors.*;
import cmutti.model.monsters.AMonster;
import java.util.ArrayList;

public class ArtifactBuilder {
	static ArrayList<Class<? extends AArtifact>> commonArtifacts;
	static ArrayList<Class<? extends AArtifact>> rareArtifacts;
	static ArrayList<Class<? extends AArtifact>> epicArtifacts;
	static {
		commonArtifacts = new ArrayList<Class<? extends AArtifact>>();
		commonArtifacts.add(FishingRope.class);
		commonArtifacts.add(ChefHat.class);
		commonArtifacts.add(SantaHat.class);
		commonArtifacts.add(Belt.class);

		rareArtifacts = new ArrayList<Class<? extends AArtifact>>();
		rareArtifacts.add(Guitar.class);
		rareArtifacts.add(Crown.class);
		rareArtifacts.add(CowboyHat.class);
		rareArtifacts.add(BlackCap.class);

		epicArtifacts = new ArrayList<Class<? extends AArtifact>>();
		epicArtifacts.add(Pan.class);
		epicArtifacts.add(StrawHat.class);
		epicArtifacts.add(VikingHelm.class);
		epicArtifacts.add(HawaianSkirt.class);
	}

	public static AArtifact getDroppedArtifact(AMonster monster) {
		AArtifact artifact = null;

		if (Swingy.getInstance().rand.nextInt(100) < monster.getArtifactDropChance()) {
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
