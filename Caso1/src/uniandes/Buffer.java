package uniandes;

import java.util.ArrayList;

/**
 * Clase que representa el Buffer del caso 1
 * @author Federico Posada 201616735 - Alvaro Yepes 201618363
 *
 */
public class Buffer {

	//Numero de clientes que debe atender el buffer, si es 0, los servidores dejan de trabajar
	private int numClientes;	
	//Capacidad limitada del buffer
	private int capMax;
	//Estructura que almacena los mensajes
	private ArrayList<Mensaje> buffer;
	
	
	/**
	 * Constructor del buffer
	 * @param cMax capacidad maxima del buffer, segunda linea del archivo propiedades.txt
	 * @param numC numero de clientes esperados, primera linea del archivo propiedades.txt
	 */
	public Buffer(int cMax, int numC)
	{
		numClientes=numC;
		capMax=cMax;
		buffer=new ArrayList<Mensaje>();
	}

	/**
	 * Este metodo es llamado por los servidores para responder los mensajes.
	 * @return true si aun hay clientes por atender, false de lo contrario
	 */
	public synchronized boolean responder() {

		//si ya no hay clientes se termina
		if(numClientes==0)
			return false;

		while (buffer.size()==0) {
			if(numClientes==0)
				return false;
			try {
				wait();
			} catch (InterruptedException e) {}
		}
				
		//El servidor toma el mensaje y le suma 1 (responde)
		Mensaje m = buffer.remove(0);
		m.responder();
		
		synchronized (this) 
		{
			notifyAll();
		}
		return true;
	}

	/**
	 * Este metodo es llamado por los Clientes para colocar un mensaje en el buffer
	 * @param m mensaje del cliente
	 */
	public synchronized void colocar(Mensaje m) {
		
		while (buffer.size()==capMax) {
			Thread.yield();
		}		
		
		buffer.add(m);
		notifyAll();
		
		synchronized (this) {			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}		
	}
	
	/**
	 * Este metodo es llamado por los clientes para avisar que se retiran.
	 */
	public synchronized void retirarCliente()
	{
		numClientes--;
		if(numClientes==0)
			notifyAll();
	}
	
	
}
