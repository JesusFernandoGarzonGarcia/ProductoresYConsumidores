package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import dao.DAO;
import dao.Productor;
import views.ViewsPrincipal;

/**
 * Esta clase define el controlador del simulador, esto quiere decir que esta clase nos permitira unir la parte grafica y la parte logica del simulador propuesto 
 * @author: Samuel lopez
 * @version: 14/11/2022/A
 */

public class Controller implements ActionListener{
	//Campos de la clase
	ViewsPrincipal views ;
	DAO dao ;
	Thread inicioProceso;

	/**
	 * Constructor del contralador del programa
	 */
	public Controller() {
		dao = new DAO();
		ActionListener listener = this;
		views = new ViewsPrincipal(listener);
	}
	//Cierre del constructors
	
	
	/**
	 * Método que toma los datos iniciales de configuracion ingresados por el usario, esto con el objetivo de iniciar la simulacion 
	 */
	public void iniciarSimulacion() {
		try {
			int productores=Integer.parseInt(views.getProductores());
			int consumidores =Integer.parseInt(views.getConsumidores());
			int tamanioBuffer =Integer.parseInt(views.getTamanioBuffer());
			int cantidadIteraciones =Integer.parseInt(views.getCantidadIteraciones());
			dao.addConsumidores(productores);
			dao.addProductores(consumidores);
			dao.addPositionBuffer(tamanioBuffer);
			iniciarHilo(cantidadIteraciones);
		} catch (Exception e) {
			views.menssage(views,"ingresa datos validos");
		}

	}
	
	
	/**
	 * Método el cual inicializa y ejecuta el hilo encargado de hacer correr la simulacion
	 */
	private void iniciarHilo(int cantidadIteraciones) {
		if (inicioProceso!=null) {
			inicioProceso.interrupt();
		}
		inicioProceso = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println(inicioProceso.getId() + inicioProceso.getName());
					views.actualizarListaConsumidores(dao.getConsumidores());
					views.actualizarListaProductores(dao.getProductores());
					views.actualizarBuffer(dao.getBuffer());
					
					for (int i = 0; i < cantidadIteraciones; i++) {
						dao.nextProductor();
						dao.nextConsumidor();
						views.actualizarProductorActual(procesoProductor());
						views.actualizarBuffer(dao.getBuffer());
						views.actualizarEstadoProductor(dao.getProductorActual().getSemaforo().isTurno());
						views.actualizarListaProductores(dao.getProductores());
						dao.addProductor();
						
						views.actualizarConsumidorActual(procesoConsumidor());
						views.actualizarBuffer(dao.getBuffer());
						views.actualizarEstadoConsumidor(dao.getConsumidorActual().getSemaforo().isTurno());
						views.actualizarListaConsumidores(dao.getConsumidores());
						dao.addConsumidor();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		inicioProceso.start();
	}

	/**
	 * Método el cual ejecuta de manera secuencial los metodos logicos de un productor y los metodos para actualizar los datos visuales del mismo
	 * @return Productor actual
	 */
	public Productor procesoProductor() throws InterruptedException {
		dao.esperarProductor();
		views.actualizarEstadoProductor(dao.getProductorActual().getSemaforo().isTurno());
		views.actualizarEstadoConsumidor(dao.getConsumidorActual().getSemaforo().isTurno());
		return dao.getProductorActual();
	}
	
	/**
	 * Método el cual ejecuta de manera secuencial los metodos logicos de un consumidor y los metodos para actualizar los datos visuales del mismo
	 * @return Consumidor actual
	 */
	public Productor procesoConsumidor() throws InterruptedException {
		dao.esperarConsumidor();
		views.actualizarEstadoProductor(dao.getProductorActual().getSemaforo().isTurno());
		views.actualizarEstadoConsumidor(dao.getConsumidorActual().getSemaforo().isTurno());
		return dao.getConsumidorActual();
	}

	/**
	 * Método el cual sobre escribe el metodo actionListener el cual es implementado por el controlador para capturar las acciones de la parte visual
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "INICIAR":
			iniciarSimulacion();
			break;
		}
	}

	/**
	 * Método main de la palicacion
	 */
	public static void main(String[] args) {
		new Controller();
	}

}
