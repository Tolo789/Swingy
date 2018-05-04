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
	public Random rand = new Random(); // every part of the game should use this rand

	// Child controllers
	@Getter private HeroSelector heroSelector = null;
	@Getter private MainGame mainGame = null;

	// UIs entry points
	FrameGUI guiFrame = null;
	FrameCLI cliFrame = null;

	private Swingy() {}

	// First function to be called, load interfaces and start playing
	public void startGame(String[] args) {
		System.out.println( "Hello World!" );

		// User Swing thread for performance reasons
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				guiFrame = new FrameGUI("Swingy");
				cliFrame = new FrameCLI();

				// TODO: create/load hero
				heroSelector = new HeroSelector(guiFrame, cliFrame);
				heroSelector.start();
			}
		});
	}

// --- End of HeroSelector controller ------------------------------------------
	public void startMainGame(AHero hero) {
		mainGame = new MainGame(hero);
		final AHero gameHero = hero;
		if (guiFrame != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					guiFrame.startMainPanel(gameHero);
				}
			});
		}
		if (cliFrame != null) {
			cliFrame.startMainPanel(hero);
		}
		mainGame.start();
	}

// --- Calls from MainGame controller ------------------------------------------
	public void displayMessage(String message) {
		message += "\n";
		final String toDisplay = message;
		if (guiFrame != null) {
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

// --- Entry point of application ----------------------------------------------
	public static void main(String[] args)
	{
		Swingy.getInstance().startGame(args);
	}
}
