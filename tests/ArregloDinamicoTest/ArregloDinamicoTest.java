package ArregloDinamicoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modeloEstructuraDatos.ArregloDinamico;
import modeloLogico.Modelo;

public class ArregloDinamicoTest {
	private ArregloDinamico arreglo;

	private static int TAMANOF=1;
	private static int TAMANOC=2   ;

	private Modelo modelo;
	
	public void setUp1() {
		arreglo= new ArregloDinamico(TAMANOC, TAMANOF);
	}
	public void setUp2()
	{
		modelo = new Modelo();
	}

	@Test
	public void testArregloDinamico() {
		setUp1();
		assertEquals(0, arreglo.darTamanoColumnas());
		assertEquals(0, arreglo.darTamanoFilas());
	}

	@Test
	public void testAgregar() {

		setUp1();
		String dato = "prueba";
		arreglo.agregar(dato, 0, 0);
		assertNotNull("No se agrego el elemento",arreglo.darElementoEn(0, 0));

		int tamano = arreglo.darTamanoColumnas();
		assertEquals("No indico el aumento del numero de columnas actuales", 1,tamano);
		int tamano2 = arreglo.darTamanoFilas();
		assertEquals("No indico el aumento del numero de filas actuales",1,tamano2);
	}

	@Test
	public void testDarTamanoFilas() {
		setUp1();
		Integer dato = 0;
		arreglo.agregar(dato, 0, 0);
		int tamano = arreglo.darTamanoFilas();
		assertEquals(1,tamano);
	}

	@Test
	public void testDarTamanoColumnas() {
		setUp1();
		Integer dato = 0;
		arreglo.agregar(dato, 0, 0);
		int tamano = arreglo.darTamanoColumnas();
		assertEquals(1,tamano);
	}

	@Test
	public void testBuscarFila() {
		setUp1();
		String datoAgregado = "hello";
		arreglo.agregar(datoAgregado, 0, 0);
		assertEquals("hello", arreglo.buscarFila(datoAgregado, 0));
	}

	@Test
	public void testBuscarColumna() {
		setUp1();
		String datoAgregado = "hello";
		arreglo.agregar(datoAgregado, 0, 0);
		assertEquals("hello", arreglo.buscarColumna(datoAgregado, 0));
	}

	@Test
	public void testDarIdFila() {
		setUp1();
		Integer datoAgregado = 2;
		arreglo.agregar(datoAgregado, 0, 0);
		assertEquals(2, arreglo.darIdFila(0));
	}

	@Test
	public void testDarElementoEn() {
		setUp1();
		String datoAgregado = "Elemento";
		arreglo.agregar(datoAgregado, 0, 0);
		assertEquals(datoAgregado, arreglo.darElementoEn(0, 0));
		assertTrue(arreglo!=null);
	}

	@Test
	public void testDarBuenasPeliculasDeUnDirector() {
		setUp2();
		assertNotNull(modelo.darPeliculasDeUnDirector("George Lucas"));
	}

}
