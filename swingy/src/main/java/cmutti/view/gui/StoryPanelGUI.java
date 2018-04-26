package cmutti.view.gui;

import cmutti.view.IStoryPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class StoryPanelGUI extends JPanel implements IStoryPanel {
	public JTextArea textArea = null;

	StoryPanelGUI() {
		textArea = new JTextArea(15, 12);
		textArea.setEditable(false);
		// textArea.setLineWrap(true);
		// textArea.setWrapStyleWord(true);

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;

		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, constraints);
	}
}
