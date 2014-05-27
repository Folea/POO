package galerias.entidades;

/** Una Roca es una entidad inerte que tiene escondida un diamante. Una roca se puede romper y dejar
 * el diamante que tiene dentro.
 * 
 */

import galerias.escenario.Casilla;
import galerias.vista.Dibujable;

public class Roca extends Inerte implements Dibujable {
	Diamante diamante;
	
	/** Constructor de una Roca con diamante.
	 * @param diamante El diamante que contiene la Roca.
	 */
	
	public Roca(Diamante diamante) {
		super();
		this.diamante = diamante;
	}
	
	/** Constructor de una Roca con diamante y casilla.
	 * @param casilla La casilla donde estara la Roca.
	 * @param diamante El diamante que contiene la Roca.
	 */
	
	public Roca(Casilla casilla, Diamante diamante) {
		super(casilla);
		this.diamante = diamante;
	}
	
	/** Metodo romper Roca.
	 * Este metodo elimina la roca de la casilla y deja en su lugar el diamante que contiene
	 * 
	 */
	
	public void romper(){
		diamante.setCasillaActual(getCasillaActual());
		super.desaparecer();
	}

	@Override
	public String getImagen() {
		return "roca";
	}

	@Override
	public int getPosicionX() {
		return this.getCasillaActual().getPosicion().getX();
	}

	@Override
	public int getPosicionY() {
		return this.getCasillaActual().getPosicion().getY();
	}

	/** Metodo alcance de una Roca.
	 * La Roca no ilumina la casilla por lo tanto siempre devuelve 0;
	 */
	
	@Override
	public int getAlcance() {
		return 0;
	}
	

}
