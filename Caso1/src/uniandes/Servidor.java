package uniandes;

public class Servidor extends Thread implements Runnable {
	
	private Buffer buffer;
	
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
		System.out.println("Era un se�or servidor ahora me voy a mimir");
	}

}
