package galerias.escenario;



/**
 * 
 * @author Cristian Folea y Francisco Javier S�nchez Moreno
 *
 */
/**
 * La clase Puerta es un tipo de casilla. Una puerta es un tipo de casilla que puede estar abierta 
 * o cerrada, en caso de estar cerrada, no podria expandirse nada por �sta.
 */

import galerias.vista.Dibujable;
public class Puerta extends Casilla implements Dibujable{
	//XXX la subclase no tiene que implementar la interfaz lo hereda de Casilla
	private boolean abierta;
	
	/**
	 * Una puerta es una casilla que representa una puerta dentro de la galer�a. Inicialmente una puerta se encuentra abierta. 
	 * La clase proporciona m�todos para abrir y cerrar la puerta, para conmutar el estado (abierto/cerrado) de la puerta 
	 * y para comprobar si est� abierta. Como cualquier otra casilla, una puerta puede estar colapsada. 
	 * Sin embargo, la responsabilidad de esta casilla es la de no propagar la expansi�n de lava a las casillas vecinas si est� cerrada
	 */
	
	public Puerta(Posicion posicion) {
		super(posicion);
		abierta = true;
		
	}
	/**
	 * Constructor de una puerta con dos parametros.
	 * Construye una puerta en la posicion indicada, con la capacidad que se indica.
	 * @param posicion Posicion de la Puerta.
	 * @param capacidad
	 */
	
	public Puerta(Posicion posicion, int capacidad) {
		super(posicion, capacidad);
		//XXX abierta = true;
		abierta = true;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", [abierta = " + abierta + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (abierta ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		//XXX lo que he puesto como comentario sobra puesto que ya se comprueba en la llamada a super.equals
		if (!super.equals(obj))
			return false;
		Puerta other = (Puerta) obj;
		if (abierta != other.abierta)
			return false;
		return true;
	}
	
	@Override
	public Puerta clone(){
		Puerta copia = null;
		copia = (Puerta)super.clone();
		//XXX el atributo se ha copiado al hacer la copia superficial
		return copia;
	}

	/**
	 * Es abierta.
	 * Es consulta si la puerta es abierta o no.
	 * @return true/false True en el caso de que la puerta este abierta false en caso contrario.
	 */
	
	public boolean esAbierta (){
		return this.abierta;
	}
	
	/**
	 * Conmutar.
	 * Cambia el estado de la puerta de abierta a cerrada y alreves.
	 */
	
	public void conmutar(){
		if (this.abierta) this.abierta = false;
		else this.abierta = true;
	}
	
	/**
	 * Expandir.
	 * A diferencia del expandir de la clase casilla, este metodo consulta si la puerta esta abierta o cerrada.
	 * En el caso de que la puerta este abierta expande la lava, en caso contrario no puede expandir la lava.
	 */
	
	@Override
	public void expandir() {
		if (this.abierta)
			super.expandir();
	}
	
	@Override
	public String getImagen() {
		if(abierta)
			return "puerta-abierta";
		else return "puerta-cerrada"; 
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
