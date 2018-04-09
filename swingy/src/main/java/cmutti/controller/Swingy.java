package cmutti.controller;

import cmutti.view.gui.FrameGUI;
import javax.swing.SwingUtilities;

public class Swingy
{
	private static Swingy instance = new Swingy(); // Singleton for easier function calling from views

	public final String[] directions = new String[]{"North", "South", "West", "East"};

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
				FrameGUI guiFrame = new FrameGUI("Swingy");
				guiFrame.StartMainPanel();
				//new MyFrame("yo");
			}
		});
	}

	// Call from view when user choose a direction
	public void directionChosen(int dirIdx) {
		System.out.println(dirIdx);
	}


	// Entry point of application
    public static void main(String[] args)
    {
		Swingy.getInstance().startGame(args);
    }
}
