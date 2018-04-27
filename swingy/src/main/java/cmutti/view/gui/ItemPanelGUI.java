package cmutti.view.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemPanelGUI extends JPanel {
	SpritePanel sprite = new SpritePanel(null, null);
	JLabel name = new JLabel("");

	ItemPanelGUI(BufferedImage itemSprite, String itemDescription) {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;

		sprite.setMainSprite(itemSprite);
		name.setText(itemDescription);

		add(sprite, constraints);
		constraints.gridx = 1;
		add(name, constraints);
	}

	public void update(BufferedImage itemSprite, String itemDescription) {
		if (itemDescription != name.getText()) {
			sprite.setMainSprite(itemSprite);
			name.setText(itemDescription);

			revalidate();
			repaint();
		}
	}
}
