package modeloLogico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import modeloEstructuraDatos.ArregloDinamico;
import modeloEstructuraDatos.ListaEncadenada;
import modeloEstructuraDatos.Pelicula;
import modeloEstructuraDatos.ShellSort;

public class Modelo {

	public ArregloDinamico datos;
	public ListaEncadenada<Pelicula> datosEncadenados;

	//Este atributo es excusivo para el requerimiento "Crear ranking del genero".
	private ArregloDinamico arregloDinamicoDeGeneroBuscado;

	private ArregloDinamico arregloDePelicula;

	public String RUTA_DATOS_PRINCIPALES= "./data/small/MoviesCastingRaw-small.csv";
	public String RUTA_DATOS_SECUNDARIOS= "./data/small/SmallMoviesDetailsCleaned.csv";

	public final static int NUMERO_OPCION_DE_CARGA_LISTAENCADENADA = 1;
	public final static int NUMERO_OPCION_DE_CARGA_ARREGLODINAMICO = 2;

	private final static int CRITERIO_NUM_VOTOS = 1;
	private final static int CRITERIO_PROMEDIO_VOTOS = 2;
	private final static int COLUMNA_DIRECTORES = 12;
	private final static int COLUMNA_CALIFICACIONES = 35;
	private final static int COLUMNA_ID = 0;	
	private final static int COLUMNA_TITULO = 34 ;
	private final static int COLUMNA_GENERO = 20;
	private final static int COLUMNA_RELEASE_DATE = 28 ;
	private final static int COLUMNA_ACTOR_1 = 1 ;
	private final static int COLUMNA_ACTOR_2 = 3 ;
	private final static int COLUMNA_ACTOR_3 = 5 ;
	private final static int COLUMNA_ACTOR_4 = 7 ;
	private final static int COLUMNA_ACTOR_5 = 9 ;
	private final static int COLUMNA_NUM_CALIFICACIONES = 36; 

	private FileReader archivoPrincipal;
	private CSVReader lectorPrincipal;
	private Comparable[] arreglo;
	//private ShellSort sort;
	private FileReader archivoSecundario;
	private CSVReader lectorSecundario;

	public Modelo() {

	}

	public void CargarModelo (int opcionDeCarga) {
		if(opcionDeCarga==NUMERO_OPCION_DE_CARGA_ARREGLODINAMICO) {
			datos = new ArregloDinamico(1,1);
			cargarDatos(RUTA_DATOS_PRINCIPALES, RUTA_DATOS_SECUNDARIOS);
		}
		if(opcionDeCarga==NUMERO_OPCION_DE_CARGA_LISTAENCADENADA) {
			datosEncadenados = new ListaEncadenada();
			cargarDatosEncadenados(RUTA_DATOS_PRINCIPALES, RUTA_DATOS_SECUNDARIOS);
		}
	}

