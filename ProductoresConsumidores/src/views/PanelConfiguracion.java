package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelConfiguracion extends JPanel{
	JTextField cantidadProductores;
	JTextField cantidadConsumidores;
	JTextField tamanioBuffer;
	JTextField cantidadIteraciones;

	public PanelConfiguracion(ActionListener listener) {

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Configuración"));
		setBackground(Color.WHITE);

		JPanel panelContenData = new JPanel();
		panelContenData.setBackground(Color.WHITE);
		panelContenData.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
		panelContenData.setLayout(new GridLayout(1, 4));
		cantidadProductores = new JTextField();
		cantidadProductores.setBorder(BorderFactory.createTitledBorder("# Productores"));
		cantidadConsumidores = new JTextField();
		cantidadConsumidores.setBorder(BorderFactory.createTitledBorder("# Consumidores"));
		tamanioBuffer = new JTextField();
		tamanioBuffer.setBorder(BorderFactory.createTitledBorder("Tamaño Buffer "));
		cantidadIteraciones = new JTextField();
		cantidadIteraciones.setBorder(BorderFactory.createTitledBorder("# iteraciones "));
		JButton botonReiniciarSimulacion = new JButton("INICIAR");
		botonReiniciarSimulacion.addActionListener(listener);
		botonReiniciarSimulacion.setActionCommand("INICIAR");

		panelContenData.add(cantidadConsumidores);
		panelContenData.add(cantidadProductores);
		panelContenData.add(tamanioBuffer);
		panelContenData.add(cantidadIteraciones);

		add(panelContenData,BorderLayout.CENTER);
		add(botonReiniciarSimulacion,BorderLayout.PAGE_END);
	}

	public String getProductores() {
		return cantidadProductores.getText();
	}

	public String getConsumidores() {
		return cantidadConsumidores.getText();
	}

	public String getTamanioBuffer() {
		return tamanioBuffer.getText();
	}
	
	public String getCantidadIteraciones() {
		return cantidadIteraciones.getText();
	}

}
