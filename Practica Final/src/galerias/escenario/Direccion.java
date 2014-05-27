package galerias.escenario;

//XXX He tenido que renombrar el proyecto que se llamaba "Practica Final"



/** La direccion es un enumerado y es una parte muy importante del juego ya que se utiliza 
 * en varios apartados del juego, tanto para el movimiento de personajes como para la 
 * conexion de casillas.
 */

import java.util.Random;
public enum Direccion {
	
	ARRIBA, ABAJO, IZQUIERDA, DERECHA;
	
	
/**
 * Direccion opuesta. 
 * Calcula la direccion opuesta a la direccion actual.
 * @return Direccion La direccion resultante, en el caso de que no se le pase una direccion correcta devuelve null
 */
	public Direccion opuesta(){
		switch (this) {
		case ARRIBA:
			return ABAJO;
		case ABAJO:
			return ARRIBA;
		case IZQUIERDA:
			return DERECHA;
		case DERECHA:
			return IZQUIERDA;
		default: 
			return null;
		}
	}
	
	/**
	 * Calcula una direccion aleatoria.
	 * @return Direccion devuelve una direccion aleatoria.
	 */
	//XXX aleatoria es un métodos static
	public static Direccion aleatoria(){
		Direccion[] direcciones = Direccion.values();
		Random generator = new Random();
		/*XXX una vez que generas un numero aleatorio sólo hay que acceder a direcciones[numero]
		 *      el switch siguiente sobra
		 */
		return direcciones[generator.nextInt(direcciones.length)];
	}
}
