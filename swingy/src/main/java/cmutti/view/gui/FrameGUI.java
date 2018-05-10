package cmutti.view.gui;

import cmutti.model.heroes.AHero;
import cmutti.view.IFrame;
import java.util.ArrayList;
import javax.swing.JFrame;

public class FrameGUI extends JFrame implements IFrame {
	public final static int FRAME_WIDTH = 1100;
	public final static int FRAME_HEIGHT = 600;
	public SelectionPanelGUI selectionPanel = null;
	public MainPanelGUI mainPanel = null;
	LoadingGUI loadingPanel = null;

	public FrameGUI(String title) {
		setTitle(title);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stop program from running when closing window
		// setResizable(false);
		setLocationRelativeTo(null);

		//add(new MyPanel());

		setVisible(true);

		loadingPanel = new LoadingGUI();
	}

	public void startSelectionPanel() {
		add(loadingPanel);

		selectionPanel = new SelectionPanelGUI();
		add(selectionPanel);

		remove(loadingPanel);
	}

	public void startMainPanel(AHero hero) {
		remove(selectionPanel);
		add(loadingPanel);

		mainPanel = new MainPanelGUI(hero);
		add(mainPanel);

		remove(loadingPanel);
	}
}
