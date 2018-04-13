package cmutti.view.gui;

import cmutti.controller.Swingy;
import cmutti.view.IChoicePanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoicePanelGUI extends JPanel implements IChoicePanel {
	private static Swingy swingy = Swingy.getInstance();
	JComboBox<String> dirCombo = new JComboBox<String>(swingy.getMainGame().directions);
	JButton confirmButton = new JButton("Confirm");

	ChoicePanelGUI() {
		JLabel label = new JLabel("String");

		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swingy.getMainGame().directionChosen(dirCombo.getSelectedItem().toString());
			}

		});

    this.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLUE));
		add(label);
		add(dirCombo);
		add(confirmButton);
	}
}
