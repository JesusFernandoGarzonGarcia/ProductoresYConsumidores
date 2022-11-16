package dao;

public class Semaforo {

	boolean turno;
	
	public Semaforo(boolean turno) {
		this.turno = turno;
	}
	
	public void setTurno(boolean turno) {
		this.turno = turno;
	}
	
	public boolean isTurno() {
		return turno;
	}

}
