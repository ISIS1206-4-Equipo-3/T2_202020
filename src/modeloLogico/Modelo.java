package modeloLogico;

import java.io.FileReader;
import java.util.Arrays;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import modeloEstructuraDatos.ArregloDinamico;

public class Modelo {

	public ArregloDinamico datos;

	private final static String RUTA_DATOS_PRINCIPALES= "./data/small/MoviesCastingRaw-small.csv";
	private final static String RUTA_DATOS_SECUNDARIOS= "./data/small/SmallMoviesDetailsCleaned.csv";



	private FileReader archivoPrincipal;
	private CSVReader lectorPrincipal;

	private FileReader archivoSecundario;
	private CSVReader lectorSecundario;


	public Modelo () {
		datos = new ArregloDinamico(1,1);
		cargarDatos(RUTA_DATOS_PRINCIPALES, RUTA_DATOS_SECUNDARIOS);
	}

	public String darPeliculasDeUnDirector(String nombre) {
		String rta = datos.darBuenasPeliculasDeUnDirector(nombre);
		return rta;
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
			System.out.println(Arrays.toString(nextline));
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
			System.out.println("Cantidad de columnas en segundo archivo: " + datos.darTamanoColumnas());
			System.out.println("Cantidad de filas en segundo archivo: " + datos.darTamanoFilas());
			System.out.println("-------- "+datos.darTamanoColumnas()*datos.darTamanoFilas() + " DATOS CARGADOS CORRECTAMENTE --------\n \n");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
