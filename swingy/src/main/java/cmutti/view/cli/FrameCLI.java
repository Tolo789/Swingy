package cmutti.view.cli;

import cmutti.model.heroes.AHero;
import cmutti.view.IFrame;

public class FrameCLI implements IFrame {
	public ChoicePanelCLI choicePanel = null;
	public MainPanelCLI mainPanel = null;

	public FrameCLI() {
		choicePanel = new ChoicePanelCLI();
	}

	public void startMainPanel(AHero hero) {
		mainPanel = new MainPanelCLI(hero);
	}
}
