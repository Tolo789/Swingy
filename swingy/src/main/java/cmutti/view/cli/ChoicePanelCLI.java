package cmutti.view.cli;

import cmutti.controller.MainGame;
import cmutti.controller.Swingy;
import cmutti.model.heroes.AHero;
import cmutti.view.IChoicePanel;
import cmutti.view.ISelectionPanel;
import cmutti.view.cli.runnables.ChoiceRunnable;
import java.lang.annotation.ElementType;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.Getter;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.defs.MaxDef;
import org.hibernate.validator.cfg.defs.MinDef;
import org.hibernate.validator.cfg.defs.NotNullDef;

public class ChoicePanelCLI implements ISelectionPanel, IChoicePanel {
	// Controller ref
	Swingy swingy = Swingy.getInstance();

	@Getter boolean waitingChoice = false;
	boolean inputByCLI = true;
	boolean selectingHero = true;
	String validationString = null; // used for dynamic hibernate validation
	int validationInt = 0;

	// Vars used only for Hero selection
	boolean creatingNew = false;
	boolean needConfirm = false;
	boolean canToggle = false;
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
				legend = "Type ";
				legend += (creatingNew) ? "hero name" : "anything";
				legend += " to Confirm, empty line to Change";
			}
			else {
				for (int i = 0; i < selectionLabels.length; i++) {
					if (i != 0)
						legend += ", ";
					legend += (i + 1) + " for '" + selectionLabels[i] + "'";
				}

				if (!creatingNew) {
					legend += ", 0 to Create New Hero";
				}
				else if (canToggle) {
					legend += ", 0 to Load Saved Heroes";
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
		if (!waitingChoice)
			return false;

		HibernateValidatorConfiguration config = Validation.byProvider(HibernateValidator.class).configure();
		ConstraintMapping mapping = config.createConstraintMapping();

		// configure mapping instance
		try {
			if (selectingHero) {
				if (needConfirm) {
					validationString = answer;
					mapping.type(ChoicePanelCLI.class)
						.property("validationString", ElementType.FIELD)
							.constraint(new NotNullDef());
				}
				else {
					validationInt = Integer.parseInt(answer);
					mapping.type(ChoicePanelCLI.class)
						.property("validationInt", ElementType.FIELD)
							.constraint(new MinDef().value(canToggle ? 0 : 1))
							.constraint(new MaxDef().value(selectionLabels.length));
				}
			}
			else {
				validationInt = Integer.parseInt(answer);
				switch (swingy.getMainGame().getGameState()) {
					case WaitingDirectionChoice:
						mapping.type(ChoicePanelCLI.class)
							.property("validationInt", ElementType.FIELD)
								.constraint(new MinDef().value(0))
								.constraint(new MaxDef().value(MainGame.directions.length));
					case WaitingFightChoice:
					case WaitingArtifactChoice:
						mapping.type(ChoicePanelCLI.class)
							.property("validationInt", ElementType.FIELD)
								.constraint(new MinDef().value(1))
								.constraint(new MaxDef().value(2));
					default:
						return false;
				}
			}
		}
		catch (NumberFormatException e) {
			System.out.println("ERROR !! '" + answer + "' given, but a numeric entry is required..!");
			return false;
		}

		// Actualcheck
		config.addMapping(mapping);
		ValidatorFactory factory = config.buildValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ChoicePanelCLI>> constraintViolations = validator.validate(this);
		if (constraintViolations.size() > 0) {
			ConstraintViolation<ChoicePanelCLI> firstViolation = constraintViolations.iterator().next();
			System.out.println("ERROR !! '" + firstViolation.getInvalidValue() + "' given, but it is an invalid entry..!");
			return false;
		}
		return true;
	}

	// Just in case we get tired of validating answer with hibernate annotations
	public boolean isValidAnswerNoHibernate(String answer) {
		if (!waitingChoice || answer == null)
			return false;

		if (selectingHero) {
			if (needConfirm) {
				return true;
			}
			else {
				if (!canToggle && answer.equals("0")) {
					System.out.println("ERROR: Must give a number between '1' and " + selectionLabels.length + "'");
					return false;
				}
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
	public void updateSelectionMode(String[] comboLabels, boolean creatingNew, AHero hero, boolean canToggle) {
		selectionLabels = comboLabels;
		this.creatingNew = creatingNew;

		if (!inputByCLI) {
			System.out.println("0");
		}
		System.out.println("");
		if (creatingNew) {
			System.out.println("Create new Hero");
		}
		else {
			System.out.println("Load saved Hero");
		}

		needConfirm = false;
		this.canToggle = canToggle;
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

	public void displayError(String error) {
		if (needConfirm) // Only display error when waiting a confirm
			System.out.println(error);
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
	public void dispose() {
		choiceThread.interrupt();
		if (!inputByCLI) {
			System.out.println("0");
		}
		System.out.println("");
	}
}
