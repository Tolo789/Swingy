package cmutti.view.basic.components;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	public MyFrame(String title) {
		setTitle(title);
		setSize(1100, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stop program from running when closing window
		setResizable(false);
		setLocationRelativeTo(null);

		add(new MyPanel());

		setVisible(true);
	}

}
