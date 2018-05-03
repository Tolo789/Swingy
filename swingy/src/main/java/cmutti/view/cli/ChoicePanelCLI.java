package cmutti.view.cli;

import cmutti.controller.MainGame;
import cmutti.controller.Swingy;
import cmutti.view.IChoicePanel;
import cmutti.view.cli.runnables.ChoiceRunnable;

public class ChoicePanelCLI implements IChoicePanel {
	public static boolean waitingChoice = false;
	public static boolean inputByCLI = false;
	private static Swingy swingy = Swingy.getInstance();

	Thread choiceThread = null;

	ChoicePanelCLI() {
		choiceThread = new Thread(new ChoiceRunnable());
		choiceThread.start();
	}

	public static void printLegend() {
		if (swingy.getMainGame() == null) {
			System.out.println("Main game not ready yet");
			return;
		}

		String legend = "";
		switch (swingy.getMainGame().getGameState()) {
			case WaitingDirectionChoice:
				legend = "Choose direction: ";
				for (int i = 0; i < MainGame.directions.length; i++) {
					if (i != 0)
						legend += ", ";
					legend += i + " for " + MainGame.directions[i];
				}
				break;
			case WaitingFightChoice:
				legend = "1 for Fight, 2 for Flee";
				break;
			case WaitingArtifactChoice:
				legend = "1 to Equip new, 2 to Keep old";
				break;
			default:
				legend = "You missed smth..";
				break;
		}

		if (!legend.equals(""))
			System.out.println(legend);
	}

	public void showDirectionChoices() {
		waitingChoice = true;

		printLegend();
		inputByCLI = false;
	}

	public void stopDirectionChoice(int choice) {
		waitingChoice = false;
		if (!inputByCLI) {
			System.out.println(choice);
		}
		System.out.println("");
	}

	public void showFightChoices() {
		waitingChoice = true;

		printLegend();
		inputByCLI = false;
	}

	public void stopFightChoice(String choice) {
		waitingChoice = false;
		if (!inputByCLI) {
			System.out.println(choice);
		}
		System.out.println("");
	}

	public void showArtifactChoices() {
		waitingChoice = true;

		printLegend();
		inputByCLI = false;
	}

	public void stopArtifactChoice(String choice) {
		waitingChoice = false;
		if (!inputByCLI) {
			System.out.println(choice);
		}
		System.out.println("");
	}
}
