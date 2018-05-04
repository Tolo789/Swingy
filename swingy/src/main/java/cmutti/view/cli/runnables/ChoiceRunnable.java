package cmutti.view.cli.runnables;

import cmutti.controller.MainGame;
import cmutti.controller.Swingy;
import cmutti.view.cli.ChoicePanelCLI;
import java.util.Scanner;

public class ChoiceRunnable implements Runnable {
	ChoicePanelCLI choicePanel = null;
	Scanner scanner = new Scanner(System.in);
	String answer = null;
	boolean firstRun = true;

	public ChoiceRunnable (ChoicePanelCLI choicePanel) {
		super();
		this.choicePanel = choicePanel;
	}

	public void run() {
		do {
			try {
				firstRun = true;
				do {
					while (!choicePanel.isWaitingChoice()) {
						Thread.sleep(100);
					}

					if (firstRun)
						firstRun = false;
					else
						choicePanel.printLegend();
					answer = scanner.nextLine();
				} while (!choicePanel.isValidAnswer(answer));

				choicePanel.redirectAnswer(answer);
			}
			catch (Exception e) {}
		} while (!Thread.currentThread().isInterrupted());
	}
}
