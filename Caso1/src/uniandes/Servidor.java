package uniandes;

/**
 * Clase que representa un thread servidor del caso 1
 * @author Federico Posada 201616735 - Alvaro Yepes 201618363
 *
 */
public class Servidor extends Thread implements Runnable {
	
	private static Buffer buffer;
	
	/**
	 * Constructor del servidor
	 * @param b Buffer que todos usan
	 */
	public Servidor(Buffer b)
	{
		buffer=b;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean siga=true;
		while(siga)
		{
			siga=buffer.responder();
		}
	}

}
