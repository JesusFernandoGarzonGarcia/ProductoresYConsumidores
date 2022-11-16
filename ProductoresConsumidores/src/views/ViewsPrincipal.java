package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.Productor;

/**
 * Esta clase define el frame inicial del programa e inicializa todos los componentes necesarios 
 * @author: Andres Vargas
 * @version: 02/11/2022/A
 */
public class ViewsPrincipal extends JFrame{
	//Campos de la clase
	PanelProcesos productor;
	PanelProcesos consumidor;
	PanelBuffer buffer;
	PanelConfiguracion confi;

	/**
	 * Constructor de la parte visual del programa
	 */
	public ViewsPrincipal(ActionListener listener) {
		init(listener);
	}

	
	/**
	 * inicializa todos los valores y compnentes necesarios para pa parte grafica
	 * @param hilo que permite escuchar las acciones de los botones de la arte visual
	 */
	private void init(ActionListener listener) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(550,550));
		setBackground(Color.WHITE);
		setLocationRelativeTo(rootPane);
		setLayout(new BorderLayout());

		JPanel contenCENTER = new JPanel();
		contenCENTER.setLayout(new BorderLayout());

		JPanel panelContenProcess = new JPanel();
		panelContenProcess.setLayout(new GridLayout(1,2));
		productor = new PanelProcesos();
		panelContenProcess.add(productor);
		consumidor = new PanelProcesos();
		panelContenProcess.add(consumidor);
		contenCENTER.add(panelContenProcess,BorderLayout.CENTER);

		buffer = new PanelBuffer();
		contenCENTER.add(buffer,BorderLayout.PAGE_END);

		add(contenCENTER,BorderLayout.CENTER);

		confi = new PanelConfiguracion(listener);

		add(confi,BorderLayout.PAGE_END);

		setVisible(true);
	}

	/**
	 * actualiza el estado del productor actual
	 * @param estado del productor actual
	 */
	public void actualizarEstadoProductor(boolean state) {
		productor.cambiarStateSemaforo(state);
		productor.repaint();
	}

	/**
	 * actualiza el estado del consumidor actual
	 * @param estado del consumidor actual
	 */
	public void actualizarEstadoConsumidor(boolean state) {
		consumidor.cambiarStateSemaforo(state);
		consumidor.repaint();
	}

	/**
	 * actualiza la cola de consumidores
	 * @param cola de consumidores 
	 */
	public void actualizarListaConsumidores(Queue<Productor> state) {
		consumidor.actualizarLista(state);
		consumidor.revalidate();
		consumidor.repaint();
	}

	/**
	 * actualiza la cola de productores
	 * @param cola de productores 
	 */
	public void actualizarListaProductores(Queue<Productor> state) {
		productor.actualizarLista(state);
		productor.revalidate();
		productor.repaint();
	}
	
	/**
	 * actualiza el producto actual
	 * @param Productor actual
	 */
	public void actualizarProductorActual(Productor proceso) {
		productor.actualizarProcesoActual(proceso);
		productor.revalidate();
		productor.repaint();
	}
	
	/**
	 * actualiza el consumidor actual
	 * @param consumidor actual
	 */
	public void actualizarConsumidorActual(Productor proceso) {
		consumidor.actualizarProcesoActual(proceso);
		consumidor.revalidate();
		consumidor.repaint();
	}
	
	/**
	 * actualiza el buffer
	 * @param Strign[] buffer que contiene valores "T y F"
	 */
	public void actualizarBuffer(String[] bufferIn) {
		buffer.actualizarBuffer(bufferIn);
		buffer.revalidate();
		buffer.repaint();
	}

	/**
	 * permite obtener la cantidad de productores ingrsados por el usuario
	 * @return cantidad de productores
	 */
	public String getProductores() {
		return confi.getProductores();
	}

	/**
	 * permite obtener la cantidad de consumidores ingrsados por el usuario
	 * @return cantidad de consumidores
	 */
	public String getConsumidores() {
		return confi.getConsumidores();
	}

	/**
	 * permite obtener tamaño del buffer ingrsados por el usuario
	 * @return tamaño del buffer 
	 */
	public String getTamanioBuffer() {
		return confi.getTamanioBuffer();
	}
	
	/**
	 * permite obtener la cantidad de iteraciones ingrsados por el usuario
	 * @return cantidad de iteraciones
	 */
	public String getCantidadIteraciones() {
		return confi.getCantidadIteraciones();
	}
	
	/**
	 * permite mostrar un mensaje al usuario
	 */
	public  void menssage(JFrame views,String mensaje) {
		JOptionPane.showMessageDialog(buffer, mensaje);
	}

	
}
