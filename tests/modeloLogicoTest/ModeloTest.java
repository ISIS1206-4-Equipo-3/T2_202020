package modeloLogicoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import modeloLogico.Modelo;

public class ModeloTest {

	
	private Modelo modelo;
	
	public void setUp1()
	{
		modelo = new Modelo();
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
	public void testCargarDatos() {
		setUp1();
		assertEquals(40,modelo.datos.darTamanoColumnas());
		assertEquals(2000, modelo.datos.darTamanoFilas());
		assertNotNull(modelo.datos.darElementoEn(10, 100));
	}

}
