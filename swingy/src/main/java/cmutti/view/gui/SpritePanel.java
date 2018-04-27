package cmutti.view.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import lombok.Setter;

@Setter
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
			// Keep scale of main sprite
			float panelScale = 1f * getWidth() / getHeight();
			float spriteScale = 1f * mainSprite.getWidth() / mainSprite.getHeight();

			int imgStartX;
			int imgStartY;
			int imgWidth;
			int imgHeight;

			// if (spriteScale == panelScale) {
			// 	// Easy case
				// imgStartX = 0;
				// imgStartY = 0;
				// imgWidth = getWidth();
				// imgHeight = getHeight();
			// }
			// else if (spriteScale > panelScale) {
			if (spriteScale > panelScale) {
				// Case where sprite is wider than panel (in scale)
				// Clip to left-right borders and center vertically
				imgStartX = 0;
				imgWidth = getWidth();

				imgHeight = (int)(imgWidth / spriteScale);
				imgStartY = (getHeight() - imgHeight) / 2;
			}
			else {
				// Case where sprite is higher than panel (in scale)
				// Clip to top-bot borders and center horizontally
				imgStartY = 0;
				imgHeight = getHeight();

				imgWidth = (int)(imgHeight * spriteScale);
				imgStartX = (getWidth() - imgWidth) / 2;
			}

				// imgStartX = 0;
				// imgStartY = 0;
				// imgWidth = getWidth();
				// imgHeight = getHeight();

			g.drawImage(mainSprite, imgStartX, imgStartY, imgWidth, imgHeight, this);
		}
	}
}
