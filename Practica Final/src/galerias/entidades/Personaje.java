package galerias.entidades;

/** Los personajes son un tipo de Entidad que se caracterizan por poder moverse y realizar acciones,
 * los personajes se pueden quema si estan mas del tiempo adecuado en una casilla colapsada y 
 * desaparecer.
 * 
 */

import galerias.escenario.Casilla;
import galerias.escenario.Direccion;

public abstract class Personaje extends Entidad {
	private long tiempoQuemadura = 0;	
	
	/**
	 * Constructor vacio de un Personaje
	 */
		
	public Personaje() {
			super();
		}
	
	/**
	 * Constructor de un Personaje, pasandole una casilla.
	 * @param casilla Casilla en la que se va a insertar el Personaje.
	 */

	public Personaje(Casilla casilla) {
		super(casilla);
	}

	/**
	 * Metodo abstracto que devuelve el proximo movimiento.
	 * Se calcula en cada Personaje ya que depende del personaje el proximo movimiento se implementa de forma
	 * distinta.
	 * @return Devuelve la Direccion del proximo Movimiento
	 */
	
	public abstract Direccion proximoMovimiento();

	/** Metodo control quemaduras de los Personajes.
	 * Este metodo controla si la casilla en la que se encuentra el jugador es colapsada o no,
	 * y en el caso de que lo sea inicializa una variable con el tiempo actual + 2000(2 s)
	 * para tener un control de cuanto tiempo esta el jugador en una casilla con lava.
	 * En el caso de que pase mas de 2 segundos lo hace desaparecer.
	 * 
	 */
	
	private void controlQuemaduras(){
		if (this.getCasillaActual()!=null){
		if(this.getCasillaActual().esColapsada()){
			if(tiempoQuemadura == 0) tiempoQuemadura = System.currentTimeMillis() + 2000;
			else if(System.currentTimeMillis() >= tiempoQuemadura) this.desaparecer();
		}
		else tiempoQuemadura = 0;
		}
		
	}

	/**
	 * Metodo abstracto de la accion de los Personajes.
	 * Se implementa en cada Personaje ya que cada personaje hace una accion distinta
	 */
	
	public abstract void accion();

	/**
	 * Metodo abstracto que obtiene el alcance de la Iluminacion
	 */
	
	public abstract int getAlcance();
	
	/**
	 * Metodo turno que se aplica sobre cada Personaje.
	 * Este metodo aplica el control de quemaduras y en el caso de que el Personaje
	 * no ha desaparecido lo mueve en su proxima direccion, y pone la direccion 
	 * en la que se movio el jugador a null para que no siga moviendose solo
	 * hasta que no se pulsa una tecla. Tambien aplica accion sobre del Personaje.
	 */
public void turno (){
	this.controlQuemaduras();
	if(this.getCasillaActual() != null){
		moverCasilla(proximoMovimiento());
		if (this instanceof Jugador) ((Jugador) this).recibir(null);
		this.accion();
		}
	}
	


}


