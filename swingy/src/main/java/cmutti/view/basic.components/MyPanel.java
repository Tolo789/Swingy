package cmutti.view.basic.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class MyPanel extends JPanel {

	MyPanel() {
		String[] comboKeys = new String[]{"North", "South", "West", "East"};

		JButton button = new JButton("HEllo");
		JLabel label = new JLabel("String");
		JTextField textField = new JTextField(10);
		final JTextArea textArea = new JTextArea(15, 12);
		textArea.setEditable(false);
		JPasswordField passField = new JPasswordField(10);
		final JComboBox<String> combo = new JComboBox<String>(comboKeys);
		//combo.add("toto");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append(combo.getSelectedItem().toString() + "\n");
			}

		});


		// add(button);
		// add(label);
		// add(textField);
		// add(passField);
		// add(combo);
		// add(new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		panel1.add(textField);
		add(new JScrollPane(panel1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 3;
		panel2.add(passField);
		constraints.gridwidth = 3;
		add(new JScrollPane(panel2, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), constraints);
		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridheight = 2;
		constraints.gridwidth = 1;
		constraints.weightx = 1;
		panel3.add(new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));
		add(panel3, constraints);
		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridheight = 1;
		panel4.add(combo);
		panel4.add(button);
		add(new JScrollPane(panel4, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), constraints);
	}

	/*
	public void paintComponent(Graphics g) {

		setBackground(Color.RED);

		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, getWidth(), getHeight());

		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gradient = new GradientPaint(300, 100, Color.GREEN, 600, 400, Color.PINK);
		g2d.setPaint(gradient);
		g2d.fillRect(300, 100, 300, 300);

		g.setColor(Color.BLUE);
		Font font = new Font("Arial", Font.BOLD, 30);
		g.setFont(font);
		g.drawString("Bonjur", 50, 50);
		g.drawRect(100, 100, 100, 100);

		try {
			Image image = ImageIO.read(new File("sprites/test.png"));
			g.drawImage(image, 10, 10, 20, 20, this);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	*/
}
