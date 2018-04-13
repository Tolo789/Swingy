package cmutti.view.gui;

import cmutti.model.AMapElement;
import cmutti.view.IMapPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MapPanelGUI extends JPanel implements IMapPanel {
	private static String grassPath = "sprites/grass.jpeg";
	private static int squareSize = 32;

	private boolean dirty = false;
	private JPanel container = null;
	private JScrollPane scrollPane = null;
	private GridBagConstraints constraints = null;

	MapPanelGUI() {
    // this.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.BLUE));
		// setLayout(null);

		container = new JPanel();
    container.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.RED));
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		// add(container);

		// scrollPane = new JScrollPane(container, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		// scrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()));
		// scrollPane.setLayout(null);
		// add(scrollPane);
	}

	public void update(AMapElement[][] mapElems) {
		// If any, delete prev elem
    if (dirty)
			removeAll();

		// spritePanels = spritePanels[mapElems.length][mapElems.length];
		setSize(10 * squareSize * mapElems.length, 10 * squareSize * mapElems.length);
		// scrollPane.setPreferredSize(new Dimension(squareSize * mapElems.length, squareSize * mapElems.length));

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
				spritePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
				constraints.gridx = x;
				constraints.gridy = y;
				add(spritePanel, constraints);
      }
    }

		dirty = true;
	}
}
