package cmutti.view.gui;

import cmutti.model.heroes.AHero;
import cmutti.view.IHeroPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeroPanelGUI extends JPanel implements IHeroPanel {
	protected AHero hero = null;

	SpritePanel sprite = null;

	JPanel statsContainer = new JPanel();
	JLabel name = new JLabel("");
	JLabel level = new JLabel("");
	JLabel experience = new JLabel("");
	JLabel life = new JLabel("");
	JLabel attack = new JLabel("");
	JLabel defense = new JLabel("");
	JLabel agility = new JLabel("");
	JLabel coordinates = new JLabel("");

	JPanel artifactsContainer = new JPanel();
	ItemPanelGUI helm = new ItemPanelGUI(null, "No helm equipped");
	ItemPanelGUI armor = new ItemPanelGUI(null, "No armor equipped");
	ItemPanelGUI weapon = new ItemPanelGUI(null, "No weapon equipped");

	HeroPanelGUI(AHero hero) {
		this.hero = hero;

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;

		sprite = new SpritePanel(hero.getMainSprite(), null);
		name.setText("Name: " + hero.getName());
		update();

		add(sprite, constraints);

		statsContainer.setLayout(new BoxLayout(statsContainer, BoxLayout.PAGE_AXIS));
		name.setAlignmentX(CENTER_ALIGNMENT);
		statsContainer.add(name);
		level.setAlignmentX(CENTER_ALIGNMENT);
		statsContainer.add(level);
		experience.setAlignmentX(CENTER_ALIGNMENT);
		statsContainer.add(experience);
		life.setAlignmentX(CENTER_ALIGNMENT);
		statsContainer.add(life);
		attack.setAlignmentX(CENTER_ALIGNMENT);
		statsContainer.add(attack);
		defense.setAlignmentX(CENTER_ALIGNMENT);
		statsContainer.add(defense);
		agility.setAlignmentX(CENTER_ALIGNMENT);
		statsContainer.add(agility);
		coordinates.setAlignmentX(CENTER_ALIGNMENT);
		statsContainer.add(coordinates);
		constraints.gridy = 1;
		add(statsContainer, constraints);

		artifactsContainer.setLayout(new BoxLayout(artifactsContainer, BoxLayout.PAGE_AXIS));
		helm.setAlignmentX(CENTER_ALIGNMENT);
		artifactsContainer.add(helm);
		armor.setAlignmentX(CENTER_ALIGNMENT);
		artifactsContainer.add(armor);
		weapon.setAlignmentX(CENTER_ALIGNMENT);
		artifactsContainer.add(weapon);
		constraints.gridy = 2;
		add(artifactsContainer, constraints);
	}

	public void update() {
		level.setText("Lvl.: " + hero.getLevel());
		experience.setText("Xp: " + hero.getXp() + "/" + hero.getNeededXp());
		life.setText("Hp: " + hero.getHp() + "/" + hero.getMaxHp());
		attack.setText("Attack: " + hero.getAttack());
		defense.setText("Defense: " + hero.getDefense());
		agility.setText("Agility: " + hero.getAgility());
		coordinates.setText("Coordinates: " + hero.getPosX() + "-" + hero.getPosY());


		if (hero.getHelm() != null) {
			helm.update(hero.getHelm().getImg(), hero.getHelm().getBonusPresentation());
		}

		if (hero.getArmor() != null) {
			armor.update(hero.getArmor().getImg(), hero.getArmor().getBonusPresentation());
		}

		if (hero.getWeapon() != null) {
			weapon.update(hero.getWeapon().getImg(), hero.getWeapon().getBonusPresentation());
		}
	}
}
