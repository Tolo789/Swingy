package cmutti.view.cli.runnables;

import cmutti.controller.MainGame;
import cmutti.controller.Swingy;
import cmutti.view.cli.ChoicePanelCLI;
import java.util.Scanner;

public class ChoiceRunnable implements Runnable {
	protected static Swingy swingy = Swingy.getInstance();

	Scanner scanner = new Scanner(System.in);
	String answer = null;

	public void run() {
		do {
			try {
				do {
					while (!ChoicePanelCLI.waitingChoice) {
						Thread.sleep(100);
					}

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
					if (answer.equals(MainGame.directions[i]))
						return true;
				}
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
				swingy.getMainGame().directionChosen(answer);
				break;
			default:
				break;
		}
	}
}
