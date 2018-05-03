package cmutti.view.cli.runnables;

import cmutti.controller.MainGame;
import cmutti.controller.Swingy;
import cmutti.view.cli.ChoicePanelCLI;
import java.util.Scanner;

public class ChoiceRunnable implements Runnable {
	protected static Swingy swingy = Swingy.getInstance();

	Scanner scanner = new Scanner(System.in);
	String answer = null;
	boolean firstRun = true;

	public void run() {
		do {
			try {
				firstRun = true;
				do {
					while (!ChoicePanelCLI.waitingChoice) {
						Thread.sleep(100);
					}

					if (firstRun)
						firstRun = false;
					else
						ChoicePanelCLI.printLegend();
					answer = scanner.nextLine();
				} while (!isValidAnswer());

				ChoicePanelCLI.inputByCLI = true;
				redirectAnswer();
			}
			catch (Exception e) {}
		} while (!Thread.currentThread().isInterrupted());
	}

	boolean isValidAnswer() {
		if (!ChoicePanelCLI.waitingChoice)
			return false;

		switch (swingy.getMainGame().getGameState()) {
			case WaitingDirectionChoice:
				for (int i = 0; i < MainGame.directions.length; i++) {
					if (answer.equals(i + ""))
						return true;
				}
				return false;
			case WaitingFightChoice:
				if (answer.equals("1") || answer.equals("2"))
					return true;
				return false;
			case WaitingArtifactChoice:
				if (answer.equals("1") || answer.equals("2"))
					return true;
				return false;
			default:
				return true;
		}
	}

	void redirectAnswer() {
		if (!ChoicePanelCLI.waitingChoice)
			return;

		switch (swingy.getMainGame().getGameState()) {
			case WaitingDirectionChoice:
				swingy.getMainGame().directionChosen(Integer.parseInt(answer));
				break;
			case WaitingFightChoice:
				swingy.getMainGame().fightDecision(answer.equals("1"));
				break;
			case WaitingArtifactChoice:
				swingy.getMainGame().artifactDecision(answer.equals("1"));
				break;
			default:
				break;
		}
	}
}
