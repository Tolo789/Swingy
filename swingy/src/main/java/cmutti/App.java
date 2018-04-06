package cmutti;

import cmutti.view.basic.components.MyFrame;
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
				new MyFrame("yo");
			}
		});
    }
}
