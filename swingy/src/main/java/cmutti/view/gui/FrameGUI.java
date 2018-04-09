package cmutti.view.gui;

import cmutti.model.heroes.AHero;
import cmutti.view.IFrame;
import javax.swing.JFrame;

public class FrameGUI extends JFrame implements IFrame {
	MainPanelGUI mainPanel = null;

	public FrameGUI(String title) {
		setTitle(title);
		setSize(1100, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stop program from running when closing window
		setResizable(false);
		setLocationRelativeTo(null);

		AssignAllPanels();

		//add(new MyPanel());

		setVisible(true);
	}

	void AssignAllPanels() {
		mainPanel = new MainPanelGUI();
	}

	public void StartMainPanel(AHero hero) {
		mainPanel.start(hero);
		add(mainPanel);
	}
}
