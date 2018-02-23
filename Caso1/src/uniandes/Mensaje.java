package uniandes;

public class Mensaje {
	
	private int contenido;
	
	public Mensaje(int c)
	{
		contenido=c;
	}

	public synchronized int getContenido() {
		return contenido;
	}

	public synchronized void responder() {
		this.contenido++;
	}
	
	

}
