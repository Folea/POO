package galerias.entidades;

/** Una Entidad puede ser construida en una casilla o sin tener una casilla en la que esta.
 * Declara varios metodos, como cambiar la casilla actual, mover la la entidad o desaparecer.
 */

import galerias.escenario.Casilla;
import galerias.escenario.Direccion;
import galerias.escenario.Posicion;
import galerias.vista.Dibujable;


public abstract class  Entidad implements Dibujable {
	private Casilla casillaActual = null;
	
	/**
	 * Constructor de una Entidad pasandole una casilla.
	 * @param casilla La casilla donde estara la Entidad al crearla
	 */
	public Entidad (Casilla casilla){
		if (!casilla.esLlena()){
			this.casillaActual = casilla;
			casillaActual.anadirEntidad(this);
		}
		else throw new IllegalArgumentException("La casilla donde quiere insertar la entidad esta llena");
	}
	/**
	 * Constructor vacio de una Entidad, se crea la Entidad con a casillaActual a null;
	 */
	public Entidad (){
		
	}

	/**
	 * Metodo para poner la Entidad en una casilla.
	 * En el caso de que dicha casilla esta llena no permite la introduccion de la Entidad en esa
	 * casilla, y salta una excepcion.
	 * @param casilla Casilla en la que se quiere introducir la Entidad.
	 */
	public void setCasillaActual(Casilla casilla) {
		if (!casilla.esLlena()){
			this.casillaActual = casilla;
			this.casillaActual.anadirEntidad(this);
		}
		else throw new IllegalArgumentException("No se puede añadir la entidad en dicha casilla, la casilla esta llena");
	}
	
	/**
	 * Metodo de consulta.
	 * @return Devuelve la casilla donde se encuentra la Entidad.
	 */
	public Casilla getCasillaActual() {
		return casillaActual;
	}
	
	/**
	 * Consulta de si el jugador esta en una casilla o no.
	 * @return Devuelve un valor booleano correspondiente a si la Entidad esta o no en una casilla.
	 */
	public boolean hayCasilla(){
		if (this.casillaActual == null) return false;
		else return true;
	}
	
	/**
	 * Metodo de consulta de la posicion de la casilla actual de la Entidad
	 * @return Devuelve una Posicion que corresponde a la posicion de la casilla actual.
	 */
	public Posicion getPosicion() {
		return this.casillaActual.getPosicion();
	}
	
	/**
	 * Mueve la Entidad. Este metodo mueve a la Entidad en la direccion indicada, si no hay casilla
	 * en dicha direccion, la Entidad no se mueve.
	 * @param direccion Direccion en la que se quiere mover
	 */
	public void moverCasilla(Direccion direccion){
		if((direccion != null) && (this.casillaActual.tieneVecina(direccion)) &&
				(!this.casillaActual.getCasillaVecina(direccion).esLlena())) {
			this.casillaActual.eliminarEntidad(this);
			this.setCasillaActual(this.casillaActual.getCasillaVecina(direccion)) ;
		}
	}
	
	/**
	 * Hace desaparecer la Entidad.
	 * Este metodo elimina la Entidad de la casilla en la que se encuentra.
	 */
	public void desaparecer(){
		this.casillaActual.eliminarEntidad(this);
		this.casillaActual=null;
	}
	
	/**
	 * Metodo abstracto que  devuelve el alcance que tiene la Entidad para la iluminacion
	 * @return Se implementa en cada clase.
	 */
	public abstract int getAlcance();
	
}
