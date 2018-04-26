package cmutti.view.gui;

import cmutti.model.AMapElement;
import cmutti.model.heroes.AHero;
import cmutti.view.IFrame;
import javax.swing.JFrame;

public class FrameGUI extends JFrame implements IFrame {
	public MainPanelGUI mainPanel = null;
	LoadingGUI loadingPanel = null;

	public FrameGUI(String title) {
		setTitle(title);
		setSize(1100, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stop program from running when closing window
		// setResizable(false);
		setLocationRelativeTo(null);

		//add(new MyPanel());

		setVisible(true);

		loadingPanel = new LoadingGUI();
	}

	public void StartMainPanel(AHero hero) {
		add(loadingPanel);

		mainPanel = new MainPanelGUI(hero);
		add(mainPanel);

		remove(loadingPanel);
	}
}
