package dao;

public class Productor {
	
	private String name;
	private Semaforo semaforo;
	private int timeUso;
	private int camposAintroducir;
	
	public Productor(String name, int timeUso) {
		this.name = name;
		this.timeUso = timeUso;
		semaforo = new Semaforo(false);
		camposAintroducir =0;
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getTimeUso() {
		return timeUso;
	}
	
	public void setCamposAintroducir(int camposAintroducir) {
		this.camposAintroducir = camposAintroducir;
	}
	
	
	public int getCamposAintroducir() {
		return camposAintroducir;
	}
	
	
	public Semaforo getSemaforo() {
		return semaforo;
	}
	
}
