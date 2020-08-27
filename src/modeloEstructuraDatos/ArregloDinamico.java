package modeloEstructuraDatos;




/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */

public class ArregloDinamico<T extends Comparable<T>>{
	
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
	
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMaxFilas;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoActFilas;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T [][]elementos;

	private int tamanoActColumnas;
	private int tamanoMaxColumnas;

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int maxColumnas, int maxFilas)
	{
		elementos = (T[][])new Object[maxColumnas][maxFilas];
		tamanoMaxFilas = maxFilas;
		tamanoMaxColumnas = maxColumnas;
		tamanoActFilas = 0;
		tamanoActColumnas = 0;
	}

	public void agregar( T dato, int numeroColumna, int numeroFila)
	{
		if ( tamanoMaxFilas<=numeroFila )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMaxFilas = 2 * tamanoMaxFilas;
			Object [][] copia = elementos;
			elementos = null;
			elementos = (T[][]) new Object[tamanoMaxColumnas][tamanoMaxFilas];
			for ( int i = 0; i < tamanoActFilas; i++)
			{
				for (int j= 0; j<tamanoActColumnas;j++)
					elementos[j][i] = (T) copia[j][i];
			} 
			System.out.println("Arreglo filas lleno: " + numeroFila + " - Arreglo filas duplicado: " + tamanoMaxFilas);
		}
		if ( tamanoMaxColumnas<=numeroColumna )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMaxColumnas = 2 * tamanoMaxColumnas;
			Object [][] copia = elementos;
			elementos = null;
			elementos = (T[][]) new Object[tamanoMaxColumnas][tamanoMaxFilas];
			for ( int i = 0; i < tamanoActFilas; i++)
			{
				for (int j= 0; j<tamanoActColumnas;j++)
					elementos[j][i] = (T) copia[j][i];
			} 
			System.out.println ("Arreglo columnas lleno: " + numeroColumna + " - Arreglo columnas duplicado: " + tamanoMaxColumnas);
		}

		elementos[numeroColumna][numeroFila] = (T)dato;
		if(numeroFila>=tamanoActFilas) tamanoActFilas = numeroFila+1;
		if(numeroColumna>=tamanoActColumnas) tamanoActColumnas = numeroColumna+1;
	}


	public int darTamanoFilas() {
		return tamanoActFilas;
	}
	

	public int darTamanoColumnas() {
		return tamanoActColumnas;
	}
	
	public T buscarFila(T dato, int pFila) {
		T datoR = null;
		for (int i = 0; i < tamanoActColumnas; i++) {
			if(elementos[i][pFila].equals(dato))
			{
				datoR = dato;
			}

		}
		return datoR;
	}
	
	public T buscarColumna(T dato, int pColumna) {
		T datoR = null;
		for (int i = 0; i < tamanoActFilas; i++) {
			if(elementos[pColumna][i].equals(dato))
			{
				datoR = dato;
			}

		}
		return datoR;
	}
	
	private String buscarContieneColumna(String dato, int pColumna) {
		String datoR = "";
		for (int i = 0; i < tamanoActFilas; i++) {
			if(elementos[pColumna][i].toString().contains(dato))
			{
				datoR += elementos[pColumna][i] + "/n";
			}

		}
		if(datoR.isEmpty()) datoR = "No hay coincidencias con :" + dato +"\n";
		return datoR;
	}
	
	private String buscarContieneFila(String dato, int pFila) {
		String datoR = "";
		for (int i = 0; i < tamanoActColumnas; i++) {
			if(elementos[i][pFila].toString().contains(dato))
			{
				datoR += elementos[i][pFila] + "\n";
			}

		}
		if(datoR.isEmpty()) datoR = "No hay coincidencias con :" + dato +"\n";
		return datoR;
	}
	
	public int darIdFila(int numFila) {
		return (Integer)elementos[0][numFila];
		
	}
	
	public Object darElementoEn (int numColumna, int numFila) {
		return elementos[numColumna][numFila];
	}
	
	public String darBuenasPeliculasDeUnDirector(String pNombre) {
		String rta = null;
		boolean existeDirector = false;
		int contadorBuenasPeliculas=0;
		String datos = "";
		String actores = "";
		double sumatoriaCalificaciones=0;
		for(int i=0; i<tamanoActFilas;i++) {
			if(pNombre.equals(elementos[COLUMNA_DIRECTORES][i])) 
			{				
				if(Double.parseDouble((String)elementos[COLUMNA_CALIFICACIONES][i])>=6.0) 
				{
					actores = "";
					for(int g = 1; g<10; g= g+2)
					{
						if(!elementos[g][i].equals("none"))
						{
							actores += elementos[g][i] + ", ";
						}
					}
					++contadorBuenasPeliculas;
					datos +="\n Pelicula " + contadorBuenasPeliculas + "\n >ID: " + elementos[COLUMNA_ID][i] + "\n >Titulo: " + elementos[COLUMNA_TITULO][i] + "\n >Genero(s): " + elementos[COLUMNA_GENERO][i] + 
							"\n >Fecha de lanzamiento: " + elementos[COLUMNA_RELEASE_DATE][i] + "\n >Actores: " + actores + "\n";
					sumatoriaCalificaciones+=Double.parseDouble((String)elementos[COLUMNA_CALIFICACIONES][i]);

				}
			existeDirector=true;
			}
		} 
		double promediodeVotacion = 0;
		if(contadorBuenasPeliculas!=0)promediodeVotacion =(double) sumatoriaCalificaciones/contadorBuenasPeliculas;
		if(existeDirector) rta = "El director "+pNombre+" tiene "+ contadorBuenasPeliculas+" peliculas buenas o\n" + 
				"con votacion positiva.\n" + "El promedio de votacion de estas peliculas es de " + promediodeVotacion + "\n Datos sobre buenas peliculas: \n"
				+ datos;
		return rta;
	}

}