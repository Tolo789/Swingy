package cmutti.view.gui;

import cmutti.controller.HeroSelector;
import cmutti.controller.Swingy;
import cmutti.model.heroes.AHero;
import cmutti.view.IMainPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectionPanelGUI extends JPanel implements IMainPanel {
	GridBagConstraints constraints = null;
	JLabel title = new JLabel("Title");
	JPanel comboContainer = new JPanel();
	JLabel comboLabel = new JLabel("Combo label");
	JComboBox<String> typesCombo = null;
	// JTextArea heroDescription = new JTextArea(150, 10);
	JLabel heroDescription = new JLabel("Description");
	JPanel nameContainer = new JPanel();
	JLabel nameLabel = new JLabel("Hero Name");
	JTextField heroName = new JTextField(10);
	HeroPanelGUI heroPanel = null;
	JButton toggleButton = new JButton("Toggle");
	JButton confirmButton = new JButton("Confirm");

	ItemListener comboChangeAction = null;

	SelectionPanelGUI() {
		comboChangeAction = new ItemListener() {
		    public void itemStateChanged(ItemEvent event) {
		       if (event.getStateChange() == ItemEvent.SELECTED) {
				   Swingy.getInstance().getHeroSelector().changedSelectedItem(typesCombo.getSelectedIndex());
		       }
		    }
		};

		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Swingy.getInstance().getHeroSelector().confirmSelection(heroName.getText());
			}
		});

		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Swingy.getInstance().getHeroSelector().toggleMode();
			}
		});

		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		JPanel titleContainer = new JPanel();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 4;
		constraints.weighty = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		titleContainer.setPreferredSize(new Dimension(1100, 100));
		titleContainer.setMinimumSize(new Dimension(1100, 100));
		titleContainer.add(title);
		add(titleContainer, constraints);

		comboContainer = new JPanel();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 2;
		constraints.weighty = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		comboContainer.setPreferredSize(new Dimension(550, 100));
		comboContainer.setMinimumSize(new Dimension(550, 100));
		comboContainer.add(comboLabel);
		add(comboContainer, constraints);

		JPanel descriptionContainer = new JPanel();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 2;
		constraints.weighty = 2;
		constraints.gridwidth = 2;
		constraints.gridheight = 2;
		descriptionContainer.setPreferredSize(new Dimension(550, 200));
		descriptionContainer.setMinimumSize(new Dimension(550, 200));
		heroDescription.setPreferredSize(new Dimension(500, 200));
		descriptionContainer.add(heroDescription);
		add(descriptionContainer, constraints);

		nameContainer = new JPanel();
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weightx = 2;
		constraints.weighty = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		nameContainer.setPreferredSize(new Dimension(550, 100));
		nameContainer.setMinimumSize(new Dimension(550, 100));
		add(nameContainer, constraints);

		JPanel toggleContainer = new JPanel();
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		toggleContainer.setPreferredSize(new Dimension(275, 100));
		toggleContainer.setMinimumSize(new Dimension(275, 100));
		toggleContainer.add(toggleButton);
		add(toggleContainer, constraints);

		JPanel confirmContainer = new JPanel();
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		confirmContainer.setPreferredSize(new Dimension(275, 100));
		confirmContainer.setMinimumSize(new Dimension(275, 100));
		confirmContainer.add(confirmButton);
		add(confirmContainer, constraints);
	}

	public void updateMode(String[] comboLabels, boolean creatingNew, AHero hero) {
		if (creatingNew) {
			title.setText("Create new Hero");
			comboLabel.setText("Hero Class");
			nameContainer.add(nameLabel);
			nameContainer.add(heroName);
			toggleButton.setText("View saved Heroes");
		}
		else {
			title.setText("Load saved Hero");
			comboLabel.setText("Saved Heroes");
			nameContainer.remove(nameLabel);
			nameContainer.remove(heroName);
			toggleButton.setText("Create new Hero");
		}

		if (typesCombo != null) {
			comboContainer.remove(typesCombo);
		}
		typesCombo = new JComboBox(comboLabels);
		typesCombo.addItemListener(comboChangeAction);
		comboContainer.add(typesCombo);

		updateHeroPanel(hero);

		revalidate();
		repaint();
	}

	public void updateHeroPanel(AHero hero) {
		if (heroPanel != null) {
			remove(heroPanel);
		}

		String html = "<html><body style='width: 100%'>";
		heroDescription.setText(html + hero.getClassDescription());

		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.weightx = 2;
		constraints.weighty = 4;
		constraints.gridwidth = 2;
		constraints.gridheight = 4;
		heroPanel = new HeroPanelGUI(hero);
		heroPanel.setPreferredSize(new Dimension(550, 400));
		heroPanel.setMinimumSize(new Dimension(550, 400));
		add(heroPanel, constraints);
	}
}