	public String darPeliculasDeUnDirector(String pNombre) {
		String rta = null;
		boolean existeDirector = false;
		int contadorBuenasPeliculas=0;
		String info = "";
		String actores = "";
		double sumatoriaCalificaciones=0;
		for(int i=0; i<datos.darTamanoFilas();i++) {
			if(pNombre.equals(datos.darElementoEn(COLUMNA_DIRECTORES,i))) 
			{				
				if(Double.parseDouble((String)datos.darElementoEn(COLUMNA_CALIFICACIONES,i))>=6.0) 
				{
					actores = "";
					for(int g = 1; g<10; g= g+2)
					{
						if(!datos.darElementoEn(g,i).equals("none"))
						{
							actores += datos.darElementoEn(g,i) + ", ";
						}
					}
					++contadorBuenasPeliculas;
					info +="\n Pelicula " + contadorBuenasPeliculas + "\n >ID: " + datos.darElementoEn(COLUMNA_ID,i) + "\n >Titulo: " + datos.darElementoEn(COLUMNA_TITULO,i) + "\n >Genero(s): " + datos.darElementoEn(COLUMNA_GENERO,i) + 
							"\n >Fecha de lanzamiento: " + datos.darElementoEn(COLUMNA_RELEASE_DATE,i) + "\n >Actores: " + actores + "\n";
					sumatoriaCalificaciones+=Double.parseDouble((String)datos.darElementoEn(COLUMNA_CALIFICACIONES,i));

				}
				existeDirector=true;
			}
		} 
		double promediodeVotacion = 0;
		if(contadorBuenasPeliculas!=0)promediodeVotacion =(double) sumatoriaCalificaciones/contadorBuenasPeliculas;
		if(existeDirector) rta = "El director "+pNombre+" tiene "+ contadorBuenasPeliculas+" peliculas buenas o\n" + 
				"con votacion positiva.\n" + "El promedio de votacion de estas peliculas es de " + promediodeVotacion + "\n info sobre buenas peliculas: \n"
				+ info;
		return rta;
	}
//	FaltanTest
	public String conocerUnDirector(String pNombre) {
		String respuesta = null;
		boolean existeDirector = false;
		int contadorPeliculas=0;
		String informacion = "";
		String actores = "";
		double sumatoriaCalificaciones=0;
		for(int i=0; i<datos.darTamanoFilas();i++) {
			if(pNombre.equals(datos.darElementoEn(COLUMNA_DIRECTORES,i))) 
			{				
				actores = "";
				for(int g = 1; g<10; g= g+2)
				{
					if(!datos.darElementoEn(g,i).equals("none"))
					{
						actores += datos.darElementoEn(g,i) + ", ";
					}
				}
				++contadorPeliculas;
				informacion +="\n Pelicula " + contadorPeliculas + "\n >ID: " + datos.darElementoEn(COLUMNA_ID,i) + "\n >Titulo: " + datos.darElementoEn(COLUMNA_TITULO,i) + "\n >Genero(s): " + datos.darElementoEn(COLUMNA_GENERO,i) + 
						"\n >Fecha de lanzamiento: " + datos.darElementoEn(COLUMNA_RELEASE_DATE,i) + "\n >Actores: " + actores + "\n";
				sumatoriaCalificaciones+=Double.parseDouble((String)datos.darElementoEn(COLUMNA_CALIFICACIONES,i));

				existeDirector=true;
			}
		} 
		double promediodeVotacion = 0;
		if(contadorPeliculas!=0)promediodeVotacion =(double) sumatoriaCalificaciones/contadorPeliculas;
		if(existeDirector) respuesta = "El director "+pNombre+" tiene "+ contadorPeliculas+" peliculas y \n" + "el promedio de votacion de estas peliculas es de " + promediodeVotacion + "\n info sobre peliculas: \n"
				+ informacion;
		return respuesta;
	}

	public String conocerUnActor(String pNombre) {
		boolean existeActor = false;
		int contadorPeliculas=0;
		int contadorDirectorNuevo=0;
		int contadorDirectorViejo=0;
		String respuesta = null;
		String directorMasColaboraciones="";
		String nombresPeliculas = "";
		String e = "";
		List<String> directores = new ArrayList<>();
		double sumatoriaCalificaciones=0;
		for(int i=0; i<datos.darTamanoFilas();i++) {
			if(pNombre.equals(datos.darElementoEn(COLUMNA_ACTOR_1,i))||pNombre.equals(datos.darElementoEn(COLUMNA_ACTOR_2,i))||pNombre.equals(datos.darElementoEn(COLUMNA_ACTOR_3,i))||pNombre.equals(datos.darElementoEn(COLUMNA_ACTOR_4,i))||pNombre.equals(datos.darElementoEn(COLUMNA_ACTOR_5,i))) 
			{	
				nombresPeliculas += " | "+ datos.darElementoEn(COLUMNA_TITULO, i);
				e = (String)datos.darElementoEn(COLUMNA_DIRECTORES, i);
				directores.add(e);
				++contadorPeliculas;
				sumatoriaCalificaciones+=Double.parseDouble((String)datos.darElementoEn(COLUMNA_CALIFICACIONES,i));
				existeActor=true;
			}
		} 

		for (int i= 0; i < directores.size(); i++) {
			for (int y = 1; y < directores.size(); y++) {
				if (directores.get(i).equals(directores.get(y)))
					contadorDirectorNuevo +=1;
			}
			if(contadorDirectorViejo<contadorDirectorNuevo){
				contadorDirectorViejo=contadorDirectorNuevo;
				directorMasColaboraciones = directores.get(i);
			}
			contadorDirectorNuevo = 0;
		}
		double promediodeVotacion = 0;
		if(contadorPeliculas!=0)promediodeVotacion =(double) sumatoriaCalificaciones/contadorPeliculas;
		if(existeActor) respuesta = "El actor "+pNombre+" a participado en  "+ contadorPeliculas+" peliculas \n" + "Estas peliculas son: "+ nombresPeliculas +"\nEl promedio de votacion de estas peliculas es de " + promediodeVotacion + "\nEl director con el que ha tenido más colaboraciones es: "	+ directorMasColaboraciones +"\n";
		return respuesta;
	}

