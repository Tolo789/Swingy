package cmutti.controller;

import cmutti.model.AMapElement;
import cmutti.model.artifacts.AArtifact;
import cmutti.model.heroes.AHero;
import cmutti.model.heroes.Backpacker;
import cmutti.model.heroes.Healer;
import cmutti.model.heroes.KarateGirl;
import cmutti.model.heroes.KarateMan;
import cmutti.view.cli.ChoicePanelCLI;
import cmutti.view.cli.FrameCLI;
import cmutti.view.gui.FrameGUI;
import java.util.Random;
import javax.swing.SwingUtilities;
import lombok.Getter;

public class Swingy
{
	// Static vars
	@Getter private static Swingy instance = new Swingy(); // Singleton for easier function calling from views

	// Game vars
	AHero hero = null;
	int uiToLoad = 0;
	public Random rand = new Random(); // every part of the game should use this rand

	// Child controllers
	@Getter private HeroSelector heroSelector = null;
	@Getter private MainGame mainGame = null;

	// UIs entry points
	FrameGUI guiFrame = null;
	FrameCLI cliFrame = null;
	boolean onlyGUI = false;
	boolean onlyCLI = false;

	private Swingy() {}

	// First function to be called, load interfaces and start playing
	public void initGame(final String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("console")) {
				if (onlyCLI) {
					System.out.println("ERROR: Two times the 'console' option..!");
					return;
				}
				else {
					onlyCLI = true;
				}
			}
			else if (args[i].equals("gui")) {
				if (onlyGUI) {
					System.out.println("ERROR: Two times the 'gui' option..!");
					return;
				}
				else {
					onlyGUI = true;
				}
			}
			else {
				System.out.println("ERROR: Unknown option '" + args[i] + "', give no option or only one between 'console' and 'gui'");
				return;
			}
		}

		if (onlyGUI && onlyCLI) {
			System.out.println("ERROR: If you want to run GUI and CLI at the same time, do not put any param..!");
			return;
		}

		uiToLoad = (onlyCLI || onlyGUI) ? 1 : 2;

		if (!onlyCLI) {
			// User Swing thread for performance reasons
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					guiFrame = new FrameGUI("Swingy");
					uiReadyToSelect();
				}
			});
		}

		if (!onlyGUI) {
			cliFrame = new FrameCLI();
			uiReadyToSelect();
		}
	}

	// Gather calls from all UIs and starts game when each one is ready
	private void uiReadyToSelect() {
		uiToLoad--;
		if (uiToLoad == 0) {
			heroSelector = new HeroSelector(guiFrame, cliFrame);
			heroSelector.start();
		}
	}

	private void uiReadyToPlay() {
		uiToLoad--;
		if (uiToLoad == 0) {
			mainGame = new MainGame(hero);
			mainGame.start();
		}
	}

// --- End of HeroSelector controller ------------------------------------------
	public void startMainGame(AHero hero) {
		uiToLoad = (onlyCLI || onlyGUI) ? 1 : 2;
		this.hero = hero;
		final AHero gameHero = hero;

		if (guiFrame != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					guiFrame.startMainPanel(gameHero);
					uiReadyToPlay();
				}
			});
		}
		if (cliFrame != null) {
			cliFrame.startMainPanel(hero);
			uiReadyToPlay();
		}
	}

// --- Calls from MainGame controller ------------------------------------------
	public void displayMessage(String message) {
		message += "\n";
		final String toDisplay = message;
		if (guiFrame != null && guiFrame.mainPanel != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					guiFrame.mainPanel.storyPanel.addText(toDisplay);
				}
			});
		}
		if (cliFrame != null) {
			cliFrame.mainPanel.storyPanel.addText(message);
		}
	}

	public void updateMap(AMapElement[][] mapElems) {
		if (guiFrame != null)
			guiFrame.mainPanel.mapPanel.update(mapElems);
	}

	public void updateHero() {
		if (guiFrame != null)
			guiFrame.mainPanel.heroPanel.update();
		if (cliFrame != null)
			cliFrame.mainPanel.heroPanel.update();
	}

	public void showDirectionChoices() {
		// displayMessage("Choose which direction to go to");
		if (guiFrame != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					guiFrame.mainPanel.choicePanel.showDirectionChoices();
				}
			});
		}

		if (cliFrame != null) {
			cliFrame.choicePanel.showDirectionChoices();
		}
	}

	public void stopDirectionChoices(int dirIdx) {
		if (cliFrame != null)
			cliFrame.choicePanel.stopDirectionChoice(dirIdx);
	}

	public void showFightChoices() {
		displayMessage("Fight or Flee ?");
		if (guiFrame != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					guiFrame.mainPanel.choicePanel.showFightChoices();
				}
			});
		}
		if (cliFrame != null)
			cliFrame.choicePanel.showFightChoices();
	}

	public void stopFightChoices(String choice) {
		if (cliFrame != null)
			cliFrame.choicePanel.stopFightChoice(choice);
	}

	public void showArtifactChoices(String artifactType) {
		displayMessage("Equip found " + artifactType + " ?");
		if (guiFrame != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					guiFrame.mainPanel.choicePanel.showArtifactChoices();
				}
			});
		}
		if (cliFrame != null)
			cliFrame.choicePanel.showArtifactChoices();
	}

	public void stopArtifactChoices(String choice) {
		if (cliFrame != null)
			cliFrame.choicePanel.stopArtifactChoice(choice);
	}

	public void showDeathChoices() {
		if (guiFrame != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					guiFrame.mainPanel.choicePanel.showDeathChoices();
				}
			});
		}
		if (cliFrame != null)
			cliFrame.choicePanel.showDeathChoices();
	}

	public void retryGame() {
		if (cliFrame != null)
			cliFrame.choicePanel.stopDeathChoice();
		displayMessage("");

		mainGame.start();
	}

	public void endGame() {
		if (guiFrame != null) {
			guiFrame.dispose();
			guiFrame = null;
		}
		if (cliFrame != null) {
			cliFrame.dispose();
		}

		DatabaseController.saveHero(hero, cliFrame != null);
	}

// --- Entry point of application ----------------------------------------------
	public static void main(String[] args)
	{
		Swingy.getInstance().initGame(args);
	}
}
