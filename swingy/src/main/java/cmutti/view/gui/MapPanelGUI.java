package cmutti.view.gui;

import cmutti.model.AMapElement;
import cmutti.model.heroes.AHero;
import cmutti.model.monsters.AMonster;
import cmutti.view.IMapPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MapPanelGUI extends JPanel implements IMapPanel {
	private static String grassPath = "sprites/grass.jpeg";
	private static int squareSize = 32;

	private BufferedImage bckSprite = null;
	private boolean dirty = false;
	private JPanel container = null;
	private JScrollPane scrollPane = null;
	private GridBagConstraints constraints = null;

	MapPanelGUI() {
		try {
			this.bckSprite = ImageIO.read(new File(grassPath));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
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

		BufferedImage backSprite = null;
		BufferedImage frontSprite = null;
		AMapElement elem = null;
		SpritePanel spritePanel = null;
		int heroX = 0;
		int heroY = 0;
	    for (int y = 0; y < mapElems.length; y++) {
			for (int x = 0; x < mapElems.length; x++) {
				elem = mapElems[y][x];
				if (elem == null) {
					frontSprite = null;
					backSprite = bckSprite;
				}
				else {
					if (elem.needGrass)
						backSprite = bckSprite;
					else
						backSprite = null;

					if (elem instanceof AHero) {
						heroX = elem.getPosX();
						heroY = elem.getPosY();
					}

					frontSprite = elem.getSprite();
				}

				spritePanel = new SpritePanel(frontSprite, backSprite);
				spritePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
				container.add(spritePanel);
			}
	    }

		// int actualsSquareSize = scrollPane.getViewport().getViewSize().width / mapElems.length;
		// actualsSquareSize /= 2;
		// scrollPane.getViewport().setViewPosition(new Point(heroX * actualsSquareSize, heroY * actualsSquareSize));

		scrollPane.validate();
		scrollPane.repaint();
		dirty = true;
	}
}
