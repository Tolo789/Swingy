package cmutti;

import cmutti.view.gui.FrameGUI;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
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
}