	public String entenderUnGenero(String entrada) {
		String rta = "";
		String nombresPeliculas = "";
		int contadorPeliculasGenero =0;
		double sumaDeVotos = 0;
		for(int i =0 ; i<datos.darTamanoFilas();i++) {
			String[] generos = datos.darElementoEn(COLUMNA_GENERO, i).toString().split("\\|");
			for(int j = 0; j<generos.length;j++) {
				if (generos[j].equalsIgnoreCase(entrada)) {
					nombresPeliculas += "- "+ datos.darElementoEn(COLUMNA_TITULO, i) + "\n";
					sumaDeVotos += Double.parseDouble(datos.darElementoEn(COLUMNA_NUM_CALIFICACIONES, i).toString());
					++contadorPeliculasGenero;
				}
			}
		}
		if(contadorPeliculasGenero>0) {
			rta += "Se han encontrado " + contadorPeliculasGenero + " peliculas del genero " + entrada + ":\n" + nombresPeliculas;
			double PromedioVotos = sumaDeVotos/contadorPeliculasGenero;
			rta += "\nTodas estas peliculas tienen un promedio de " + PromedioVotos + " votos. \n";
		}else {
			rta += "No se encontraron peliculas con el genero " + entrada;
		}
		return rta;
	}


	public String darListaPeliculas()
	{
		String resp = "";
		int contador = 1;
		arregloDePelicula = new ArregloDinamico<>(4, 1);
		for(int i =0 ; i<datos.darTamanoFilas();i++) {
			resp += contador + ". "+ datos.darElementoEn(COLUMNA_TITULO, i) + "\n";
			++contador;
			arregloDePelicula.agregar(contador, 0, contador-1);
			arregloDePelicula.agregar(datos.darElementoEn(COLUMNA_TITULO, i).toString(), 1, contador-1);
			arregloDePelicula.agregar(datos.darElementoEn(COLUMNA_NUM_CALIFICACIONES, i).toString(), 2, contador-1);
			arregloDePelicula.agregar(datos.darElementoEn(COLUMNA_CALIFICACIONES, i).toString(), 3, contador-1);

		}
		return resp;
	}


	public String crearRankingGeneroPrimerLlamado(String entrada) {
		String rta = "";
		String listaDePeliculas = "";
		arregloDinamicoDeGeneroBuscado = new ArregloDinamico<>(4, 1);
		int contador = 1;
		for(int i =0 ; i<datos.darTamanoFilas();i++) {
			if (datos.darElementoEn(COLUMNA_GENERO, i).toString().toLowerCase().contains(entrada.toLowerCase())) {
				listaDePeliculas += contador + ". "+ datos.darElementoEn(COLUMNA_TITULO, i) + "\n";
				++contador;
				arregloDinamicoDeGeneroBuscado.agregar(contador, 0, contador-1);
				arregloDinamicoDeGeneroBuscado.agregar(datos.darElementoEn(COLUMNA_TITULO, i).toString(), 1, contador-1);
				arregloDinamicoDeGeneroBuscado.agregar(datos.darElementoEn(COLUMNA_NUM_CALIFICACIONES, i).toString(), 2, contador-1);
				arregloDinamicoDeGeneroBuscado.agregar(datos.darElementoEn(COLUMNA_CALIFICACIONES, i).toString(), 3, contador-1);
			}
		}
		if(contador>1) {
			rta += "Se han encontrado las siguientes peliculas del genero " + entrada + ":\n" + listaDePeliculas;
		}else {
			rta += "No se encontraron peliculas con el genero " + entrada;
		}
		return rta;
	}

