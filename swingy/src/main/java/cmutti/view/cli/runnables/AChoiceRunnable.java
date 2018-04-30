package cmutti.view.cli.runnables;

import cmutti.controller.Swingy;
import java.util.Scanner;

public abstract class AChoiceRunnable implements Runnable {
	protected static Swingy swingy = Swingy.getInstance();

	Scanner scannerIn = new Scanner(System.in);
	String answer = null;

	public void run() {
		do {
			printLegend();
			answer = scannerIn.nextLine();
		} while (!isValidAnswer());

		redirectAnswer();
	}

	abstract void printLegend();
	abstract boolean isValidAnswer();
	abstract void redirectAnswer();
}
