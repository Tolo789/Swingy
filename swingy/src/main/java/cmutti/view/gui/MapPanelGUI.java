package cmutti.view.gui;

import cmutti.view.IMapPanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapPanelGUI extends JPanel implements IMapPanel {
	MapPanelGUI() {
	    this.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.BLUE));
		JLabel label = new JLabel("String");
		add(label);
	}
}
