package cmutti.view.cli;

import cmutti.view.IStoryPanel;

public class StoryPanelCLI implements IStoryPanel {

	StoryPanelCLI() {
	}

	public void addText(String newText) {
		System.out.print(newText);
	}
}
