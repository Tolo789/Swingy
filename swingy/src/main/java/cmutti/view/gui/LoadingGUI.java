package cmutti.view.gui;

import cmutti.view.IMapPanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingGUI extends JPanel {
	LoadingGUI() {
		JLabel label = new JLabel("Loading");
		add(label);
	}
}
