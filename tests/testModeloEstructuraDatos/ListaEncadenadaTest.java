package testModeloEstructuraDatos;

import static org.junit.Assert.*;

import org.junit.Test;


import modeloEstructuraDatos.ListaEncadenada;
import modeloLogico.Modelo;
public class ListaEncadenadaTest {

	private ListaEncadenada ls;
	private Modelo modelo;
	
	public void setUp1() {
		ls= new ListaEncadenada();
		modelo = new Modelo();
		modelo.CargarModelo(modelo.NUMERO_OPCION_DE_CARGA_LISTAENCADENADA);
		
		
	}
	
	@Test
	public void testListaEncadenada() {
		setUp1();
		
		assertNull("Primer elemento es null",ls.firstElement());
	}


	@Test
	public void testAddFirst() {
		setUp1();
		Comparable e = "dato";
		ls.addFirst(e);
		assertNotNull("No se agrego",ls.firstElement());
		
	}
  
	@Test
	public void testAddLast() {
		setUp1();
		Comparable e = "dato";
		ls.addLast(e);
		assertNotNull("No se agrego",ls.lastElement());
	}

	@Test 
	public void testInsertElement() {
		setUp1();
		if (ls.size()>0) {
			Comparable e = "dato";
			ls.insertElement(e, 1);
		}    
		
		assertNull("No se agrego",ls.getElement(1));
	}

	@Test
	public void testRemoveFirst() {
		setUp1();
		Comparable e = "dato";
		Comparable a = "dato1";
		if (ls.size()>0) {
			ls.insertElement(e, 0);
			ls.insertElement(a, 1);
		}
		
		ls.removeFirst();
		assertEquals(null, ls.firstElement());
	}

	@Test
	public void testDeleteElement() {
		setUp1();
		
		Comparable e = "1";
		ls.addFirst(e);
		ls.deleteElement(0);
		assertNotEquals(null, ls.getElement(0));

	}

	@Test
	public void testFirstElement() {
		setUp1();
		Comparable e = "dato";
		ls.addFirst(e);
		assertNotNull("No se agrego",ls.firstElement());
	}

	@Test
	public void testLastElement() {
		setUp1();
		assertNull("No hay ultimo elemento",ls.lastElement());
	}

	@Test
	public void testGetElement() {
		setUp1();
		Comparable e = "dato";
		ls.insertElement(e, 1);
		assertNull("No se agrego",ls.getElement(1));
	}

	@Test
	public void testSize() {
		setUp1();
		assertNotNull("No hay",ls.size());
		
	}

	@Test
	public void testIsEmpty() {
		setUp1();
		assertEquals(true, ls.isEmpty());
	}

	@Test
	public void testExchange() {
		setUp1();
		
		
		Comparable e = "1";
		Comparable a = "2";
		ls.addFirst(e);
		ls.addFirst(a);
		ls.exchange(0, 1);
		assertNotEquals(e, ls.getElement(0));

	}

	@Test
	public void testChangeInfo() {
		
		setUp1();
		Comparable a = "dato1";
		Comparable b = null;
		ls.addFirst(a);
		ls.changeInfo(0, b);
		assertEquals(null, ls.getElement(0));
		
	}

}
