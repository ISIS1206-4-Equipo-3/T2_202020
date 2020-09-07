package controller;

import java.util.Scanner;
import view.View;
import modeloLogico.Modelo;

public class controlador {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	public controlador()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run()
	{
		Scanner lectura = new Scanner(System.in);
		boolean acabar = false;
		Integer dato = null;
		Comparable rta = " ";

		while(!acabar)
		{
			view.printMenu();
			boolean acabarOpcion1 = false;
			int opcion = Integer.parseInt(lectura.nextLine());
			switch(opcion) {

			case 2:
				view.printMessage("-------- Cargando informacion con Arreglo Dinamico -------- \n");
				modelo.CargarModelo(modelo.NUMERO_OPCION_DE_CARGA_ARREGLODINAMICO);
				int centinela = 0;
				while (!acabarOpcion1) {
					if (centinela==0) 
					{
						modelo.copiarMatriz();
						modelo.imprimirPeliculaArregloDinamico();
						centinela++;
					}
					
					view.printMenuOpcion2();
					
					int opcionDos = Integer.parseInt(lectura.nextLine());
					switch(opcionDos) {

					case 1:
						view.printMessage(" \n Encontrar peliculas buenas de un director \n Dar nombre del director: ");
						String director_name = lectura.nextLine();
						rta = modelo.darPeliculasDeUnDirector(director_name);
						if ( rta != null)
						{
							view.printMessage("Peliculas buenas del director "+director_name+ "\n"+ rta);

						}
						else
						{
							view.printMessage("Director no encontrado");
						}							
						break;
					
					case 2:
						view.printMessage("Estas son las peliculas que contiene el archivo:");
						view.printMessage(modelo.darListaPeliculas());
						view.printInstruccionesDeEntradaReq6();
						String numeroPeliculas = lectura.nextLine();
						view.printReq6Orden();
						int ordenPeliculas = Integer.parseInt(lectura.nextLine());
						view.printTipoOrden();
						int parametroOrdenamiento = Integer.parseInt(lectura.nextLine());
						view.printMessage(modelo.crearRankingPeliculas(numeroPeliculas, ordenPeliculas, parametroOrdenamiento));
						break;
					case 5:
						view.printMessage("\nPorfavor introduzca el nombre del genero a buscar:");
						String genero = lectura.nextLine();
						if(genero.length()<5) view.printMessage("+CAUTION: Es necesario introducir un nombre del genero a buscar\n");
						else{view.printMessage(modelo.entenderUnGenero(genero) + "\n");}
						break;
					case 6:
						view.printMessage("\nPorfavor introduzca el nombre del genero a rankear:");
						genero = lectura.nextLine();
						if(genero.length()<5) view.printMessage("+CAUTION: Es necesario introducir un nombre del genero a buscar\n");
						else{
							String listaGenero = modelo.crearRankingGeneroPrimerLlamado(genero);
							if(listaGenero.contains("No se encontraron peliculas con el genero")) {
								view.printMessage(listaGenero);
								
							}else {
								view.printMessage(listaGenero);
								view.printInstruccionesDeEntradaReq6();
								String entrada = lectura.nextLine();
								view.printReq6Orden();
								int orden = Integer.parseInt(lectura.nextLine());
								view.printMessage(modelo.crearRankingGeneroSegundoLlamado(entrada, orden));
							}
							break;
						}
					case 7: 
						acabarOpcion1 =true;
						break;
					}
				}
				break;
			case 1:
				view.printMessage("-------- Cargando informacion con Lista Encadenada -------- \n");
				modelo.CargarModelo(modelo.NUMERO_OPCION_DE_CARGA_LISTAENCADENADA);
				while (!acabarOpcion1) {
					view.printMenuOpcion1();
					int opcionDos = Integer.parseInt(lectura.nextLine());
					switch(opcionDos) {

					case 1:
						view.printMessage(" \n Imprimiendo informacion de las peliculas... \n");
						modelo.imprimirTodasLasPeliculas();
						break;
					case 2: 
						acabarOpcion1 =true;
						break;
					}
				}
				break;
			case 3:
				view.printMessage("Buscando peliculas con peor promedio de votacion");
				modelo.buscarPeoresPeliculas();
				break;
			case 4:
				view.printInformacionDeCreadores();
				break;
			case 5:
				view.printCambiarDatosACargar();
				int opcion1 = Integer.parseInt(lectura.nextLine());
				switch(opcion1) {
				case 1:
					modelo.RUTA_DATOS_PRINCIPALES = "./data/small/MoviesCastingRaw-small.csv";
					modelo.RUTA_DATOS_SECUNDARIOS = "./data/small/SmallMoviesDetailsCleaned.csv";
					view.printMessage("\nSe han establecido los datos pequeños para cargar.\n");
					break;
				case 2:
					modelo.RUTA_DATOS_PRINCIPALES = "./data/large/AllMoviesCastingRaw.csv";
					modelo.RUTA_DATOS_SECUNDARIOS = "./data/large/AllMoviesDetailsCleaned.csv";
					view.printMessage("\nSe han establecido los datos grandes para cargar.\n");
					break;
				}
				break;
			case 6:
				acabar =true;
				break;
			}
		}
	}

}