	public String crearRankingGeneroSegundoLlamado (String entrada, int orden) {
		try {
			String rta ="";
			String[] entradaSeparada = entrada.split(",");
			ArregloDinamico nuevoArregloAOrganizar =new ArregloDinamico<>(arregloDinamicoDeGeneroBuscado.darTamanoColumnas(), arregloDinamicoDeGeneroBuscado.darTamanoFilas());
			if (entradaSeparada.length<10) throw new Exception();
			int[] numerosDePeliculasARankear = new int[entradaSeparada.length];
			for(int i = 0; i<entradaSeparada.length;i++) {
				numerosDePeliculasARankear[i] = Integer.parseInt(entradaSeparada[i].trim());
			}
			for(int i = 0; i<numerosDePeliculasARankear.length;i++) {
				nuevoArregloAOrganizar.agregar((int)arregloDinamicoDeGeneroBuscado.darElementoEn(0, numerosDePeliculasARankear[i]), 0, i);
				nuevoArregloAOrganizar.agregar((String)arregloDinamicoDeGeneroBuscado.darElementoEn(1, numerosDePeliculasARankear[i]), 1, i);
				nuevoArregloAOrganizar.agregar(Integer.parseInt((String) arregloDinamicoDeGeneroBuscado.darElementoEn(2, numerosDePeliculasARankear[i])), 2, i);
				nuevoArregloAOrganizar.agregar(Double.parseDouble((String) arregloDinamicoDeGeneroBuscado.darElementoEn(3, numerosDePeliculasARankear[i])), 3, i);
			}
			ArregloDinamico arregloOrdenado = organizarRankingGenero(nuevoArregloAOrganizar, orden);
			rta += "Se ha creado el ranking por promedio de votaciones con los elementos dados :\n";
			for (int i = 0; i<arregloOrdenado.darTamanoFilas(); i++) {
				rta += "\n#"+(i+1)+" Titulo:" + arregloOrdenado.darElementoEn(1, i);
				rta += "\n    - Numero Votos:" + arregloOrdenado.darElementoEn(2, i);
				rta += "\n    - Promedio Votaciones:" + arregloOrdenado.darElementoEn(3, i) + "\n";
			}
			rta += "----------------------------------------------------------------------------\n";
			return rta;
		}catch (Exception e) {
			return "++CAUTION: Se encontro un error en el formato de la entrada porfavor asegurese que:\n  -Sean minimo 10 elementos\n  -Esten correctamente separados por comas\n  -Todos los numeros ingresados tengan una pelicula correspondiente \n \n";
		}

	}

	public String crearRankingPeliculas(String peliculas, int orden, int criterio)
	{
		try
		{
			String rta ="";
			String[] entradaSeparada = peliculas.split(",");
			ArregloDinamico nuevoArregloAOrganizar =new ArregloDinamico<>(arregloDePelicula.darTamanoColumnas(), arregloDePelicula.darTamanoFilas());
			if (entradaSeparada.length<10) throw new Exception();
			int[] numerosDePeliculasARankear = new int[entradaSeparada.length];
			for(int i = 0; i<entradaSeparada.length;i++) {
				numerosDePeliculasARankear[i] = Integer.parseInt(entradaSeparada[i].trim());
			}
			for(int i = 0; i<numerosDePeliculasARankear.length;i++) {
				nuevoArregloAOrganizar.agregar((int)arregloDePelicula.darElementoEn(0, numerosDePeliculasARankear[i]), 0, i);
				nuevoArregloAOrganizar.agregar((String)arregloDePelicula.darElementoEn(1, numerosDePeliculasARankear[i]), 1, i);
				nuevoArregloAOrganizar.agregar(Integer.parseInt((String) arregloDePelicula.darElementoEn(2, numerosDePeliculasARankear[i])), 2, i);
				nuevoArregloAOrganizar.agregar(Double.parseDouble((String) arregloDePelicula.darElementoEn(3, numerosDePeliculasARankear[i])), 3, i);
			}
			ArregloDinamico arregloOrdenado = organizarRankingPeliculas(nuevoArregloAOrganizar, orden, criterio);
			rta += "Se ha creado el ranking por promedio de votaciones con los elementos dados :\n";
			for (int i = 0; i<arregloOrdenado.darTamanoFilas(); i++) {
				rta += "\n#"+(i+1)+" Titulo:" + arregloOrdenado.darElementoEn(1, i);
				rta += "\n    - Numero Votos:" + arregloOrdenado.darElementoEn(2, i);
				rta += "\n    - Promedio Votaciones:" + arregloOrdenado.darElementoEn(3, i) + "\n";
			}
			rta += "----------------------------------------------------------------------------\n";
			return rta;
		}
		catch(Exception e)
		{
			return "++CAUTION: Se encontro un error en el formato de la entrada porfavor asegurese que:\n  -Sean minimo 10 elementos\n  -Esten correctamente separados por comas\n  -Todos los numeros ingresados tengan una pelicula correspondiente \n \n";
		}
	}

