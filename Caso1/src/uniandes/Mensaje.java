package uniandes;

/**
 * Clase que representa un mensaje en el caso 1
 * @author Federico Posada 201616735 - Alvaro Yepes 201618363
 *
 */
public class Mensaje {
	
	private int contenido;
	
	/**
	 * Constructor de un mensaje
	 * @param c Contenido del mensaje
	 */
	public Mensaje(int c)
	{
		contenido=c;
	}

	/**
	 * @return contenido del mensaje
	 */
	public synchronized int getContenido() {
		return contenido;
	}

	/**
	 * Suma 1 al mensaje, para responderlo.
	 */
	public synchronized void responder() {
		this.contenido++;
	}
	
	

}
