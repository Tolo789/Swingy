package cmutti.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpritePanel extends JPanel {
	protected String mainSprite = "";
	protected String bckSprite = "";

	SpritePanel(String mainSprite, String bckSprite) {
		this.mainSprite = mainSprite;
		this.bckSprite = bckSprite;
	}

	public void paintComponent(Graphics g) {
		try {
			if (bckSprite != "") {
				Image image = ImageIO.read(new File(bckSprite));
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}

			if (mainSprite != "") {
				Image image = ImageIO.read(new File(mainSprite));
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