	private ArregloDinamico organizarRankingPeliculas(ArregloDinamico arregloAOrganizar, int orden , int criterio) 
	{
		if(criterio == CRITERIO_PROMEDIO_VOTOS) {
			Comparable[] listaOrganizar = new Comparable[arregloAOrganizar.darTamanoFilas()];
			ArregloDinamico rta = new ArregloDinamico<>(4, arregloAOrganizar.darTamanoFilas());
			for (int i = 0; i<arregloAOrganizar.darTamanoFilas(); i++) {
				listaOrganizar[i] = (double)arregloAOrganizar.darElementoEn(3, i);
			}
			ShellSort shellsort = new ShellSort ();
			shellsort.sort(listaOrganizar);
			for(int i = 0; i<arregloAOrganizar.darTamanoFilas(); i++) {
				int posicionDelElemento = buscarLaPosicionDelPromedioVotaciones(arregloAOrganizar, (double)listaOrganizar[i]);
				for(int j = 0; j<4;j++) {
					rta.agregar((Comparable)arregloAOrganizar.darElementoEn(j, posicionDelElemento), j, i);
				}
				arregloAOrganizar.agregar(null, 3, posicionDelElemento);
			}
			if(orden == 2) {
				int i =0;
				for (int j = (arregloAOrganizar.darTamanoFilas()-1); j>i;j--) {
					rta.intercambiarFila(i, j);
					i++;
				}
			}
			return rta;
		}
		else
		{
			Comparable[] listaOrganizar = new Comparable[arregloAOrganizar.darTamanoFilas()];
			ArregloDinamico rta = new ArregloDinamico<>(4, arregloAOrganizar.darTamanoFilas());
			for (int i = 0; i<arregloAOrganizar.darTamanoFilas(); i++) {
				listaOrganizar[i] = (int)arregloAOrganizar.darElementoEn(2, i);
			}
			ShellSort shellsort = new ShellSort ();
			shellsort.sort(listaOrganizar);
			for(int i = 0; i<arregloAOrganizar.darTamanoFilas(); i++) {
				int posicionDelElemento = buscarLaPosicionDelNumeroVotaciones(arregloAOrganizar, (int)listaOrganizar[i]);
				for(int j = 0; j<4;j++) {
					rta.agregar((Comparable)arregloAOrganizar.darElementoEn(j, posicionDelElemento), j, i);
				}
				arregloAOrganizar.agregar(null, 3, posicionDelElemento);
			}
			if(orden == 2) {
				int i =0;
				for (int j = (arregloAOrganizar.darTamanoFilas()-1); j>i;j--) {
					rta.intercambiarFila(i, j);
					i++;
				}
			}
			return rta;
		}
	}



