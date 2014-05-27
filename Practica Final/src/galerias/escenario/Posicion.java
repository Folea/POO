package galerias.escenario;
/**
 * 
 * @author Cristian Folea y Francisco Javier S�nchez Moreno
 *
 */
//XXX Falta la descripci�n de la clase
/** Una posici�n se caracteriza por sus coordenadas x e y de tipo entero pudiendo 
 * 	tomar valores negativos. Esta clase ser� inmutable y por tanto ninguna operaci�n 
 *  podr� cambiar el valor de los atributos una vez construido el objeto.
 */
import java.lang.Math;
public class Posicion implements Cloneable {


	private final int x, y;
	
	/**
	 * Constructor pasandole parametros.
	 * @param x La coordenada x de la posicion.
	 * @param y Coordenada y de la posicion.
	 */
	
	public Posicion (int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor por defecto. 
	 * El constructor por defecto asigna se crea con x = 0  e y = 0. 
	 */
	
	public Posicion(){
		this(0,0);
	}

	/**
	 * Constructor de copia.
	 * A este constructor se le pasa como parametro una posicion y copia la coordenadas de dicha posicion.
	 * @param posicion La posicion a partir de la que se crea la nueva posicion.
	 */
	
	public Posicion(Posicion posicion){
		this(posicion.x, posicion.y);
	}
	
	/**
	 * Adyacente de una posicion.
	 * Cambia el valor de la posicion por el de su adyacente en la direccion indicada.
	 * @param direccion La direccion en la que se va a calcular la adyacente.
	 */
	
	public Posicion adyacente (Direccion direccion){
		/*XXX tiene que devolver un nuevo objeto posici�n que represente
		 *      una posici�n adyacente al objeto receptor, no cambiar los 
		 *      valores del objeto receptor, puesto que es inmutable
		 */
		switch(direccion){
		case ARRIBA:
			return new Posicion(this.x, this.y + 1);
		case ABAJO:
			return new Posicion(this.x, this.y - 1);
		case DERECHA:
			return new Posicion(this.x + 1, this.y);
		case IZQUIERDA:
			return new Posicion(this.x - 1, this.y);
		default: return null;
		}
	}
	
	/**
	 * Metodo de consulta.
	 * Este metodo devuelve el valor de x de la posicion indicada .
	 * @return x.
	 */
	
	public int getX() {
		return x;
	}

	/**
	 * Devuelve el valor de y.
	 * Este metodo devuelve el valor de y de la posicion indicada.
	 * @return y.
	 */
	
	public int getY() {
		return y;
	}

	/**
	 * Comprueba si una posicion tiene una casilla adyacente en una direccion.
	 * @param posicion La posicion con la que se comprueba si es adyacente.
	 * @param direccion La direccion en la que se comprueba si es adyacente.
	 * @return true/false Devuelve true o false dependiendo de si es verdad o no.
	 */
	
	public boolean esAdyacente (Posicion posicion, Direccion direccion){
		 /*XXX Este m�todo se simplifica creando la posici�n adyacente en esa direcci�n
		 *    y compar�ndola con la posici�n que os pasan como par�metro 
		 */
		return new Posicion(this.adyacente(direccion)).equals(posicion);
	}

	/**
	 * Obtener la direccion relativa.
	 * Pasandole una posicion nos devuelve en que direccion se encuentra respecto a la actual posicion.
	 * @param posicion La posicion que quieremos saber donde esta.
	 * @return direccion Devuelve donde esta la posicion pasada como parametro o en caso de que no este devuelve null.
	 */
	
	public Direccion relativa (Posicion posicion){
		/*XXX esta estructura condicional se simplifica utilizando un bucle que
		 *    recorra todas las direcciones contenidas en Direcciones.values()
		 *    y comprobando si es adyacente en cada direcci�n
		 */ 

		if (this.esAdyacente(posicion,Direccion.ARRIBA)) return Direccion.ARRIBA;
		else if (this.esAdyacente(posicion,Direccion.ABAJO)) return Direccion.ABAJO;
		else if (this.esAdyacente(posicion,Direccion.DERECHA)) return Direccion.DERECHA;
		else if (this.esAdyacente(posicion,Direccion.IZQUIERDA)) return Direccion.IZQUIERDA;
		else return null;
	}
	
	/**
	 * Calcula la distancia entre dos posiciones.
	 * Este metodo devuelve un entero que contiene el valor de la distancia entre dos posiciones.
	 * @param posicion La posicion sobre la que se quiere calcular la distancia.
	 * @return double El valor de la distancia.
	 */
	
	public double distancia(Posicion posicion){
		return Math.sqrt(Math.pow((this.x - posicion.x),2)+ Math.pow((this.y - posicion.y),2));
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "Posicion [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public Posicion clone(){
		Posicion copia = null;
		try {
			copia = (Posicion)super.clone();
			//XXX los atributos de tipo primitivo se copian en la copia superficial (llamada super.clone)
		} catch (CloneNotSupportedException e) {
			System.out.println("No ha podido copiarse la posicion");
		}
		return copia;
	}

	
}
