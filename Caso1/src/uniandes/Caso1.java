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
			//Cargamos la configuracion del archivo propiedades.txt
			File propiedades=new File("data/propiedades.txt");
			FileReader fr = new FileReader(propiedades);
			BufferedReader lector = new BufferedReader(fr);
			ArrayList<Cliente> tempClientes=new ArrayList<Cliente>();

			String lineaActual = lector.readLine();	
			
			//Cada linea hasta que haya un espacio tendra un numero, que representa el numero de consultas que hace el cliente
			while(lineaActual.equals(""))
			{	
				System.out.println(lineaActual);
				Cliente c=new Cliente(Integer.parseInt(lineaActual),buffer);
				tempClientes.add(c);
				lineaActual = lector.readLine();
			}
			//Leemos el espacio para acceder al numero de servidores
			
			lector.readLine();
			numServidores=Integer.parseInt(lector.readLine());
			lector.close();
			int max;
			
			//En un for hacemos la inicializacion de los threads cliente y servidor
			if(numServidores<=tempClientes.size())
			{
				// moddificado para revisar orden de creacion de el cliente y los servidores.
				max=tempClientes.size();
				for(int i=0;i<max;i++)
				{
					tempClientes.get(i).start();
					if(i<numServidores){
						Servidor s;
						s=new Servidor(buffer);
						s.start();
					}
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
