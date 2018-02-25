package uniandes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase principal para el Caso 1.
 * @author Federico Posada 201616735 - Alvaro Yepes 201618363
 *
 */
public class Caso1 {
	
	public static int numServidores=0;
	
	public static void main(String[] args)
	{		
		try {
			
			//Cargamos la configuracion del archivo propiedades.txt
			File propiedades=new File("data/propiedades.txt");
			FileReader fr = new FileReader(propiedades);
			BufferedReader lector = new BufferedReader(fr);
			ArrayList<Cliente> tempClientes=new ArrayList<Cliente>();

			//La primera linea indica el número de clientes esperados
			String lineaActual = lector.readLine();	
			int numClientes=Integer.parseInt(lineaActual);
			//La segunda linea indica la capacidad del buffer
			lineaActual = lector.readLine();	
			int capMax=Integer.parseInt(lineaActual);
			
			//Iniciamos el buffer
			Buffer buffer=new Buffer(capMax, numClientes);
			System.out.println("Se crea el buffer con capacidad "+capMax+" para atender a "+numClientes+" clientes.");
			
			//Saltamos un espacio
			lineaActual=lector.readLine();
			
			//Cada linea hasta que haya un espacio tendra un numero, que representa el numero de consultas que hace el cliente
			int contadorId=1;
			int contadorClientes=0;
			lineaActual=lector.readLine();
			
			while(!lineaActual.equals(""))
			{	
				System.out.println(lineaActual);
				Cliente c=new Cliente(Integer.parseInt(lineaActual),buffer,contadorId);
				tempClientes.add(c);
				lineaActual = lector.readLine();
				contadorId++;
				contadorClientes++;
			}
			
						
			if(contadorClientes!=numClientes) {
				lector.close();
				throw new Exception("Error: Se esperaban "+numClientes+" clientes y se inicializaron "+contadorClientes+".\nEl número de clientes está al inicio de propiedades.txt");
			}
			
			//Leemos el espacio para acceder al numero de servidores			
			
			System.out.println("Linea actual: "+lineaActual);
			numServidores=Integer.parseInt(lector.readLine());
			System.out.println("Se crearan "+numServidores+" servidores");
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
