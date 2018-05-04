package cmutti.view.cli;

import cmutti.controller.MainGame;
import cmutti.controller.Swingy;
import cmutti.model.heroes.AHero;
import cmutti.view.IChoicePanel;
import cmutti.view.ISelectionPanel;
import cmutti.view.cli.runnables.ChoiceRunnable;

public class ChoicePanelCLI implements ISelectionPanel, IChoicePanel {
	public static boolean waitingChoice = false;
	public static boolean inputByCLI = false;
	public static boolean selectingHero = true;
	private static Swingy swingy = Swingy.getInstance();

	Thread choiceThread = null;

	ChoicePanelCLI() {
		choiceThread = new Thread(new ChoiceRunnable());
		choiceThread.start();
	}

// --- Legend llogic -----------------------------------------------------------
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
					legend += (i + 1) + " for " + MainGame.directions[i];
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

// --- Validation logic --------------------------------------------------------
	public static boolean isValidAnswer(String answer) {
		if (!waitingChoice)
			return false;

		switch (swingy.getMainGame().getGameState()) {
			case WaitingDirectionChoice:
				for (int i = MainGame.directions.length; i > 0; i--) {
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

// --- Redirect logic ----------------------------------------------------------
	public static void redirectAnswer(String answer) {
		if (!waitingChoice)
			return;

		switch (swingy.getMainGame().getGameState()) {
			case WaitingDirectionChoice:
				swingy.getMainGame().directionChosen(Integer.parseInt(answer) - 1);
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

// --- Hero selector funcs -----------------------------------------------------
	public void updateSelectionMode(String[] comboLabels, boolean creatingNew, AHero hero) {

	}

	public void updateHeroSelected(AHero hero) {

	}

// --- Main game funcs ---------------------------------------------------------
	public void showDirectionChoices() {
		printLegend();
		waitingChoice = true;
		inputByCLI = false;
	}

	public void stopDirectionChoice(int choice) {
		waitingChoice = false;
		if (!inputByCLI) {
			System.out.println(choice + 1);
		}
		System.out.println("");
	}

	public void showFightChoices() {
		printLegend();
		waitingChoice = true;
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
		printLegend();
		inputByCLI = false;
		waitingChoice = true;
	}

	public void stopArtifactChoice(String choice) {
		waitingChoice = false;
		if (!inputByCLI) {
			System.out.println(choice);
		}
		System.out.println("");
	}
}
