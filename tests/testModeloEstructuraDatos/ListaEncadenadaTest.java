package testModeloEstructuraDatos;

import static org.junit.Assert.*;

import org.junit.Test;


import modeloEstructuraDatos.ListaEncadenada;
import modeloLogico.Modelo;
public class ListaEncadenadaTest {

	private ListaEncadenada ls;
	private Modelo modelo;
	
	public void setUp1() {
		ls= new ListaEncadenada<>();
		modelo = new Modelo();
	}
	
	@Test
	public void testListaEncadenada() {
		setUp1();
		
		assertNull("No se cargo",ls.firstElement());
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
		Comparable e = "dato";
		Comparable a = "dato1";
		ls.insertElement(e, 0);
		ls.insertElement(a, 1);
		assertTrue(ls!=null);
		assertTrue(ls.getElement(0)!=null);
		ls.deleteElement(0);
		assertEquals(a, ls.firstElement());
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
		assertTrue(ls!=null);
		setUp1();
		Comparable e = "dato";
		Comparable a = "dato1";
		ls.insertElement(e, 1);
		ls.insertElement(a, 0);
		ls.exchange(0, 1);
		assertEquals(a, ls.getElement(1));
		assertEquals(e, ls.getElement(0));
		
	}

	@Test
	public void testChangeInfo() {
		assertTrue(ls!=null);
		setUp1();
		Comparable a = "dato1";
		Comparable b = "dato2";
		
		ls.changeInfo(0, b);
		assertEquals(null, ls.getElement(0));
		
	}

}
