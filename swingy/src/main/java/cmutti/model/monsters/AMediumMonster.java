package cmutti.model.monsters;

public abstract class AMediumMonster extends AMonster {

	protected AMediumMonster(String name, int level, int posY, int posX, int artifactDropChance) {
		super(name, level, posY, posX, artifactDropChance);
	}

	// Define "standard" monster stats for common
	public int getBaseXp() {
		return 200;
	}

	public int getBaseHp() {
		return 12;
	}

	public int getBaseAttack() {
		return 6;
	}

	public int getBaseDefense() {
		return 6;
	}

	public int getBaseAgility() {
		return 1;
	}

	public int getGrowthXp() {
		return 70;
	}

	// Artifacts drop chance by rarity
	public int getRareArtifactChance() {
		return 23;
	}

	public int getEpicArtifactChance() {
		return 7;
	}
}
