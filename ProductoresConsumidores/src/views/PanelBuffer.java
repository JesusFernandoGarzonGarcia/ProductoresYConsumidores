package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBuffer extends JPanel{

	public PanelBuffer() {
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
	}

	public void actualizarBuffer(String[] buffer) {
		removeAll();
		setLayout(new GridLayout(1,buffer.length));
		for (String string : buffer) {
			JPanel panel = new JPanel();
			JLabel label = new JLabel(string);
			if (string.equalsIgnoreCase("T")) {
				panel.setBackground(Color.ORANGE);
			}else {
				panel.setBackground(Color.GRAY);
			}
			panel.add(label);
			add(panel);
		}
		revalidate();
		repaint();

	}

}
