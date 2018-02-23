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

		if(numClientes==0)
			return false;
		while (capActual==0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		capActual--;
		Mensaje m=buffer.remove(0);
		m.notify();
		return true;
	}

	public synchronized void colocar(Cliente c,Mensaje m) {
		numClientes++;
		while (capActual==capMax) {
			c.yield();
		}
		int respEsperada=m.getContenido()+1;
		buffer.add(m);
		capActual++;
		while(m.getContenido()!=respEsperada) //esto no esta bien
		{
			try {
				m.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		numClientes--;
		
	}
	
	
	
}
