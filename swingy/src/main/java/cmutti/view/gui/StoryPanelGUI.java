package cmutti.view.gui;

import cmutti.view.IStoryPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StoryPanelGUI extends JPanel implements IStoryPanel {
	StoryPanelGUI() {
		JLabel label = new JLabel("String");
		add(label);
	}
}
