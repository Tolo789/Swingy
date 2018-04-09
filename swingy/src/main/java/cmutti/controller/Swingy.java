package cmutti.controller;

import cmutti.view.gui.FrameGUI;
import javax.swing.SwingUtilities;

public class Swingy
{
	Swingy(String[] args) {
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

    public static void main(String[] args)
    {
		new Swingy(args);
    }
}
