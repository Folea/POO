package galerias.escenario;



/**
 * 
 * @author Cristian Folea y Francisco Javier S�nchez Moreno
 *
 */
/**
 * La clase Salida es un tipo de casilla cuya su capacidad siempre es 1 y sirve para pasar de un nivel
 * a otro.
 */
import galerias.vista.Dibujable;
public class Salida extends Casilla implements Dibujable{
	//XXX la subclase no tiene que implementar la interfaz lo hereda de Casilla
	
	/**
	 * Una salida es una casilla que permite escapar de una galer�a. Una salida se caracteriza por no limitar el n�mero de
	 * entidades que pueden acceder a ella. Esta condici�n se expresa indicando que su capacidad libre es siempre 1
	 */
	
	public Salida(Posicion posicion) {
		super(posicion, 1);
	}
	
	/**
	 * Capacidad libre.
	 * Este metodo devuelve la capacidad libre de la salida que siempre sera 1.
	 */
	
	public int getCapacidadLibre() {
		return 1;
	}
	
	@Override
	public Salida clone(){
		Salida copia = null;
		copia = (Salida)super.clone();
		return copia;
	}
	
	//XXX Redefinir el m�todo clone para aplicar la regla covariante
	
	@Override
	public String getImagen() {
		return "salida";
	}

	@Override
	public int getPosicionX() {
		return this.getPosicion().getX();
	}

	@Override
	public int getPosicionY() {
		return this.getPosicion().getY();
	}
}
