package cmutti.view.gui;

import cmutti.model.AMapElement;
import cmutti.view.IMapPanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapPanelGUI extends JPanel implements IMapPanel {
	private static String grassPath = "sprites/grass.jpeg";
	private static int squareSize = 32;

	private boolean dirty = false;

	MapPanelGUI() {
    // this.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.BLUE));

		setLayout(null);
	}

	public void update(AMapElement[][] mapElems) {
		// If any, delete prev elem
    if (dirty)
			removeAll();

		// spritePanels = spritePanels[mapElems.length][mapElems.length];
		setSize(squareSize * mapElems.length, squareSize * mapElems.length);

		String backPath = "";
		String frontPath = "";
		AMapElement elem = null;
		SpritePanel spritePanel = null;
    for (int y = 0; y < mapElems.length; y++) {
      for (int x = 0; x < mapElems.length; x++) {
				elem = mapElems[y][x];
				if (elem == null) {
					frontPath = "";
					backPath = grassPath;
				}
				else {
					if (elem.needGrass)
						backPath = grassPath;
					else
						backPath = "";

					frontPath = elem.spritePath;
									// spritePanel = new SpritePanel(frontPath, backPath);
									// spritePanel.setLocation(x * squareSize, y * squareSize);
									// spritePanel.setSize(squareSize, squareSize);
									// add(spritePanel);
				}

				spritePanel = new SpritePanel(frontPath, backPath);
				spritePanel.setLocation(x * squareSize, y * squareSize);
				spritePanel.setSize(squareSize, squareSize);
				spritePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
				add(spritePanel);
      }
    }

		dirty = true;
	}
}
