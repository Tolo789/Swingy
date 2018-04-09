package cmutti.view.gui;

import cmutti.model.heroes.AHero;
import cmutti.view.IHeroPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeroPanelGUI extends JPanel implements IHeroPanel {
	JLabel name = new JLabel("Name");
	JLabel level = new JLabel("Level");
	JLabel life = new JLabel("Life");
	JLabel experience = new JLabel("Experience");
	JLabel coordinates = new JLabel("Coordinates");

	HeroPanelGUI() {
		add(name);
		add(level);
		add(life);
		add(experience);
		add(coordinates);
	}

	public void updateInfo(AHero hero) {
		name.setText("Name: " + hero.getName());
	}
}
