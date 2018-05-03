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
	// Generic label
	JLabel label = new JLabel("String");

	// Direction utils
	JComboBox<String> dirCombo = new JComboBox<String>(swingy.getMainGame().directions);
	JButton confirmButton = new JButton("Confirm");

	// Fight utils
	JButton fightButton = new JButton("Fight");
	JButton fleeButton = new JButton("Flee");

	// Artifact utils
	JButton equipButton = new JButton("Equip");
	JButton cancelButton = new JButton("Cancel");

	ChoicePanelGUI() {

		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swingy.getMainGame().directionChosen(dirCombo.getSelectedIndex());
			}
		});

		fightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swingy.getMainGame().fightDecision(true);
			}
		});


		fleeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swingy.getMainGame().fightDecision(false);
			}
		});

		equipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swingy.getMainGame().artifactDecision(true);
			}
		});


		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swingy.getMainGame().artifactDecision(false);
			}
		});

		// this.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLUE));
	}

	public void showDirectionChoices() {
		label.setText("Choose direction");
		this.removeAll();
		// add(label);
		add(dirCombo);
		add(confirmButton);

		revalidate();
		repaint();
	}

	public void showFightChoices() {
		label.setText("Fight or flee ?");
		this.removeAll();
		// add(label);
		add(fightButton);
		add(fleeButton);

		revalidate();
		repaint();
	}

	public void showArtifactChoices() {
		label.setText("Equip or cancel ?");
		this.removeAll();
		// add(label);
		add(equipButton);
		add(cancelButton);

		revalidate();
		repaint();
	}
}
