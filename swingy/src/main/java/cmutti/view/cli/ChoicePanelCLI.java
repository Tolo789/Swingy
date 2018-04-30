package cmutti.view.cli;

import cmutti.controller.Swingy;
import cmutti.view.IChoicePanel;
import cmutti.view.cli.runnables.DirectionsRunnable;

public class ChoicePanelCLI implements IChoicePanel {
	private static Swingy swingy = Swingy.getInstance();

	Thread directionsThread = null;

	ChoicePanelCLI() {
	}

	public void showDirectionChoices() {
		directionsThread = new Thread(new DirectionsRunnable());
		directionsThread.start();
	}

	public void stopDirectionChoices() {
		System.out.println("interrupting..");
		directionsThread.interrupt();
		// directionsThread.stop();
	}

	public void showFightChoices() {
		System.out.println("Fight or flee ?");
		// 		swingy.getMainGame().fightDecision(false);
	}

	public void showArtifactChoices() {
		System.out.println("Equip or cancel ?");
		// 		swingy.getMainGame().artifactDecision(false);
	}
}
