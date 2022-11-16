package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import dao.Productor;

public class PanelProcesos extends JPanel {


	JList<String> lis;
	PanelSemaforo semaformo;
	Productor consumidorActual;
	JLabel l;

	public PanelProcesos() {
		setBackground(Color.WHITE);
		setLayout( new GridLayout(2,2));
		setBorder( BorderFactory.createEmptyBorder(0,20,0,20));
		l = new JLabel("Actual",SwingConstants.CENTER);
		semaformo = new PanelSemaforo("Productores", false);
		if (consumidorActual!= null) {
			l.setText(consumidorActual.getName());
			l.setHorizontalAlignment(SwingConstants.CENTER);
			l.setBackground(Color.WHITE);
			add(l);
			semaformo = new PanelSemaforo("Productores", consumidorActual.getSemaforo().isTurno());
			add(semaformo);
		}else {
			add(l);
			add(semaformo);
			lis = new JList<>();
			add(new JScrollPane(lis));			
		}
	}

	public void actualizarLista(Queue<Productor> data) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		if (data!=null) {
			for (Productor string : data) {
				if (string!=null) {
					modelo.addElement(string.getName()+" "+string.getSemaforo().isTurno()+""+string.getTimeUso());
				}
			}
		}
		lis.setModel(modelo);
	}

	public void actualizarProcesoActual(Productor actual) {
		consumidorActual=actual;
		String data =  "<html><body>"+consumidorActual.getName()+" <br> Campos a Utilizar "+consumidorActual.getCamposAintroducir()+" <br></body></html>";
		l.setText(data);
		l.setHorizontalAlignment(SwingConstants.CENTER);
		l.setBackground(Color.WHITE);
		semaformo.cambiarStateSemaforo(consumidorActual.getSemaforo().isTurno());
		semaformo.revalidate();
		semaformo.repaint();
		revalidate();
		repaint();
	}

	public void cambiarStateSemaforo(boolean state) {
		semaformo.cambiarStateSemaforo(state);
		semaformo.repaint();
	}


}