	private ArregloDinamico organizarRankingGenero(ArregloDinamico arregloAOrganizar, int orden) 
	{
		Comparable[] listaOrganizar = new Comparable[arregloAOrganizar.darTamanoFilas()];
		ArregloDinamico rta = new ArregloDinamico<>(4, arregloAOrganizar.darTamanoFilas());
		for (int i = 0; i<arregloAOrganizar.darTamanoFilas(); i++) {
			listaOrganizar[i] = (double)arregloAOrganizar.darElementoEn(3, i);
		}
		ShellSort shellsort = new ShellSort ();
		shellsort.sort(listaOrganizar);
		for(int i = 0; i<arregloAOrganizar.darTamanoFilas(); i++) {
			int posicionDelElemento = buscarLaPosicionDelPromedioVotaciones(arregloAOrganizar, (double)listaOrganizar[i]);
			for(int j = 0; j<4;j++) {
				rta.agregar((Comparable)arregloAOrganizar.darElementoEn(j, posicionDelElemento), j, i);
			}
			arregloAOrganizar.agregar(null, 3, posicionDelElemento);
		}
		if(orden == 2) {
			int i =0;
			for (int j = (arregloAOrganizar.darTamanoFilas()-1); j>i;j--) {
				rta.intercambiarFila(i, j);
				i++;
			}
		}
		return rta;
	}
	private int buscarLaPosicionDelPromedioVotaciones (ArregloDinamico arregloABuscar, double promedio) {
		for(int i = 0; i< arregloABuscar.darTamanoFilas();i++) {
			if(arregloABuscar.darElementoEn(3, i)!=null) {
				if((double)arregloABuscar.darElementoEn(3, i) == promedio) return i;
			}
		}
		return -1;
	}
	private int buscarLaPosicionDelNumeroVotaciones (ArregloDinamico arregloABuscar, int numeroVotaciones) {
		for(int i = 0; i< arregloABuscar.darTamanoFilas();i++) {
			if(arregloABuscar.darElementoEn(2, i)!=null) {
				if((int)arregloABuscar.darElementoEn(2, i) == numeroVotaciones) return i;
			}
		}
		return -1;
	}

