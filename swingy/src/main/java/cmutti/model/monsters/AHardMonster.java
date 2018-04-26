package cmutti.model.monsters;

public abstract class AHardMonster extends AMonster {

	protected AHardMonster(String name, int level, int posY, int posX, int artifactDropChance) {
		super(name, level, posY, posX, artifactDropChance);
	}

	// Define "standard" monster stats for common
	public int getBaseXp() {
		return 500;
	}

	public int getBaseHp() {
		return 16;
	}

	public int getBaseAttack() {
		return 8;
	}

	public int getBaseDefense() {
		return 8;
	}

	public int getBaseAgility() {
		return 2;
	}

	public int getGrowthXp() {
		return 110;
	}

	// Artifacts drop chance by rarity
	public int rareArtifactChance() {
		return 60;
	}

	public int epicArtifactChance() {
		return 20;
	}
}
