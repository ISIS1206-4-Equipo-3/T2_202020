package view;

import modeloLogico.Modelo;

public class View {

	public View()
	{
		
	}
	
	public void printMenu()
	{
		System.out.println("1. Encontrar buenas peliculas de un director");
		System.out.println("2. Crear ranking de peliculas");
		System.out.println("3. Conocer informacion sobre un director");
		System.out.println("4. Conocer informacion sobre un actor");
		System.out.println("5. Conocer caracteristicas de un g�nero de peliculas");
		System.out.println("6. Crear ranking de un genero de peliculas");
		System.out.println("7. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		
	
	public void printModelo(Modelo modelo)
	{

	}
	
}
