package cmutti.controller;

import cmutti.model.AMapElement;
import cmutti.model.artifacts.AArtifact;
import cmutti.model.heroes.AHero;
import cmutti.model.heroes.Backpacker;
import cmutti.model.heroes.Healer;
import cmutti.model.heroes.KarateGirl;
import cmutti.model.heroes.KarateMan;
import cmutti.view.gui.FrameGUI;
import cmutti.view.cli.FrameCLI;
import java.util.Random;
import javax.swing.SwingUtilities;
import lombok.Getter;

public class Swingy
{
	// Static vars
	@Getter private static Swingy instance = new Swingy(); // Singleton for easier function calling from views

	// Game vars
	public Random rand = new Random(); // every part of the game should use this rand
	AHero hero = null;

	// Child controllers
	@Getter private MainGame mainGame = null;

	// UIs
	FrameGUI guiFrame = null;
	FrameCLI cliFrame = null;

	private Swingy() {}

	public static Swingy getInstance() {
		return Swingy.instance;
	}

	// First function to be called, load interfaces and start playing
	public void startGame(String[] args) {
		System.out.println( "Hello World!" );

		// User Swing thread for performance reasons
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				guiFrame = new FrameGUI("Swingy");
				cliFrame = new FrameCLI();

				// TODO: create/load hero
				hero = new KarateMan("yo2", 1);
				// hero = new Backpacker("yo2", 1);
				// hero = new KarateGirl("yo2", 1);
				// hero = new Healer("yo2", 1);
				StartMainGame();
			}
		});
	}

// --- Calls from CharSelect controller ------------------------------------------
	private void StartMainGame() {
		if (guiFrame != null)
			guiFrame.StartMainPanel(hero);
		if (cliFrame != null)
			cliFrame.StartMainPanel(hero);
		mainGame = new MainGame(hero);
		mainGame.start();
	}

// --- Calls from all controllers ----------------------------------------------
	public void displayMessage(String message) {
		message += "\n";
		if (guiFrame != null) {
			guiFrame.mainPanel.storyPanel.addText(message);
		}
		if (cliFrame != null) {
			cliFrame.mainPanel.storyPanel.addText(message);
		}
	}

// --- Calls from MainGame controller ------------------------------------------
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
			guiFrame.mainPanel.choicePanel.showDirectionChoices();
		}

		if (cliFrame != null) {
			cliFrame.mainPanel.choicePanel.showDirectionChoices();
		}
	}

	public void stopDirectionChoices(int dirIdx) {
		if (cliFrame != null)
			cliFrame.mainPanel.choicePanel.stopDirectionChoice(dirIdx);
	}

	public void showFightChoices() {
		displayMessage("Fight or Flee ?");
		if (guiFrame != null)
			guiFrame.mainPanel.choicePanel.showFightChoices();
		if (cliFrame != null)
			cliFrame.mainPanel.choicePanel.showFightChoices();
	}

	public void stopFightChoices(String choice) {
		if (cliFrame != null)
			cliFrame.mainPanel.choicePanel.stopFightChoice(choice);
	}

	public void showArtifactChoices(String artifactType) {
		displayMessage("Equip found " + artifactType + " ?");
		if (guiFrame != null)
			guiFrame.mainPanel.choicePanel.showArtifactChoices();
		if (cliFrame != null)
			cliFrame.mainPanel.choicePanel.showArtifactChoices();
	}

	public void stopArtifactChoices(String choice) {
		if (cliFrame != null)
			cliFrame.mainPanel.choicePanel.stopArtifactChoice(choice);
	}

	// Entry point of application
	public static void main(String[] args)
	{
		Swingy.getInstance().startGame(args);
	}
}
