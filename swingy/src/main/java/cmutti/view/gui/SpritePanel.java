package cmutti.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpritePanel extends JPanel {
	protected String spritePath = "";

	SpritePanel(String path) {
		spritePath = path;
	}

	public void paintComponent(Graphics g) {
		if (spritePath == "")
			return;

		try {
			Image image = ImageIO.read(new File(spritePath));
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
