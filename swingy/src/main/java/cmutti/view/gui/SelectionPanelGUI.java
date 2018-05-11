package cmutti.view.gui;

import cmutti.controller.HeroSelector;
import cmutti.controller.Swingy;
import cmutti.model.heroes.AHero;
import cmutti.view.ISelectionPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class SelectionPanelGUI extends JPanel implements ISelectionPanel {
	GridBagConstraints constraints = null;
	JLabel title = new JLabel("");
	JPanel comboContainer = null;
	JLabel comboLabel = new JLabel("");
	JComboBox<String> typesCombo = null;
	// JTextArea heroDescription = new JTextArea(150, 10);
	JLabel heroDescription = new JLabel("");
	JPanel nameContainer = null;
	JLabel nameLabel = new JLabel("Hero Name");
	JPanel errorContainer = null;
	JLabel errorLabel = new JLabel("");
	JTextField heroName = new JTextField(20);
	HeroPanelGUI heroPanel = null;
	JPanel toggleContainer = null;
	JButton toggleButton = new JButton("");
	JButton confirmButton = new JButton("Confirm");

	ItemListener comboChangeAction = null;
	final String html = "<html><body style='width: 100%'>";

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
		titleContainer.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH, FrameGUI.FRAME_HEIGHT / 6));
		titleContainer.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH, FrameGUI.FRAME_HEIGHT / 6));
		titleContainer.add(title);
		Font font = title.getFont();
		title.setFont(font.deriveFont(font.getStyle() | Font.BOLD));
		add(titleContainer, constraints);

		comboContainer = new JPanel();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 2;
		constraints.weighty = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		comboContainer.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT / 6));
		comboContainer.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT / 6));
		comboContainer.add(comboLabel);
		add(comboContainer, constraints);

		JPanel descriptionContainer = new JPanel();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 2;
		constraints.weighty = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		descriptionContainer.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT / 6));
		descriptionContainer.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT / 6));
		heroDescription.setPreferredSize(new Dimension((FrameGUI.FRAME_WIDTH / 2) - 50, FrameGUI.FRAME_HEIGHT / 6));
		descriptionContainer.add(heroDescription);
		add(descriptionContainer, constraints);

		nameContainer = new JPanel();
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 2;
		constraints.weighty = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		nameContainer.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT / 6));
		nameContainer.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT / 6));
		add(nameContainer, constraints);


		errorContainer = new JPanel();
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weightx = 2;
		constraints.weighty = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		errorContainer.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT / 6));
		errorContainer.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, FrameGUI.FRAME_HEIGHT / 6));
		errorLabel.setPreferredSize(new Dimension((FrameGUI.FRAME_WIDTH / 2) - 50, FrameGUI.FRAME_HEIGHT / 6));
		errorLabel.setForeground(Color.RED);
		errorContainer.add(errorLabel);
		add(errorContainer, constraints);

		toggleContainer = new JPanel();
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		toggleContainer.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, FrameGUI.FRAME_HEIGHT / 6));
		toggleContainer.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, FrameGUI.FRAME_HEIGHT / 6));
		toggleContainer.add(toggleButton);
		add(toggleContainer, constraints);

		JPanel confirmContainer = new JPanel();
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		confirmContainer.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, FrameGUI.FRAME_HEIGHT / 6));
		confirmContainer.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 4, FrameGUI.FRAME_HEIGHT / 6));
		confirmContainer.add(confirmButton);
		add(confirmContainer, constraints);
	}

	public void updateSelectionMode(String[] comboLabels, boolean creatingNew, AHero hero, boolean canToggle) {
		if (creatingNew) {
			title.setText("Create new Hero");
			comboLabel.setText("Hero Class");
			nameContainer.add(nameLabel);
			nameContainer.add(heroName);

			if (canToggle) {
				toggleButton.setText("View saved Heroes");
				toggleContainer.add(toggleButton);
			}
			else {
				toggleContainer.remove(toggleButton);
			}
		}
		else {
			title.setText("Load saved Hero");
			comboLabel.setText("Saved Heroes");
			nameContainer.remove(nameLabel);
			nameContainer.remove(heroName);
			toggleButton.setText("Create new Hero");
			toggleContainer.add(toggleButton);
		}

		if (typesCombo != null) {
			comboContainer.remove(typesCombo);
		}
		typesCombo = new JComboBox(comboLabels);
		typesCombo.addItemListener(comboChangeAction);
		comboContainer.add(typesCombo);
		errorLabel.setText("");

		updateHeroSelected(0, hero);

		revalidate();
		repaint();
	}

	public void updateHeroSelected(int heroIdx, AHero hero) {
		if (heroPanel != null) {
			remove(heroPanel);
		}

		typesCombo.setSelectedIndex(heroIdx);
		errorLabel.setText("");

		heroDescription.setText(html + hero.getClassDescription());

		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.weightx = 2;
		constraints.weighty = 4;
		constraints.gridwidth = 2;
		constraints.gridheight = 4;
		heroPanel = new HeroPanelGUI(hero);
		heroPanel.setPreferredSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, 4 * FrameGUI.FRAME_HEIGHT / 6));
		heroPanel.setMinimumSize(new Dimension(FrameGUI.FRAME_WIDTH / 2, 4 * FrameGUI.FRAME_HEIGHT / 6));
		add(heroPanel, constraints);

		revalidate();
		repaint();
	}

	public void displayError(String error) {
		errorLabel.setText(html + error);
	}
}
