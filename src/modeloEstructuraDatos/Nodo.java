package modeloEstructuraDatos;

public class Nodo<T extends Comparable<T>> {

	private T dato;
	private Nodo<T> siguiente;

	public Nodo(T pDato) {
		this.dato = pDato;
		this.siguiente = null;
	}

	public T darDato() { return dato; }
	
	public T darSigiente() {return (T) siguiente;}
	
	public void setSiguiente (Nodo<T> pSiguiente) {siguiente = pSiguiente;}
	
	public boolean tieneSiguiente () {
		if(siguiente==null) return false;
		else { return true;}
	}
	

}
