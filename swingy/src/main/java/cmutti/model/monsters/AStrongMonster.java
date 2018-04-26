package cmutti.model.monsters;

public abstract class AStrongMonster extends AMonster {

	protected AStrongMonster(String name, int level, int posY, int posX, int artifactDropChance) {
		super(name, level, posY, posX, artifactDropChance);
	}

	// Define "standard" monster stats for common
	public int getBaseXp() {
		return 300;
	}

	public int getBaseHp() {
		return 14;
	}

	public int getBaseAttack() {
		return 7;
	}

	public int getBaseDefense() {
		return 7;
	}

	public int getBaseAgility() {
		return 2;
	}

	public int getGrowthXp() {
		return 90;
	}

	// Artifacts drop chance by rarity
	public int rareArtifactChance() {
		return 35;
	}

	public int epicArtifactChance() {
		return 10;
	}
}
