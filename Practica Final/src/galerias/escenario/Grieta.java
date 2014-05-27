package galerias.escenario;


/**
 * 
 * @author Cristian Folea y Francisco Javier S�nchez Moreno
 *
 */

/**
 * Un grieta es una casilla que se caracteriza por estar siempre colapsada.
 */

import galerias.vista.Dibujable;
public class Grieta extends Casilla implements Dibujable{
	//XXX la subclase no tiene que implementar la interfaz lo hereda de Casilla

	/**
	 * Constructor de una grieta con dos parametros.
	 * Construye una grieta a pasandole la posicion en la que esta y la capacidad.
	 * @param posicion La posicion de la grieta.
	 * @param capacidad las entidades que puede contener.
	 */
	
	public Grieta(Posicion posicion, int capacidad) {
		super(posicion, capacidad);
		this.colapsar();
		}

	
	/**
	 * Constructor de una grieta con un parametro.
	 * Crea una grieta en la posicion indicada y con la capacidad por defecto.
	 * @param posicion La posicion de la grieta.
	 */
	
	public Grieta(Posicion posicion) {
		super(posicion);
		this.colapsar();
	}
	
	@Override
	public Grieta clone(){
		Grieta copia = null;
		copia = (Grieta)super.clone();
		return copia;
	}
	
	//XXX redefinir el clone para aplicar la regla covariante

	@Override
	public String getImagen() {
		return "lava";
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
