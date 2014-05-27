package galerias.entidades;

/** Un cobarde es un tipo de Ladron que le tiene miedo a la lava.Su movimiento es aleatorio al no 
 * ser que haya una casilla colapsada en la direccion donde quiere ir.
 */


import java.util.LinkedList;
import java.util.Random;

import galerias.escenario.Casilla;
import galerias.escenario.Direccion;
import galerias.vista.Dibujable;

public class Cobarde extends Ladron implements Dibujable{
	
	/** Constructor del cobarde sin parametros.
	 * 
	 */
	
	public Cobarde() {
		super();
	}

	/**
	 * Constructor de Cobarde pasandole una casilla.
	 * @param casilla La casilla donde va a estar el Cobarde.
	 */
	
	public Cobarde(Casilla casilla) {
		super(casilla);
	}

	/** Proximo movimiento del Cobarde.
	 * El proximo movimiento del cobarte depende de las vecinas que no estan colapsadas,
	 * elige una aleatoriamente. Si esta rodeado de lava se queda quieto.
	 * 
	 */
	
	
	@Override
	public Direccion proximoMovimiento() {
		LinkedList<Casilla> casillasNoColapsadas = new LinkedList<Casilla>();
		for (Casilla casilla : this.getCasillaActual().getCasillasVecinas()){
			if(!casilla.esColapsada()){
				casillasNoColapsadas.add(casilla);
			}
		}
		Random generador = new Random();
		if (casillasNoColapsadas.isEmpty()) return null;
		else {
			Casilla casillaSiguiente = casillasNoColapsadas.get(generador.nextInt(casillasNoColapsadas.size()));
			if(casillaSiguiente.esLlena()) return null;
			else return this.getCasillaActual().getPosicion().relativa(casillaSiguiente.getPosicion());
		}
	}

	@Override
	public String getImagen() {
		return "cobarde";
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

	/** El alcance del Cobarde.
	 * El alcance es siempre 0 ya que no tiene lampara y no ilumina. 
	 */
	@Override
	public int getAlcance() {
		return 0;
	}

}
