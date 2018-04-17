package cmutti.view.gui;

import cmutti.model.heroes.AHero;
import cmutti.view.IHeroPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeroPanelGUI extends JPanel implements IHeroPanel {
	protected AHero hero = null;

	SpritePanel sprite = null;
	JLabel name = new JLabel("");
	JLabel level = new JLabel("");
	JLabel experience = new JLabel("");
	JLabel life = new JLabel("");
	JLabel attack = new JLabel("");
	JLabel defense = new JLabel("");
	JLabel agility = new JLabel("");
	JLabel coordinates = new JLabel("");

	HeroPanelGUI(AHero hero) {
		this.hero = hero;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		sprite = new SpritePanel(hero.getMainSprite(), null);
		name.setText("Name: " + hero.getName());
		update();

		add(sprite);
		add(name);
		add(level);
		add(experience);
		add(life);
		add(attack);
		add(defense);
		add(agility);
		add(coordinates);
	}

	public void update() {
		level.setText("Lvl.: " + hero.getLevel());
		experience.setText("Xp: " + hero.getXp() + "/" + hero.getNeededXp());
		life.setText("Hp: " + hero.getHp() + "/" + hero.getMaxHp());
		attack.setText("Attack: " + hero.getAttack());
		defense.setText("Defense: " + hero.getDefense());
		agility.setText("Agility: " + hero.getAgility());
		coordinates.setText("Coordinates: " + hero.getPosX() + "-" + hero.getPosY());
	}
}
