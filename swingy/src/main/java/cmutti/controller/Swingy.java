package cmutti.controller;

import cmutti.model.heroes.AHero;
import cmutti.model.heroes.KarateMan;
import cmutti.view.gui.FrameGUI;
import javax.swing.SwingUtilities;
import lombok.Getter;

public class Swingy
{
	// Static vars
	private static Swingy instance = new Swingy(); // Singleton for easier function calling from views

	// Game vars
	AHero hero = null;

	// Child controllers
	@Getter private MainGame mainGame = null;

	// UIs
	FrameGUI guiFrame = null;

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

				// TODO: create/load hero
				hero = new KarateMan("yo2", 3);
				StartMainGame();
			}
		});
	}

	private void StartMainGame() {
		mainGame = new MainGame(hero, guiFrame);
	}

	// Entry point of application
  public static void main(String[] args)
  {
		Swingy.getInstance().startGame(args);
  }
}
