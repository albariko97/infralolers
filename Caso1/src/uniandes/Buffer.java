package uniandes;

import java.util.ArrayList;

public class Buffer {

	private int numClientes;	
	private int capMax;
	private int capActual;	
	private ArrayList<Mensaje> buffer;
	
	public Buffer(int c)
	{
		numClientes=0;
		capActual=0;
		capMax=c;
		buffer=new ArrayList<Mensaje>();
	}

	public synchronized boolean responder() {

		//si ya no hay clientes se termina
		if(numClientes==0)
			return false;
		while (capActual==0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		//El servidor toma el mensaje y le suma 1 (responde)
		buffer.get(0).responder();
		buffer.get(0).notify();
		return true;
	}

	public synchronized void colocar(Cliente c,Mensaje m) {
		numClientes++;
		while (capActual==capMax) {
			c.yield();
		}
		//Guardamos la respuesta esperada para que pueda retirarse
		int respEsperada=m.getContenido()+1;
		buffer.add(m);
		capActual++;
		while(m.getContenido()!=respEsperada) 
		{
			try {
				m.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		capActual--;
		buffer.remove(0);
		numClientes--;
		notifyAll();		
	}
	
	
	
}
