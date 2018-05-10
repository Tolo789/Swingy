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
	public HeroPanelGUI heroPanel = null;
	public MapPanelGUI mapPanel = null;
	public StoryPanelGUI storyPanel = null;
	public ChoicePanelGUI choicePanel = null;

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
		heroPanel.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, FrameGUI.FRAME_HEIGHT));
		heroPanel.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, FrameGUI.FRAME_HEIGHT));
		add(heroPanel, constraints);

		mapPanel = new MapPanelGUI();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 3;
		constraints.gridwidth = 3;
		mapPanel.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT));
		mapPanel.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT));
		mapPanel.setMaximumSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT));
		add(mapPanel, constraints);

		storyPanel = new StoryPanelGUI();
		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridheight = 2;
		constraints.gridwidth = 1;
		constraints.weightx = 1;
		constraints.weighty = 2;
		storyPanel.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, 2 * FrameGUI.FRAME_HEIGHT / 3));
		storyPanel.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, 2 * FrameGUI.FRAME_HEIGHT / 3));
		storyPanel.setMaximumSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, 2 * FrameGUI.FRAME_HEIGHT / 3));
		add(storyPanel, constraints);

		choicePanel = new ChoicePanelGUI();
		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridheight = 1;
		constraints.weighty = 1;
		choicePanel.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, FrameGUI.FRAME_HEIGHT / 3));
		choicePanel.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, FrameGUI.FRAME_HEIGHT / 3));
		choicePanel.setMaximumSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, FrameGUI.FRAME_HEIGHT / 3));
		add(choicePanel, constraints);

	}
}
