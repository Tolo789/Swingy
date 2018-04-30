package cmutti.view.cli;

import cmutti.model.heroes.AHero;
import cmutti.view.IFrame;

public class FrameCLI implements IFrame {
	public MainPanelCLI mainPanel = null;

	public FrameCLI() {
	}

	public void StartMainPanel(AHero hero) {
		mainPanel = new MainPanelCLI(hero);
	}
}
