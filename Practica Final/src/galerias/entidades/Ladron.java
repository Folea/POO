package galerias.entidades;

/** El ladron es un tipo de personaje que se encarga de robar los diamantes de las casillas o del jugador
 * si coinciden en la misma casilla.
 */

import galerias.escenario.Casilla;
import galerias.escenario.Direccion;

public abstract class Ladron extends Personaje {
	
	/** Constructor del Ladron sin parametros.
	 * 
	 */
	
	public Ladron() {
		super();
	}

	/**
	 * Constructor del Ladron pasandole una casilla.
	 * @param casilla La casilla donde va a estar el Ladron.
	 */
	
	public Ladron(Casilla casilla) {
		super(casilla);
	}

	/** Metodo que calcula el proximo movimiento.
	 * 
	 */
	
	@Override
	public abstract Direccion proximoMovimiento();

	/**
	 * El metodo accion del Ladron.
	 * Consiste en comprobar si hay algun diamante en la casilla
	 * en la que se encuentra, en caso de que hay algun diamante,
	 * lo hace desaparecer ya que lo roba.
	 */
	
	@Override
	public void accion() {
		for (Entidad entidad : this.getCasillaActual().getEntidadesCasilla()){
			if(entidad instanceof Diamante){
				this.getCasillaActual().eliminarEntidad(entidad);
				entidad.desaparecer();
			}
			else if(entidad instanceof Jugador) ((Jugador) entidad).perderDiamante();
		}		
	}
}
