package cmutti.view.basic.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyPanel extends JPanel {

	MyPanel() {
		String[] comboKeys = new String[]{"a", "b"};

		JButton button = new JButton("HEllo");
		JLabel label = new JLabel("String");
		JTextField textField = new JTextField(10);
		JPasswordField passField = new JPasswordField(10);
		JComboBox<String> combo = new JComboBox<String>(comboKeys);
		//combo.add("toto");

		add(button);
		add(label);
		add(textField);
		add(passField);
		add(combo);
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
