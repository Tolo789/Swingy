package cmutti.model.artifacts;

import cmutti.model.heroes.AHero;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "artifacts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "aartifact")
public abstract class AArtifact {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int id;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "heroes_id")
	AHero owner = null;

	@Transient
	protected BufferedImage img = null;
	@NotNull
    @Size(min = 1, max = 20)
	@Pattern(regexp = "^\\p{Alnum}+(?: \\p{Alnum}+)*$", message = "Only alphanum char with non-trailing spaces are allowed")
	protected String name = "";
	@Min(0)
	protected int level = 0;
	@Min(0)
	protected int attack = 0;
	@Min(0)
	protected int defense = 0;
	@Min(0)
	protected int hp = 0;

	protected AArtifact(String name, int level) {
		try {
			img = ImageIO.read(new File(getSpritePath()));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.level = level;

		attack = getBaseAttack() + getGrowthAttack() * level;
		defense = getBaseDefense() + getGrowthDefense() * level;
		hp = getBaseHp() + getGrowthHp() * level;
	}

	public abstract String getPresentation();
	public abstract String getBonusPresentation();

	protected String getSpritePath() {
		return "sprites/test.png";
	}

	public int getBaseHp() {
		return 0;
	}

	public int getBaseAttack() {
		return 0;
	}

	public int getBaseDefense() {
		return 0;
	}

	public int getGrowthHp() {
		return 0;
	}

	public int getGrowthAttack() {
		return 0;
	}

	public int getGrowthDefense() {
		return 0;
	}
}
