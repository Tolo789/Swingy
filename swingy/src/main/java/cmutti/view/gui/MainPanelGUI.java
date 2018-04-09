package cmutti.view.gui;

import cmutti.model.heroes.AHero;
import cmutti.view.IMainPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class MainPanelGUI extends JPanel implements IMainPanel {
	HeroPanelGUI heroPanel = null;
	MapPanelGUI mapPanel = null;
	StoryPanelGUI storyPanel = null;
	ChoicePanelGUI choicePanel = null;

	public void start(AHero hero) {
		AssignAllPanels();
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		heroPanel.updateInfo(hero);
		add(heroPanel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 3;
		constraints.gridwidth = 3;
		add(mapPanel, constraints);

		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridheight = 2;
		constraints.gridwidth = 1;
		constraints.weightx = 1;
		constraints.weighty = 2;
		add(storyPanel, constraints);

		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridheight = 1;
		constraints.weighty = 1;
		add(choicePanel, constraints);

	}

	void AssignAllPanels() {
		heroPanel = new HeroPanelGUI();
		mapPanel = new MapPanelGUI();
		storyPanel = new StoryPanelGUI();
		choicePanel = new ChoicePanelGUI();
	}

}
