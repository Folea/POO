package galerias.entidades;

/** El minero es un tipo de personaje cuyo trabajo es encontrar rocas y romperlas para dejar los 
 * diamantes que las rocas contienen. Su movimiento se basa en elegir las casillas que menos visitas
 * tienen. 
 *
 */

import galerias.escenario.Casilla;
import galerias.escenario.Direccion;
import galerias.vista.Dibujable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;



public class Minero extends Personaje implements Dibujable{
	private long tiempo;
	HashMap<Casilla, Integer> visitas = new HashMap<Casilla, Integer>();

	/** Constructor del Minero sin parametros.
	 * 
	 */
	
	public Minero() {
		super();
	}

	/**
	 * Constructor del Minero pasandole una Casilla.
	 * En este constructor a diferencia de los demas, se inserta la casilla en el Mapa, ya 
	 * que el minero tiene que saber por que casillas pasa y cuantas veces ha pasado.
	 * @param casilla Es la casilla donde va a estar el Minero.
	 */
	
	public Minero(Casilla casilla) {
		super(casilla);
		visitas.put(casilla, 1);
	}
	
	/** Redefinicion del metodo setCasillaActual.
	 * Se redefine este metodo ya que el minero tiene una cuenta de las casillas por las
	 * que va pasando y si se le asigna una casilla al Minero tenemos que meterlo en 
	 * el mapa.
	 * 
	 */
		
	@Override
	public void setCasillaActual(Casilla casilla){
			super.setCasillaActual(casilla);
			if(visitas.get(casilla) != 0) visitas.put(casilla,visitas.get(casilla)+1);
			else visitas.put(casilla,1);		
	}
	
	/** Metodo proximo movimiento de Minero.
	 * Este metodo devuelve la direccion en la que se va a mover el minero cumpliendo unas
	 * condiciones. El minero siempre elige la direccion donde esta la casilla con menos visitas,
	 * en el caso de que hay varias casillas con igual numero de visitas, elige aleatoriamente.
	 * Si el minero esta cansado devuelve null, ya que no debe moverse.
	 * 
	 */
	
	
	public Direccion proximoMovimiento() { 
		if(!esCansado()){
			LinkedList<Casilla> iguales = new LinkedList<Casilla>();
			int menor = 0;
			for (Casilla casilla : this.getCasillaActual().getCasillasVecinas()){
				if(!visitas.containsKey(casilla)) visitas.put(casilla, 0);
				if(iguales.isEmpty()){
					menor = visitas.get(casilla);
					iguales.add(casilla);
				}
				else{
						if(menor > visitas.get(casilla)){
							menor = visitas.get(casilla);
							iguales.clear();
							iguales.add(casilla);
						}
					else if(menor == visitas.get(casilla)) iguales.add(casilla);
					}
				}			
			Random generador = new Random();
			Casilla casillaSiguiente = iguales.get(generador.nextInt(iguales.size()));
			visitas.put(casillaSiguiente, visitas.get(casillaSiguiente)+1);
			return this.getCasillaActual().getPosicion().relativa(casillaSiguiente.getPosicion());
	}
		else return null;
		
	}
	
	/** Metodo accion del Minero.
	 * El minero debe romper rocas si existe alguna roca en la casilla en la que esta y pone
	 * el tiempo a tiempo actual + 2000 (2 s), que es el tiempo que no puede moverse.
	 * 
	 */
	
	public void accion(){
			if (this.romperRoca()) tiempo = System.currentTimeMillis() + 2000;
	}
	
	/** Metodo que comprueba si esta cansado el Minero.
	 * Comprueba si ha pasado el tiempo que el Minero debe estar quieto.
	 * @return true o false dependiendo de si ha pasado o no el tiempo
	 */
	
	private boolean esCansado(){
		return (System.currentTimeMillis() < tiempo);
	}
	
	/** Metodo romper roca
	 * Este metodo comprueba si hay alguna roca en la casilla en la que se encuentra el minero,
	 * en el caso de que hay alguna roca la rompe.
	 * @return Devuelve true o false dependiendo de si ha roto alguna roca o no.
	 */
	
	private boolean romperRoca(){
		for (Entidad entidad : this.getCasillaActual().getEntidadesCasilla()){
			if (entidad instanceof Roca){
				((Roca) entidad).romper();
				return true;
			}
		}
		return false;
	}

	@Override
	public String getImagen() {
		return "minero";
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
	
	/** Metodo que devuelve el alcance de la luz del Minero.
	 * El Minero tiene una lampara que ilumina la casilla en la que esta y una mas. 
	 */
	
	@Override
	public int getAlcance(){
		return 2;
	}
}
