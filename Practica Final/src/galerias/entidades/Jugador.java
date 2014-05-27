package galerias.entidades;

/** El jugador es un tipo de personaje que es controlado por el usuario. El jugador puede iluminar 
 * casillas y su alcance depende de los diamantes que tiene recogidos. Su accion es recoger los diamantes
 * de las casillas, si hay alguno.
 */

import java.util.LinkedList;

import galerias.escenario.Casilla;
import galerias.escenario.Direccion;
import galerias.vista.Dibujable;

public class Jugador extends Personaje implements Dibujable {
	private LinkedList<Diamante> listaDiamantes;
	private int kilatesDiamantes;
	private Direccion direccion;
	private int alcance = 2;
	
	public int getKilatesDiamantes() {
		return kilatesDiamantes;
	}
	
	/** Constructor del Jugador.
	 * 
	 */
	
	public Jugador() {
		super();
		listaDiamantes = new LinkedList<Diamante>();
	}
	
	/** Constructor del Jugador pasandole un parametro.
	 * 
	 * @param casilla La casilla donde va a estar el Jugador
	 */
	
	public Jugador(Casilla casilla) {
		super(casilla);
		listaDiamantes = new LinkedList<Diamante>();
	}

	@Override
	public Direccion proximoMovimiento(){
		return this.direccion;
		
	}
	/** Metodo que cambia la direccion a la que va a ir el jugador.
	 * 
	 * @param direccion 
	 */
	
	public void recibir(Direccion direccion){
		this.direccion = direccion;
	}
	
	
	public LinkedList<Diamante> getListaDiamantes() {
		return listaDiamantes;
	}
	
	/** Metodo accion.
	 * Consiste en que el Jugador mira a ver si hay un diamante en la casilla en la que se encuentra,
	 * en caso de que hay un diamante lo coge.
	 * 
	 */
	
	@Override
	public void accion() {
		for (Entidad entidad : this.getCasillaActual().getEntidadesCasilla()){
			if(entidad instanceof Diamante){
				listaDiamantes.add((Diamante)entidad);
				entidad.desaparecer();
				kilatesDiamantes += ((Diamante) entidad).getKilates();
			}
		}
	}
	
	/** Metodo que elimina el ultimo diamante de la lista de diamantes.
	 * 
	 */
	
	public void perderDiamante() {
		if(kilatesDiamantes > 0 && this.listaDiamantes.size() > 0){
		this.kilatesDiamantes -= this.listaDiamantes.getLast().getKilates();
		this.listaDiamantes.removeLast();
		}		
	}

	@Override
	public String getImagen() {
		return "jugador";
	}

	@Override
	public int getPosicionX() {
		if(getCasillaActual() == null) return -1;
		return this.getPosicion().getX();
	}

	@Override
	public int getPosicionY() {
		if(getCasillaActual() == null) return -1;
		return this.getPosicion().getY();
	}
	
	/** Metodo getAlcance.
	 * Devuelve el alcance de la luz que depende de los kilates que tienen los diamantes que tiene el 
	 * Jugador. 
	 */
	@Override
	public int getAlcance(){
		return alcance + (kilatesDiamantes / Diamante.UMBRAL);
	}
	
	
}
