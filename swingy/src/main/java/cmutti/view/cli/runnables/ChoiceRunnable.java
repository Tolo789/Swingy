package cmutti.view.cli.runnables;

import cmutti.controller.MainGame;
import cmutti.controller.Swingy;
import cmutti.view.cli.ChoicePanelCLI;
import java.util.Scanner;

public class ChoiceRunnable implements Runnable {
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
				} while (!ChoicePanelCLI.isValidAnswer(answer));

				ChoicePanelCLI.inputByCLI = true;
				ChoicePanelCLI.redirectAnswer(answer);
			}
			catch (Exception e) {}
		} while (!Thread.currentThread().isInterrupted());
	}
}
