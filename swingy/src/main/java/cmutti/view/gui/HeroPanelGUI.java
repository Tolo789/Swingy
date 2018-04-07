package cmutti.view.gui;

import cmutti.view.IHeroPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeroPanelGUI extends JPanel implements IHeroPanel {
	HeroPanelGUI() {
		JLabel label = new JLabel("String");
		add(label);
	}
}
