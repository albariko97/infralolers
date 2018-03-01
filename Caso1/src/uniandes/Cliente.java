package uniandes;

import java.util.Random;

/**
 * Clase que representa un cliente en el caso 1
 * @author Federico Posada 201616735 - Alvaro Yepes 201618363
 *
 */
public class Cliente extends Thread implements Runnable {

	private int numConsultas;
	private static Buffer buffer;
	private int id;

	/**
	 * Constructor del Cliente
	 * @param n Numero de consultas, asignadas en el archivo propiedades.txt
	 * @param b Buffer que usan todos
	 * @param i Identificador para llevar registro de los clientes
	 */
	public Cliente(int n, Buffer b,int i)
	{
		numConsultas=n;
		buffer=b;
		id=i;
	}

	@Override
	public void run() {

		int contador=1;
		while(contador<=numConsultas)
		{
			Mensaje m=new Mensaje(contador);
			
			synchronized (m) {
				try {
					buffer.colocar(m);
					m.wait();
					//Cuando llega aqu� es porque el mensaje ya ha cambiado
					System.out.println("Id de cliente: "+id+" : Mensaje Original: "+contador+", Mensaje respondido: "+m.getContenido());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
			contador++;
		}

		buffer.retirarCliente();
		System.out.println("El cliente "+id+" acaba su ejecuci�n.");

	}	




}
