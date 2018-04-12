package cmutti.controller;

import cmutti.model.heroes.AHero;
import cmutti.model.heroes.KarateMan;
import cmutti.view.gui.FrameGUI;
import javax.swing.SwingUtilities;

public class Swingy
{
	// Static vars
	private static Swingy instance = new Swingy(); // Singleton for easier function calling from views
	public static String[] directions = new String[]{"North", "South", "West", "East"};

	// Game vars
	FrameGUI guiFrame = null;
	AHero hero = null;

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
				hero = new KarateMan("yo2", 1);
				StartMainGame();
			}
		});
	}

	private void StartMainGame() {
		guiFrame.StartMainPanel(hero);
	}

	// Call from view when user choose a direction
	public void directionChosen(int dirIdx) {
		System.out.println(dirIdx);
		//hero.levelUp();
	}


	// Entry point of application
    public static void main(String[] args)
    {
		Swingy.getInstance().startGame(args);
    }
}
