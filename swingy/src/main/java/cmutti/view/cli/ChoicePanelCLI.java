package cmutti.view.cli;

import cmutti.controller.MainGame;
import cmutti.controller.Swingy;
import cmutti.view.IChoicePanel;
import cmutti.view.cli.runnables.ChoiceRunnable;

public class ChoicePanelCLI implements IChoicePanel {
	public static boolean waitingChoice = false;
	public static boolean inputByCLI = true;
	private static Swingy swingy = Swingy.getInstance();

	Thread choiceThread = null;

	ChoicePanelCLI() {
		choiceThread = new Thread(new ChoiceRunnable());
		choiceThread.start();
	}

	public static void printLegend() {
		if (swingy.getMainGame() == null) {
			return;
		}

		String legend = "";
		switch (swingy.getMainGame().getGameState()) {
			case WaitingDirectionChoice:
				legend = "Choose direction: (";
				for (int i = 0; i < MainGame.directions.length; i++) {
					if (i != 0)
						legend += ", ";
					legend += MainGame.directions[i];
				}
				legend += ")";
				break;
			default:
				break;
		}

		if (!legend.equals(""))
			System.out.println(legend);
	}

	public void showDirectionChoices() {
		waitingChoice = true;

		if (!inputByCLI) {
			printLegend();
		}
		inputByCLI = false;
	}

	public void stopDirectionChoices(String direction) {
		waitingChoice = false;
		if (!inputByCLI) {
			System.out.println(direction);
		}
		System.out.println("");
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
