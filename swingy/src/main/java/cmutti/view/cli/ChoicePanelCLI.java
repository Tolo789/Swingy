package cmutti.view.cli;

import cmutti.controller.MainGame;
import cmutti.controller.Swingy;
import cmutti.model.heroes.AHero;
import cmutti.view.IChoicePanel;
import cmutti.view.ISelectionPanel;
import cmutti.view.cli.runnables.ChoiceRunnable;
import lombok.Getter;

public class ChoicePanelCLI implements ISelectionPanel, IChoicePanel {
	// Controller ref
	Swingy swingy = Swingy.getInstance();

	@Getter boolean waitingChoice = false;
	boolean inputByCLI = false;
	boolean selectingHero = true;

	// Vars used only for Hero selection
	boolean creatingNew = false;
	boolean needConfirm = false;
	String[] selectionLabels = null;

	Thread choiceThread = null;

	ChoicePanelCLI() {
		choiceThread = new Thread(new ChoiceRunnable(this));
		choiceThread.start();
	}

// --- Legend llogic -----------------------------------------------------------
	public void printLegend() {
		String legend = "";

		if (selectingHero) {
			if (needConfirm) {
				legend = "Type any";
				legend += (creatingNew) ? " name" : "thing";
				legend += " to Confirm, empty line to Change";
			}
			else {
				for (int i = 0; i < selectionLabels.length; i++) {
					if (i != 0)
						legend += ", ";
					legend += (i + 1) + " for " + selectionLabels[i];
				}

				legend += ", 0 to ";
				if (!creatingNew) {
					legend += "Create New Hero";
				}
				else {
					legend += "Load Saved Heroes";
				}
			}
		}
		else if (swingy.getMainGame() != null) {
			switch (swingy.getMainGame().getGameState()) {
				case WaitingDirectionChoice:
					legend = "Choose direction: ";
					for (int i = 0; i < MainGame.directions.length; i++) {
						if (i != 0)
							legend += ", ";
						legend += (i + 1) + " for " + MainGame.directions[i];
					}
					legend += ", 0 to Exit Game";
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
		}

		if (!legend.equals(""))
			System.out.println(legend);
	}

// --- Validation logic --------------------------------------------------------
	public boolean isValidAnswer(String answer) {
		if (!waitingChoice || answer == null)
			return false;

		if (selectingHero) {
			if (needConfirm) {
				return true;
			}
			else {
				for (int i = 0; i <= selectionLabels.length; i++) {
					if (answer.equals(i + ""))
						return true;
				}
			}
			System.out.println("ERROR: Must give a number between '0' and " + selectionLabels.length + "'");
			return false;
		}
		else {
			switch (swingy.getMainGame().getGameState()) {
				case WaitingDirectionChoice:
					for (int i = 0; i <= MainGame.directions.length; i++) {
						if (answer.equals(i + ""))
							return true;
					}
					System.out.println("ERROR: Must give a number between '0' and " + MainGame.directions.length + "'");
					return false;
				case WaitingFightChoice:
				case WaitingArtifactChoice:
					if (answer.equals("1") || answer.equals("2"))
						return true;
					System.out.println("ERROR: Must give '1' or '2'");
					return false;
				default:
					return false;
			}
		}
	}

// --- Redirect logic ----------------------------------------------------------
	public boolean redirectAnswer(String answer) {
		if (!waitingChoice || answer == null)
			return false;

		inputByCLI = true;
		if (selectingHero) {
			if (needConfirm) {
				if (answer.equals("")) {
					// Trick to switch back to hero selection
					needConfirm = false;
					inputByCLI = false;
					return false;
				}
				else
					swingy.getHeroSelector().confirmSelection(answer);
			}
			else {
				if (answer.equals("0"))
					swingy.getHeroSelector().toggleMode();
				else {
					swingy.getHeroSelector().changedSelectedItem(Integer.parseInt(answer) - 1);
				}
			}
		}
		else {
			switch (swingy.getMainGame().getGameState()) {
				case WaitingDirectionChoice:
					if (answer.equals("0"))
						swingy.endGame();
					else
						swingy.getMainGame().directionChosen(Integer.parseInt(answer) - 1);
					break;
				case WaitingFightChoice:
					swingy.getMainGame().fightDecision(answer.equals("1"));
					break;
				case WaitingArtifactChoice:
					swingy.getMainGame().artifactDecision(answer.equals("1"));
					break;
				default:
					// Should never end up here, but just in case
					inputByCLI = false;
					return false;
			}
		}

		return true;
	}

// --- Hero selector funcs -----------------------------------------------------
	public void updateSelectionMode(String[] comboLabels, boolean creatingNew, AHero hero) {
		selectionLabels = comboLabels;
		this.creatingNew = creatingNew;

		if (creatingNew) {
			System.out.println("Create new Hero");
		}
		else {
			System.out.println("Load saved Hero");
		}

		needConfirm = false;
		printLegend();
		waitingChoice = true;
		inputByCLI = false;
	}

	public void updateHeroSelected(int heroIdx, AHero hero) {
		if (needConfirm) {
			System.out.println("");
			needConfirm = false;
			printLegend();
		}
		if (!inputByCLI)
			System.out.println(heroIdx + 1);

		System.out.println();
		System.out.println("Selected following hero:");
		HeroPanelCLI heroPanel = new HeroPanelCLI(hero);
		if (creatingNew) {
			System.out.println("Class Infos:\n" + hero.getClassDescription() + "\n");
		}

		needConfirm = true;
		printLegend();
		waitingChoice = true;
		inputByCLI = false;

	}

// --- Main game funcs ---------------------------------------------------------
	public void mainGameStarted() {
		selectingHero = false;
		selectionLabels = null;
	}

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

// --- Force end of choice thread -------------------------------------------
	public boolean dispose() {
		choiceThread.interrupt();
		if (!inputByCLI) {
			System.out.println("0");
		}
		System.out.println("");
		return !inputByCLI;
	}
}
