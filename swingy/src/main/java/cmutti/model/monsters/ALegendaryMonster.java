package cmutti.model.monsters;

public abstract class ALegendaryMonster extends AMonster {

	protected ALegendaryMonster(String name, int level, int posY, int posX, int artifactDropChance) {
		super(name, level, posY, posX, artifactDropChance);
	}

	// Define "standard" monster stats for common
	public int getBaseXp() {
		return 1000;
	}

	public int getBaseHp() {
		return 20;
	}

	public int getBaseAttack() {
		return 10;
	}

	public int getBaseDefense() {
		return 10;
	}

	public int getBaseAgility() {
		return 3;
	}

	public int getGrowthXp() {
		return 500;
	}

	// Artifacts drop chance by rarity
	public int rareArtifactChance() {
		return 100;
	}

	public int epicArtifactChance() {
		return 33;
	}
}
