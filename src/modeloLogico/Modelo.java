package modeloLogico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

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
	
	
	public String RUTA_DATOS_PRINCIPALES= "./data/small/MoviesCastingRaw-small.csv";
	public String RUTA_DATOS_SECUNDARIOS= "./data/small/SmallMoviesDetailsCleaned.csv";
	
	public final static int NUMERO_OPCION_DE_CARGA_LISTAENCADENADA = 1;
	public final static int NUMERO_OPCION_DE_CARGA_ARREGLODINAMICO = 2;
	

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
	private ShellSort sort;
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

	public void cargarDatosEncadenados(String pRutaPrincipal, String pRutaSecundaria)
	{
		try {
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
					Pelicula anadir = new Pelicula(id, director, numVotos, promedioVotos, actor1, actor2, actor3, actor4, actor5, genero);
					datosEncadenados.addFirst(anadir);
					contador++;
				}
			}
			System.out.println("Primera pelicula");
			datosEncadenados.lastElement().imprimirPelicula();
			System.out.println("Ultima pelicula");
			datosEncadenados.firstElement().imprimirPelicula();
			System.out.println("-------- Los datos fueron cargados correctamente ("+contador+" peliculas) --------\n");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void cargarDatos(String pRutaPrincipal, String pRutaSecundaria) {
		try {   
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
			System.out.println("Porfavor espere...");
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
			System.out.println("Cantidad de columnas en total: " + datos.darTamanoColumnas());
			System.out.println("Cantidad de filas en total: " + datos.darTamanoFilas());
			System.out.println("-------- "+datos.darTamanoColumnas()*datos.darTamanoFilas() + " DATOS CARGADOS CORRECTAMENTE --------\n \n");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
     
	public void copiarMatriz()
	{
		
		arreglo = datos.copiarMatriz(COLUMNA_ID, COLUMNA_DIRECTORES, COLUMNA_NUM_CALIFICACIONES, COLUMNA_CALIFICACIONES, COLUMNA_ACTOR_1,
				COLUMNA_ACTOR_2, COLUMNA_ACTOR_3, COLUMNA_ACTOR_4, COLUMNA_ACTOR_5, COLUMNA_GENERO);
	}
	
	public String buscarPeoresPeliculas() {
		sort.sort(arreglo);
		return null;
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
