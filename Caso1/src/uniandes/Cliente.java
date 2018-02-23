package uniandes;

import java.util.Random;

public class Cliente extends Thread implements Runnable {
	
	private int numConsultas;
	private Buffer buffer;
	
	public Cliente(int n, Buffer b)
	{
		numConsultas=n;
		buffer=b;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int contador=1;
		Random random=new Random();
		while(contador<=numConsultas)
		{
			Mensaje m=new Mensaje(random.nextInt(10));
			buffer.colocar(this,m);
		}
		
		System.out.println("Era un señor cliente terminé me voy a mimir");

	}	




}
