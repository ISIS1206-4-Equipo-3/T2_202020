package view;

import modeloLogico.Modelo;

public class View {

	public View()
	{
		
	}
	
	public void printMenu()
	{
		System.out.println("1. Cargar los datos con lista encadenada");
		System.out.println("2. Cargar los datos con Arreglo Dinamico");
		System.out.println("3. Encontrar peliculas con peor promedio de votacion");
		System.out.println("4. Informacion de creadores");
		System.out.println("5. Cambiar datos a cargar");
		System.out.println("6. Exit");
	}
	
	public void printMenuOpcion2()
	{
		System.out.println("1. Encontrar buenas peliculas de un director");
		System.out.println("2. Crear ranking de peliculas");
		System.out.println("3. Conocer informacion sobre un director");
		System.out.println("4. Conocer informacion sobre un actor");
		System.out.println("5. Entender un genero cinematografico");
		System.out.println("6. Crear ranking de un genero de peliculas");
		System.out.println("7. <--- REGRESAR");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		
	
	public void printModelo(Modelo modelo)
	{
		
	}

	public void printMenuOpcion1() {
		System.out.println("1. Imprimir todas las peliculas de la lista encadenada");
		System.out.println("2. <--- REGRESAR");
	}
	
	public void printInformacionDeCreadores(){
		System.out.println("\nCreado por:");
		System.out.println("Alejandro Alcaraz 201921767");
		System.out.println("Alejandro Ahogado 201920701");
		System.out.println("Santiago Triana 201923265");
		System.out.println("Universidad de los Andes - Bogota, Colombia");
		System.out.println("Estructuras de Datos 202020_ISIS_1206_04 \n");
	}
	
	public void printCambiarDatosACargar() {
		System.out.println("\n1. Cargar datos de peliculas pequenos.");
		System.out.println("2. Cargar datos de peliculas grandes.");
	}
	
	public void printReq6Orden() {
		System.out.println("¿En que orden desea organizar el ranking?");
		System.out.println("1. Ascendente");
		System.out.println("2. Descendente");
	}
	public void printTipoOrden()
	{
		System.out.println("Con que criterio quiere organizar el ranking?");
		System.out.println("1. Numero de votos");
		System.out.println("2. Promedio de votos");
	}
	
	public void printInstruccionesDeEntradaReq6() {
		System.out.println("Por favor introduzca los numeros de las peliculas que desea rankear, tenga en cuenta que:");
		System.out.println("  -Deben seguir un formato separado por comas ej: 1,2,3,4,5,6,7,8,9,10");
		System.out.println("  -Deben ser minimo 10 peliculas");
		System.out.println("  -Los numeros ingresados deben estar entre los impresos anteriormente");
	}
	
}
