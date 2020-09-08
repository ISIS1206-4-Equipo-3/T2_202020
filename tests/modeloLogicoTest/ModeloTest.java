package modeloLogicoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modeloLogico.Modelo;

public class ModeloTest {

	
	private Modelo modelo;
	
	public void setUp1()
	{
		modelo = new Modelo();
		modelo.CargarModelo(modelo.NUMERO_OPCION_DE_CARGA_ARREGLODINAMICO);
		
	}
	public void setUp2()
	{
		
	}
	
	
	@Test
	public void testModelo() {
	setUp1();
	assertNotNull(modelo.datos);
		
	}

	@Test
	public void testDarPeliculasDeUnDirector() {
		setUp1();
		assertNotNull(modelo.darPeliculasDeUnDirector("George Lucas"));
		
	}
	@Test
	public void testEntenderUnGenero()
	{
		setUp1();
		assertEquals("No se encontraron peliculas con el genero prueba",modelo.entenderUnGenero(("prueba") ));
		assertNotEquals("No se encontraron peliculas con el genero Drama", modelo.entenderUnGenero(("Drama")));
	}
	
	@Test
	public void testCrearRankingGeneroPrimerLlamado()
	{
		setUp1();
		assertEquals("No se encontraron peliculas con el genero prueba",modelo.crearRankingGeneroPrimerLlamado("prueba") );
		assertNotEquals("No se encontraron peliculas con el genero Drama", modelo.crearRankingGeneroPrimerLlamado("Drama"));
	}
	@Test
	public void testDarListaPeliculas()
	{
		setUp1();
		assertTrue(modelo.darListaPeliculas().startsWith("1. " + modelo.datos.darElementoEn(34, 0)));
		assertNotNull(modelo.darListaPeliculas());
	}
	@Test
	public void testCargarDatos() {
		setUp1();
		assertEquals(40,modelo.datos.darTamanoColumnas());
		assertEquals(2000, modelo.datos.darTamanoFilas());
		assertNotNull(modelo.datos.darElementoEn(10, 100));
	}
	
	@Test
	public void testconocerUnDirector() {
		setUp1();
		assertNotNull(modelo.conocerUnDirector("Marc Meyer"));
		assertNotEquals("Director no encontrado",modelo.conocerUnDirector("George Lucas"));
	
	}
	@Test
	public void testconocerUnActor() {
		setUp1();
		assertNotNull(modelo.conocerUnActor("Tom Cruise"));
		assertNotEquals("Director no encontrado",modelo.conocerUnActor("Brad Pitt"));
	
	}


}
