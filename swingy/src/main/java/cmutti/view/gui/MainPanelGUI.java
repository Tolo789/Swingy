package cmutti.view.gui;

import cmutti.model.heroes.AHero;
import cmutti.view.IMainPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainPanelGUI extends JPanel implements IMainPanel {
	HeroPanelGUI heroPanel = null;
	MapPanelGUI mapPanel = null;
	StoryPanelGUI storyPanel = null;
	ChoicePanelGUI choicePanel = null;

	MainPanelGUI(AHero hero) {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		heroPanel = new HeroPanelGUI(hero);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		add(heroPanel, constraints);

		mapPanel = new MapPanelGUI();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 3;
		constraints.gridwidth = 3;
		JScrollPane scrollPane = new JScrollPane(mapPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		// scrollPane.setPreferredSize(new Dimension(640, 640));
		add(scrollPane, constraints);
		// add(mapPanel, constraints);

		storyPanel = new StoryPanelGUI();
		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridheight = 2;
		constraints.gridwidth = 1;
		constraints.weightx = 1;
		constraints.weighty = 2;
		add(storyPanel, constraints);

		choicePanel = new ChoicePanelGUI();
		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridheight = 1;
		constraints.weighty = 1;
		add(choicePanel, constraints);

	}

	private void AssignAllPanels() {
	}

}
