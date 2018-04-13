package cmutti.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpritePanel extends JPanel {
	protected BufferedImage mainSprite = null;
	protected BufferedImage bckSprite = null;

	SpritePanel(BufferedImage mainSprite, BufferedImage bckSprite) {
		this.mainSprite = mainSprite;
		this.bckSprite = bckSprite;
	}

	public void paintComponent(Graphics g) {
		if (bckSprite != null) {
			g.drawImage(bckSprite, 0, 0, getWidth(), getHeight(), this);
		}

		if (mainSprite != null) {
			g.drawImage(mainSprite, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
