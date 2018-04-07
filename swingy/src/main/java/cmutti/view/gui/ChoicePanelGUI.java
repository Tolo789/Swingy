package cmutti.view.gui;

import cmutti.view.IChoicePanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoicePanelGUI extends JPanel implements IChoicePanel {
	ChoicePanelGUI() {
	    this.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLUE));
		JLabel label = new JLabel("String");
		add(label);
	}
}
