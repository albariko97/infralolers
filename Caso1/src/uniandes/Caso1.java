package uniandes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Caso1 {
	
	public static int numServidores=0;
	public static Buffer buffer=new Buffer(12);
	
	public static void main(String[] args)
	{		
		try {
			File propiedades=new File("data/propiedades.txt");
			FileReader fr = new FileReader(propiedades);
			BufferedReader lector = new BufferedReader(fr);
			ArrayList<Cliente> tempClientes=new ArrayList<Cliente>();

			String lineaActual = lector.readLine();		
			
			while(lineaActual != null)
			{	
				Cliente c=new Cliente(Integer.parseInt(lineaActual),buffer);
				tempClientes.add(c);
				lineaActual = lector.readLine();
			}
			lector.readLine();
			numServidores=Integer.parseInt(lector.readLine());
			lector.close();
			int max;
			if(numServidores<=tempClientes.size())
			{
				max=tempClientes.size();
				for(int i=0;i<max;i++)
				{
					if(i<numServidores){
						Servidor s;
						s=new Servidor(buffer);
						s.start();
					}
					tempClientes.get(i).start();
				}
			}
			else
			{
				max=numServidores;
				for(int i=0;i<max;i++)
				{
					if(i<tempClientes.size()){
						tempClientes.get(i).start();
					}
					Servidor s;
					s=new Servidor(buffer);
					s.start();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no existe");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
