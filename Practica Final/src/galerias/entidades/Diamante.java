package galerias.entidades;

/** Un diamante es una entidad inerte que tiene un valor en kilates.El diamante ilumina su casilla
 * si tiene mas kilates que el umblal. Un diamante puede ser robado por los ladrones o cogido por el 
 * jugador, en el ultimo casa ayuda a aumentar el alcance de la iluminacion del jugador.
 */

import galerias.escenario.Casilla;
import galerias.vista.Dibujable;

public class Diamante extends Inerte implements Dibujable {

	private final int kilates;
	protected final static int UMBRAL = 10;
	
	/** Constructor de un Diamante pasandole un valor.
	 * @param valor El valor de los kilates del diamante.
	 */
	
	public Diamante(int valor) {
		super();
		this.kilates = valor;
		
	}
	
	/** Constructor de un Diamante pasandole un valor y casilla.
	 * @param casilla La casilla donde va a estar.
	 * @param valor Los kilates que tiene.
	 */
	
	public Diamante(Casilla casilla, int valor) {
		super(casilla);
		this.kilates = valor;
	}
	
	/** Metodo de consulta de los kilates.
	 * @return los kilates del Diamante.
	 */
	
	public int getKilates() {
		return kilates;
	}

	@Override
	public String getImagen() {
		return "diamante";
	}

	@Override
	public int getPosicionX() {
		return this.getPosicion().getX();
	}

	@Override
	public int getPosicionY() {
		return this.getPosicion().getY();
	}
	
	/** Metodo ilumina.
	 * Este metodo comprueba si los kilates el umbral para saber si hay que iluminar la casilla
	 * en la que esta o no.
	 * @return true o false dependiendo de si hay que iluminar o no.
	 */
	
	public boolean ilumnina(){
		if (kilates > UMBRAL) return true;
		else return false;
	}
	
	/** Metodo alcande del Diamante.
	 * Siempre es 1 ya que se ilumina solo la casilla en la que esta.
	 */
	
	@Override
	public int getAlcance() {
		return 1;
	}

}
