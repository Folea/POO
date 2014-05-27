package galerias.entidades;



/** El temerario es un tipo de ladron que se caracteriza por no tenerle miedo a la lava.Su movimiento
 * es aleatorio.
 * 
 */
import java.util.LinkedList;
import java.util.Random;

import galerias.escenario.Casilla;
import galerias.escenario.Direccion;
import galerias.vista.Dibujable;
public class Temerario extends Ladron implements Dibujable{
	
	/** Constructor del Temerario sin parametros.
	 * 
	 */
	
	public Temerario() {
		super();
	}

	/**
	 * Constructor del Temerario con casilla.
	 * @param casilla La casilla donde va a estar.
	 */
	
	public Temerario(Casilla casilla) {
		super(casilla);
	}

	/** Metodo proximo movimiento del Temerario.
	 * El temerario calcula en dependiendo de los diamantes,
	 * si en alguna casilla hay diamante va a dicha casilla.En 
	 * el caso de que haya mas de un diamante elige aleatoriamente
	 * una de de esas casillas.Si ninguna de sus vecinas tiene diamante
	 * elige una casilla aleatoriamente
	 * 
	 */
	
	@Override
	public Direccion proximoMovimiento() {
		LinkedList<Casilla> casillasDiamantes = new LinkedList<Casilla>();
		for (Casilla casilla : this.getCasillaActual().getCasillasVecinas()){
			for (Entidad entidad : casilla.getEntidadesCasilla()){
				if(entidad instanceof Diamante) casillasDiamantes.add(casilla);
			}
		}
		Random generador = new Random();
		if (casillasDiamantes.isEmpty()){
			Casilla casillaSiguiente = this.getCasillaActual().getCasillasVecinas().get(generador.nextInt(getCasillaActual().getCasillasVecinas().size()));
			return this.getCasillaActual().getPosicion().relativa(casillaSiguiente.getPosicion());
		}
		else {
			Casilla casillaSiguiente = casillasDiamantes.get(generador.nextInt(casillasDiamantes.size()));
			if(casillaSiguiente.esLlena()) return null;
			else return this.getCasillaActual().getPosicion().relativa(casillaSiguiente.getPosicion());
		}
	}
	
	@Override
	public String getImagen() {
		return "temerario";
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

	/** Metodo de alcance de la iluminacion.
	 * Siempre devuelve 0 ya que el temerario no tiene lampara y se ve solo si esta en alcance 
	 * de otro Personaje.
	 * 
	 */
	@Override
	public int getAlcance() {
		return 0;
	}

}
