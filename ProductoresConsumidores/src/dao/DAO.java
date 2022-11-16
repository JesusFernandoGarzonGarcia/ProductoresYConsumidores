package dao;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Esta clase define el contralador del programa, esto quiere decir que esta clase nos permitira  unir la parte grafica y la parte logica del programa 
 * @author: Jesus Garzon
 * @version: 02/11/2022/A
 */
public class DAO {

	//Campos de la clase
	Queue<Productor> productores = new LinkedList<>();
	Queue<Productor> consumidores = new LinkedList<>();
	String[] buffer;
	int datosLLenosEnBuffer;
	Productor consumidorActual = null;
	Productor productorActual = null;
	int timeSystem ;

	/**
	 * Constructor del contralador del programa
	 */
	public DAO() {
		timeSystem =0;
		datosLLenosEnBuffer =0;
	}

	/**
	 * llena la cola de productores 
	 * @param cantidad cantidad de productores a generar y agregrar a la cola
	 */
	public void addProductores(int cantidad) {
		productores = new LinkedList<>();
		for (int i = 0; i < cantidad; i++) {
			int time = (int) (Math.random()*5);
			productores.add(new Productor("Productor "+ (i+1),time+1));
		}
	}

	/**
	 * toma el primer productor de la cola y convierte su semaforo en true
	 * @return productor Actual 
	 */
	public Productor nextProductor() {
		productorActual = productores.remove();
		productorActual.getSemaforo().setTurno(true);
		return productorActual;
	}

	/**
	 * toma el primer Consumidor de la cola y convierte su semaforo en true
	 * @return consumidor Actual 
	 */
	public Productor nextConsumidor() {
		consumidorActual = consumidores.remove();
		consumidorActual.getSemaforo().setTurno(true);
		return consumidorActual;
	}

	/**
	 * convierte el semaforo del productor actual en false y lo agrega al final de la cola
	 */
	public void addProductor() {
		productorActual.getSemaforo().setTurno(false);
		productores.add(productorActual);
	}
	
	/**
	 * convierte el semaforo del consumidor actual en false y lo agrega al final de la cola
	 */
	public void addConsumidor() {
		consumidorActual.getSemaforo().setTurno(false);
		consumidores.add(consumidorActual);
	}


	/**
	 * valida el estado de los semaforos de productor actual y consumidor actual
	 * produce tiempo de espera para que el productor realice su operacion
	 * genera numero ramdon para la cantidad de espacios del buffer a utilizar 
	 * llena los datos en el buffer
	 */
	public void esperarProductor() throws InterruptedException{
		productorActual.getSemaforo().setTurno(true);
		consumidorActual.getSemaforo().setTurno(false);
		Thread.sleep(productorActual.getTimeUso()*1000);
		int dataAddBuffer = (int) (Math.random()*camposBufferVasios()+1);
		datosLLenosEnBuffer = dataAddBuffer;
		productorActual.setCamposAintroducir(dataAddBuffer);
		System.out.println(" datos llenos "+datosLLenosEnBuffer );
		for (int i = 0; i < datosLLenosEnBuffer; i++) {
			if (i<=datosLLenosEnBuffer) {
				buffer[i] ="T";
			}else {
				buffer[i] ="F";
			}
		}
	}

	/**
	 * verifica los espacios vacios en el buffer
	 * @return cantidad de espacios vacion en el buffer
	 */
	public int  camposBufferVasios() {
		int vacio =0;
		for (String string : buffer) {
			if (string.equalsIgnoreCase("F")) {
				vacio++;
			}

		}
		return vacio;
	}

	/**
	 * verifica los espacios utilizados(llenos) en el buffer
	 * @return cantidad de espacion utilizados (llenos) en el buffer
	 */
	public int  camposBufferLLenos() {
		int lleno =0;
		for (String string : buffer) {
			if (string.equalsIgnoreCase("T")) {
				lleno++;
			}
		}
		return lleno;
	}

	/**
	 * valida el estado de los semaforos de productor actual y consumidor actual
	 * produce tiempo de espera para que el consumidor realice su operacion
	 * genera numero ramdon para la cantidad de espacios del buffer a tomar 
	 * toma los datos en el buffer
	 */
	public void esperarConsumidor() throws InterruptedException{
		productorActual.getSemaforo().setTurno(false);
		consumidorActual.getSemaforo().setTurno(true);
		Thread.sleep(consumidorActual.getTimeUso()*1000);
		int dataAddBuffer = (int) (Math.random()*camposBufferLLenos()+1);
		consumidorActual.setCamposAintroducir(dataAddBuffer);
		datosLLenosEnBuffer -= dataAddBuffer;
		System.out.println("datos llenos " +datosLLenosEnBuffer);

		for (int i = 0; i<buffer.length; i++) {
			if (i<datosLLenosEnBuffer) {
				buffer[i] ="T";
			}else {
				buffer[i] ="F";
			}
		}
	}


	/**
	 * llena la cola de consumidores 
	 * @param cantidad cantidad de consumidores a generar y agregrar a la cola
	 */
	public void addConsumidores(int cantidad) {
		consumidores = new LinkedList<>();
		for (int i = 0; i < cantidad; i++) {
			int time = (int) (Math.random()*5);
			consumidores.add(new Productor("Consumidor "+ (i+1),time+1));
		}
	}

	/**
	 * llena el vector que representa nuestro buffer
	 * @param cantidad cantidad de posiciones disponibles en el buffer
	 */
	public void addPositionBuffer(int cantidad) {
		buffer = new String[cantidad];
		for (int i = 0; i < cantidad; i++) {
			buffer[i]= "F";
		}
	}

	/**
	 * nos permite obtener el vector que representa el buffer
	 * @return String[] "buffer" con valores engre T y F
	 */
	public String[] getBuffer() {
		return buffer;
	}

	/**
	 * nos permite obtener la lista de consumidores
	 * @return Queue que contiene los consumidores
	 */
	public Queue<Productor> getConsumidores() {
		return consumidores;
	}

	/**
	 * nos permite obtener la lista de productores
	 * @return Queue que contiene los productores
	 */
	public Queue<Productor> getProductores() {
		return productores;
	}

	/**
	 * nos permite obtener el productor actual
	 * @return productor actual
	 */
	public Productor getProductorActual() {
		return productorActual;
	}

	/**
	 * nos permite obtener el consumidor actual
	 * @return consumidor actual
	 */
	public Productor getConsumidorActual() {
		return consumidorActual;
	}

}
