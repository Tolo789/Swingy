package cmutti.view.gui;

import cmutti.model.AMapElement;
import cmutti.view.IMapPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
		setLayout(new GridBagLayout());

		container = new JPanel();

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;

		scrollPane = new JScrollPane(container);
		add(scrollPane, constraints);
	}

	public void update(AMapElement[][] mapElems) {
		// If any, delete prev elem
    if (dirty) {
			container.removeAll();
		}

		container.setLayout(new GridLayout(mapElems.length, mapElems.length, 0, 0));
		container.setPreferredSize(new Dimension(squareSize * mapElems.length, squareSize * mapElems.length));

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
				}

				spritePanel = new SpritePanel(frontPath, backPath);
				spritePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
				container.add(spritePanel);
      }
    }

		dirty = true;
	}
}
