package cmutti.view.cli;

import cmutti.model.heroes.AHero;
import cmutti.view.IMainPanel;

public class MainPanelCLI implements IMainPanel {
	public HeroPanelCLI heroPanel = null;
	public StoryPanelCLI storyPanel = null;

	MainPanelCLI(AHero hero) {
		heroPanel = new HeroPanelCLI(hero);

		storyPanel = new StoryPanelCLI();
	}
}
