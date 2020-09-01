package modeloEstructuraDatos;




/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */

public class ArregloDinamico<T extends Comparable<T>>{
	
	
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
	private Object[][] elementos;
	private Comparable[] arregloTemporal;
	private int tamanoActColumnas;
	private int tamanoMaxColumnas;

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int maxColumnas, int maxFilas)
	{
		Object[][] ts = new Object[maxColumnas][maxFilas];
		elementos = ts;
		tamanoMaxFilas = maxFilas;
		tamanoMaxColumnas = maxColumnas;
		tamanoActFilas = 0;
		tamanoActColumnas = 0;
	}
	public Comparable[] copiarMatriz(int posLanzamiento, int posTitulo, int posId, int posDirector, int posNumVotos, int posPromedioVotos,  int posActor1, int posActor2,
			int posActor3, int posActor4, int posActor5, int posGenero)
	{		
		if(tamanoActFilas != 0) {
		Pelicula peli;
		arregloTemporal = new Comparable[tamanoActFilas];
		for(int i=0; i<tamanoActFilas; i++)
		{
			peli = new Pelicula(elementos[posLanzamiento][i].toString(),elementos[posTitulo][i].toString(), Integer.parseInt(elementos[posId][i].toString().trim()),(String)elementos[posDirector][i],
			Integer.parseInt(elementos[posNumVotos][i].toString()), Double.parseDouble((String)elementos[posPromedioVotos][i]),
			(String)elementos[posActor1][i], (String)elementos[posActor2][i], (String)elementos[posActor3][i], (String)elementos[posActor4][i], (String)elementos[posActor5][i],
			(String)elementos[posGenero][i]); 
			arregloTemporal[i] = peli;
		}
	}
		return arregloTemporal;
	}

	public void agregar( T dato, int numeroColumna, int numeroFila)
	{
		if ( tamanoMaxFilas<=numeroFila )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMaxFilas = 2 * tamanoMaxFilas;
			Object [][] copia = elementos;
			elementos = null;
			elementos = new Object[tamanoMaxColumnas][tamanoMaxFilas];
			for ( int i = 0; i < tamanoActFilas; i++)
			{
				for (int j= 0; j<tamanoActColumnas;j++)
					elementos[j][i] = (T)copia[j][i];
			}
		}
		if ( tamanoMaxColumnas<=numeroColumna )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMaxColumnas = 2 * tamanoMaxColumnas;
			Object [][] copia = elementos;
			elementos = null;
			elementos = new Object[tamanoMaxColumnas][tamanoMaxFilas];
			for ( int i = 0; i < tamanoActFilas; i++)
			{
				for (int j= 0; j<tamanoActColumnas;j++)
					elementos[j][i] = (T) copia[j][i];
			}
			
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
	
	private String buscarContieneColumna(T dato, int pColumna) {
		String datoR = "";
		for (int i = 0; i < tamanoActFilas; i++) {
			if(elementos[pColumna][i].toString().contains((CharSequence) dato))
			{
				datoR += elementos[pColumna][i] + "/n";
			}

		}
		if(datoR.isEmpty()) datoR = "No hay coincidencias con :" + dato +"\n";
		return datoR;
	}
	
	private String buscarContieneFila(T dato, int pFila) {
		String datoR = "";
		for (int i = 0; i < tamanoActColumnas; i++) {
			if(elementos[i][pFila].toString().contains((CharSequence) dato))
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

}