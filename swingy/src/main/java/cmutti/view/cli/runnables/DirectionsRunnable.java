package cmutti.view.cli.runnables;

import cmutti.controller.MainGame;

public class DirectionsRunnable extends AChoiceRunnable {
	void printLegend() {
		String legend = "Choose direction: (";
		for (int i = 0; i < MainGame.directions.length; i++) {
			if (i != 0)
				legend += ", ";
			legend += MainGame.directions[i];
		}
		legend += ")";
		System.out.println(legend);
	}

	boolean isValidAnswer() {
		System.out.println("validating..");
		for (int i = 0; i < MainGame.directions.length; i++) {
			if (answer.equals(MainGame.directions[i]))
				return true;
		}
		return false;
	}

	void redirectAnswer() {
		// System.out.println("redirect ! + " answer);
		swingy.getMainGame().directionChosen(answer);
	}
}
