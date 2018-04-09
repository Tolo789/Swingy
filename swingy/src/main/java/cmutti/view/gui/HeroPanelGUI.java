package cmutti.view.gui;

import cmutti.model.heroes.AHero;
import cmutti.view.IHeroPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeroPanelGUI extends JPanel implements IHeroPanel {
	protected AHero hero = null;

	JLabel name = new JLabel("");
	JLabel level = new JLabel("");
	JLabel life = new JLabel("");
	JLabel experience = new JLabel("");
	JLabel coordinates = new JLabel("");
	SpritePanel sprite = null;

	HeroPanelGUI(AHero hero) {
		this.hero = hero;

		sprite = new SpritePanel(hero.spritePath);
		updateInfo();

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(sprite);
		add(name);
		add(level);
		add(life);
		add(experience);
		add(coordinates);
	}

	public void updateInfo() {
		name.setText("Name: " + hero.getName());
		level.setText("Level: " + hero.getLevel());
		life.setText("Life: " + hero.getHp() + "/" + hero.getMaxHp());
		experience.setText("Experience: " + hero.getXp() + "/" + hero.getNeededXp());
		coordinates.setText("Coordinates: " + hero.getPosX() + "-" + hero.getPosY());
	}
}
