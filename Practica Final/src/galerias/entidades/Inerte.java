package galerias.entidades;

/** Inerte es un tipo de Entidad que se carecteriza por no poder moverse.
 */

import galerias.escenario.Casilla;
import galerias.escenario.Direccion;

public abstract class Inerte extends Entidad {
	
	/** Constructor de una Entidad inerte sin parametros.
	 * 
	 */
	
	public Inerte() {
		super();
	}

	/** Constructor de una Entidad inerte pasandole una casilla.
	 * @param casilla La casilla donde va a estar la Entidad inerte.
	 */
	
	public Inerte(Casilla casilla) {
		super(casilla);
	}
	
	@Override
	public void moverCasilla(Direccion direccion){};

}
