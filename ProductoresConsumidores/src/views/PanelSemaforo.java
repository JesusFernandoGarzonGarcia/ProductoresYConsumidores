package views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PanelSemaforo extends JPanel{

	private static final long serialVersionUID = 1L;
	boolean state= false;
	ArrayList<String> procesosDispinobles;

	public PanelSemaforo(String nombre,boolean state) {
		setBackground(Color.WHITE);
		this.state =state;
		procesosDispinobles = new ArrayList<>();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(50,30,51,100);
		if (state) {
			g.setColor(Color.GREEN);
			g.fillOval(51,31,49,49);
		}else {
			g.setColor(Color.RED);
			g.fillOval(51,80,49,49);
		}
		g.setColor(Color.BLACK);
		if (!procesosDispinobles.isEmpty()) {
			for (String string : procesosDispinobles) {
				 paintList(g,string);
			}
		}
	}

	private void paintList(Graphics g,String nombre) {
		g.drawRect(30,30,51,100);
		g.drawString(nombre,35, 35);
	}

	public void cambiarStateSemaforo(boolean state) {
		this.state =state;
		repaint();
	}
	
	public void actualizarLista(ArrayList<String> lista) {
		this.procesosDispinobles =lista;
		repaint();
	}
}
