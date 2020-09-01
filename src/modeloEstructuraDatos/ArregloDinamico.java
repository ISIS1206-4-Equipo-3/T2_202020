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
	private Object[] arregloTemporal;
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
	public void copiarMatriz()
	{
		Pelicula peli;
		arregloTemporal = new Object[tamanoActFilas];
		for(int i=0; i<elementos.length; i++)
		{
			peli = new Pelicula(elementos[0][i], director, numVotos, promedioVotos, actor1, actor2, actor3
					, actor4, actor5, pGenero) 
			arregloTemporal[i] = peli;
		}
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