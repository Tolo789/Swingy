package cmutti.view.gui;

import cmutti.view.IChoicePanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoicePanelGUI extends JPanel implements IChoicePanel {
	String[] directions = new String[]{"North", "South", "West", "East"};
	JComboBox<String> dirCombo = new JComboBox<String>(directions);

	ChoicePanelGUI() {
		JLabel label = new JLabel("String");
		add(label);
		//add(dirCombo);
	    this.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLUE));
	}
}