	public void cargarDatosEncadenados(String pRutaPrincipal, String pRutaSecundaria)
	{
		try {
			long startTime = System.nanoTime();
			datosEncadenados = new ListaEncadenada<>();
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			archivoPrincipal = new FileReader(pRutaPrincipal);
			archivoSecundario = new FileReader(pRutaSecundaria);
			lectorSecundario = new CSVReaderBuilder(archivoSecundario).withCSVParser(parser).build();
			lectorPrincipal = new CSVReaderBuilder (archivoPrincipal).withCSVParser(parser).build();
			String [] lineaPrincipal = lectorPrincipal.readNext();;
			String [] lineaSecundaria = lectorSecundario.readNext();
			int contador = 0;
			while(((lineaPrincipal = lectorPrincipal.readNext())!=null) && ((lineaSecundaria = lectorSecundario.readNext())!=null)){
				if(lineaPrincipal != null && lineaSecundaria != null) {
					int id = Integer.parseInt(lineaPrincipal[0]);
					int numVotos = Integer.parseInt(lineaSecundaria[18]);
					double promedioVotos = Double.parseDouble(lineaSecundaria[17]);

					String director = lineaPrincipal[COLUMNA_DIRECTORES];
					String actor1 = lineaPrincipal[COLUMNA_ACTOR_1];
					String actor2 = lineaPrincipal[COLUMNA_ACTOR_2];
					String actor3 = lineaPrincipal[COLUMNA_ACTOR_3];
					String actor4 = lineaPrincipal[COLUMNA_ACTOR_4];
					String actor5 = lineaPrincipal[COLUMNA_ACTOR_5];
					String genero = lineaSecundaria[2];
					String titulo = lineaSecundaria[16];
					String lanzamiento = lineaSecundaria[10];
					Pelicula anadir = new Pelicula(lanzamiento, titulo, id, director, numVotos, promedioVotos, actor1, actor2, actor3, actor4, actor5, genero);
					datosEncadenados.addFirst(anadir);
					contador++;
				}
			}
			long endTime = System.nanoTime();

			System.out.println("Primera pelicula");
			datosEncadenados.lastElement().imprimirPelicula();
			System.out.println("Ultima pelicula");
			datosEncadenados.firstElement().imprimirPelicula();
			System.out.println("-------- Los datos fueron cargados correctamente ("+contador+" peliculas) --------\n");
			System.out.println("Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void cargarDatos(String pRutaPrincipal, String pRutaSecundaria) {
		try {
			long startTime = System.nanoTime();
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			archivoPrincipal = new FileReader(pRutaPrincipal);
			archivoSecundario = new FileReader(pRutaSecundaria);
			lectorSecundario = new CSVReaderBuilder(archivoSecundario).withCSVParser(parser).build();
			lectorPrincipal = new CSVReaderBuilder (archivoPrincipal).withCSVParser(parser).build();
			String [] nextline;

			//Se inicia la carga de datos del archivo principal
			int cantidadFilas = 0;
			nextline = lectorPrincipal.readNext();
			while((nextline = lectorPrincipal.readNext())!=null) {
				if(nextline != null) {
					String[] temp = nextline;
					for(int i = 0; i<temp.length;i++) {
						try {
							int posibleNumero = Integer.parseInt(temp[i]);
							datos.agregar(posibleNumero, i, cantidadFilas);
						}catch(Exception e) {
							datos.agregar(temp[i], i, cantidadFilas);
						}
					}
					cantidadFilas++;
				}
			}
			System.out.println("Cantidad de columnas en primer archivo: " + datos.darTamanoColumnas());
			System.out.println("Cantidad de filas en primer archivo: " + datos.darTamanoFilas());
			System.out.println("Por favor espere...");
			//Carga de archivos principal finalizada, inicia carga de archivos secundarios.
			int inicioColumnaDeCarga = datos.darTamanoColumnas()-1;
			nextline = lectorSecundario.readNext();
			int filaAInsertar = 0;

			while((nextline = lectorSecundario.readNext())!=null) {
				if(nextline != null) {
					String[] temp = nextline;
					boolean centinelaBusquedaFila = true;
					for(int j=Integer.valueOf(filaAInsertar);j<datos.darTamanoFilas() && centinelaBusquedaFila;j++) {
						if(Integer.parseInt(temp[0])==(datos.darIdFila(j))) {
							centinelaBusquedaFila=false;
						}
					}
					if(!centinelaBusquedaFila) {
						for(int i = 1; i<temp.length;i++) {
							if(i!=7) {
								try {
									int posibleNumero = Integer.parseInt(temp[i]);
									datos.agregar(posibleNumero, i+inicioColumnaDeCarga, filaAInsertar);
								}catch(Exception e) {
									datos.agregar(temp[i], i+inicioColumnaDeCarga, filaAInsertar);
								} 
							}
						}
					}else {}
					filaAInsertar++;
				}
			}
			long endTime = System.nanoTime();
			System.out.println("Cantidad de columnas en total: " + datos.darTamanoColumnas());
			System.out.println("Cantidad de filas en total: " + datos.darTamanoFilas());
			System.out.println("-------- "+datos.darTamanoColumnas()*datos.darTamanoFilas() + " DATOS CARGADOS CORRECTAMENTE --------\n ");


			System.out.println("Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void imprimirPeliculaArregloDinamico() {
		System.out.println("Primera pelicula");
		((Pelicula)arreglo[0]).imprimirPelicula();
		System.out.println("Ultima pelicula");
		((Pelicula)arreglo[arreglo.length-1]).imprimirPelicula();
		//System.out.println(arreglo.length);
	}
	public void copiarMatriz()
	{
		arreglo = datos.copiarMatriz(COLUMNA_RELEASE_DATE, COLUMNA_TITULO, COLUMNA_ID, COLUMNA_DIRECTORES, COLUMNA_NUM_CALIFICACIONES, COLUMNA_CALIFICACIONES, COLUMNA_ACTOR_1,
				COLUMNA_ACTOR_2, COLUMNA_ACTOR_3, COLUMNA_ACTOR_4, COLUMNA_ACTOR_5, COLUMNA_GENERO);

	}

	public void buscarPeoresPeliculas() {
		if(datos==null) {
			System.out.println("\nNO SE HA CARGADO LA LISTA DE PELICULAS.\nPor favor seleccionar la opcion 2. (cargar los datos con Arreglo Dinamico) antes de seleccionar esta opcion\n");
		}
		else {
			ShellSort.sort(arreglo);
			for(int i =0; i<20; i++)
			{
				int aImprimir = i+1;
				System.out.println("\n-Peor pelicula # " + aImprimir );
				((Pelicula)arreglo[i]).imprimirPelicula();
			}
		}
	}

	public void imprimirTodasLasPeliculas() {
		int posicionElemento = datosEncadenados.size();
		Pelicula act = datosEncadenados.getElement(posicionElemento);
		while(posicionElemento>0) {
			posicionElemento--;
			act.imprimirPelicula();
			act = datosEncadenados.getElement(posicionElemento);
		}
	}

}
