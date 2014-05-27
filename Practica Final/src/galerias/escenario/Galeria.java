package galerias.escenario;


/**
 * @author Cristian Folea y Francisco Javier S�nchez Moreno
 */
/**
 * XXX Una galer�a representa un grupo de casillas conectadas entre s�.
 * Es la plantilla del juego donde va a haber una grupo de casillas conectadas entre si sobre las cuales
 * se puede mover los personajes.
 * Se compone por una casilla inicial, que es la casilla a partir de la que se crea la galeria.
 * Tiene una casilla activa, que es la casilla a la que se pueden conectar otras casillas.
 * Tiene un temporizador que se encarga de hacer las acciones sobre los elementos de la casilla y un 
 * estado, que es el estado actual de la casilla.
 */
import galerias.entidades.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.Timer;

public class Galeria implements ActionListener{

	//private final Posicion posicionInicial = new Posicion(0,0); //XXX esto no es un atributo de la clase Galer�a
	private Casilla inicial = null; //XXX el valor inicial tiene que ser null
	private Casilla activa = null;  //XXX el valor inicial tiene que ser null
	private LinkedList<Casilla> casillas = new LinkedList<Casilla>();
	private Estado estado = null;
	private int segundos;
	private Timer timer = new Timer(500, this);
	private HashSet<Casilla> alcanzables  = new HashSet<Casilla>();
		

	/**
	 * Contruye una galeria
	 * Construye una galeria a partir de una casilla.Dicha casilla debe estar situada en la posicion 0,0.
	 * Si no cumple esa condicion saltara una excepcion.
	 * @param inicial La casilla a partir de la cual se crea la galeria.
	 */	
	
	public Galeria(Casilla inicial) {
		Posicion posicionInicial = new Posicion(0,0);
		if(inicial.getPosicion().equals(posicionInicial)){
		casillas.add(inicial);
		this.activa = inicial;
		this.inicial = inicial;
		estado = Estado.NO_INICIADO;
		}
		else throw new IllegalArgumentException("La casilla que le pasa como parametro a la galeria no es una casilla valida");
	}
	
	/**
	 * Inserta una casilla.
	 * Este metodo inserta una casilla en la galeria. Esa casilla debe ser adyacente en la direccion indicada a 
	 * la casilla activa y no puede tener vecina en la direccion en la que se encuentra la casilla activa.
	 * @param direccion La direccion en la que se quiere conectar respecto a la casilla activa.
	 * @param casilla La casilla que se quiere conectar.
	 */
	
	public void insertarCasilla(Direccion direccion, Casilla casilla){
		if (estado == Estado.NO_INICIADO)	
			if(casilla.getPosicion().getX()>=0 && casilla.getPosicion().getY()>=0  && 
					activa.getPosicion().esAdyacente(casilla.getPosicion(), direccion) && 
					(casilla.getCasillasVecinas().isEmpty())){
					/*XXX es un requisito que la casilla que se va a insertar no tenga vecinas.
					 *     si las tiene no se puede ejecutar el m�todo
					 */
				activa.conectar(direccion, casilla);
				this.casillas.add(casilla);
			}	
			else new IllegalAccessError("La casilla tiene que ser adyacente y no tener ninguna vecina");
			//XXX si no se cumplen los requisitos hay que lanzar una excepci�n. 
		else new IllegalAccessError("No se puede insertar la casilla, el juego esta en curso");
	}
	
	/**
	 * Casillas Galeria
	 * Este metodo devuelve la coleccion de casillas que estan en la galeria.
	 * @return casillas.
	 */

	public LinkedList<Casilla> getCasillasGaleria() {
		//XXX devolver copia de la colecci�n
		return new LinkedList<Casilla>(casillas);
	}

	/**
	 * Inserta una casilla activa.
	 * Este metodo inserta una casilla a la galeria al igual que el metodo insertarCasilla, pero la diferencia es que 
	 * este metodo hace que la casilla insertada sea la casilla activa.
	 * @param direccion Direccion en la que se quiere insertar.
	 * @param casilla Casilla que se quiere insertar.
	 */
	
	public void insertarCasillaActiva(Direccion direccion, Casilla casilla){
		insertarCasilla(direccion, casilla);
		activa=casilla;
	}

	/**
	 * Avanzar casilla activa
	 * Este metodo hace que la casilla activa cambia por la casilla que esta en la direccion indicada, 
	 * si dicha casilla existe.
	 * @param direccion La direccion donde esta la casilla por la que se queire cambiar.
	 * @return true/false True en en caso de que se realizo con exito y fals een caso contrario
	 */
	
	public boolean avanzarCasillaActiva(Direccion direccion){
		if(estado == Estado.NO_INICIADO){
			if(this.activa.getCasillaVecina(direccion)!= null){
				this.activa = this.activa.getCasillaVecina(direccion);
				return true;
			}
			return false;
		}
		return false;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	/** Cambia la casilla activa.
	 * Este metodo cambia la casilla activa por la que le pasa como parametro.
	 * @param casilla
	 */
	public void setCasillaActiva(Casilla casilla){
		if(estado == Estado.NO_INICIADO){
			try {
				this.casillas.contains(casilla);
				activa = casilla;
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}		
		else new IllegalAccessException("La casilla no esta dentro de las casillas de la galeria");
		/*XXX else --> hay que lanzar una excepci�n. Es un requisito
		 *      para la ejecuci�n del m�todo 
		 */
	}
	
	/** 
	 * Ancho de la Galeria.
	 * Este metodo devuelve el ancho de la galeria, la distancia en el eje x entre la casilla 0,0  e la casilla mas lejana.
	 * @return x Devuelve el ancho.
	 */
	
	public int getAncho(){
		int x = 0;
		for (Casilla cas : casillas){
			if (cas.getPosicion().getX() > x) x = cas.getPosicion().getX() + 1;
		}
		return x;
	}
	
	/** Altura de la Galeria
	 * Este metodo devuelve la altura de la galeria, la distancia en el eje y entre la casilla 0,0  
	 * y la casilla mas lejana.
	 * @return y Devuelve la altura.
	 */
	
	public int getAlto(){
		int y = 0;
		for (Casilla cas : casillas){
			if (cas.getPosicion().getY() > y) y = cas.getPosicion().getY() + 1;
		}
		return y;	
	}
		
	/**
	 * Consulta la casilla inicial. 
	 * @return inicial.
	 */
	
	/*public Casilla getCasillaInicial() {
		return inicial;
	}*/
	
	//XXX la casilla inicial no cambia!! Este m�todo no debe estar disponible

	/**
	 * Consulta la casilla activa.
	 * @return activa.
	 */
	
	public Casilla getCasillaActiva(){
		return activa;		
	}	
		
	@Override
	public String toString() {
		return getClass().getName() + "[inicial="
				+ inicial + ", activa=" + activa +  ", Ancho = " + getAncho() + ", Atlro = " + getAlto()
				+ "]";
	}

	
	/** Metodo action performed.
	 * Este metodo es llamado cada cierto tiempo por el timer y lo que hace es iluminar las casillas
	 * que estan al alcance de los personajes, aplica el turno a los personajes y expande la lava de
	 * las casillas cada 5 llamadas del timer. 
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
	++segundos;
	apagarLuzTodas();
		LinkedList<Casilla> casillas = new LinkedList<Casilla>();
		LinkedList<Personaje> entidades = new LinkedList<Personaje>();
		
		
		for (Casilla casilla : this.getCasillasGaleria()){
			if(casilla.esColapsada()) casillas.add(casilla);
			for(Entidad entidad : casilla.getEntidadesCasilla())
					if (entidad instanceof Personaje){
						entidades.add((Personaje) entidad);
					}
					else if((entidad instanceof Diamante) && ((Diamante) entidad).ilumnina())
						entidad.getCasillaActual().encenderLuz();
		}
		
		for (Personaje personaje : entidades){
			((Personaje) personaje).turno();
			if(personaje.getCasillaActual() != null){
				if(personaje instanceof Jugador){
						personaje.getCasillaActual().expandirLuz(personaje.getAlcance());
						if(personaje.getCasillaActual() instanceof Salida){
						setEstado(Estado.FINALIZADO_EXITO);
						timer.stop();
						}
					}						
				else if(personaje instanceof Minero){
					personaje.getCasillaActual().expandirLuz(personaje.getAlcance());
					

				}
				}
			else if(personaje instanceof Jugador){
				setEstado(Estado.FINALIZADO_FRACASO);
				timer.stop();
			}
		}
		if (segundos % 5 == 0) for (Casilla casilla : casillas) casilla.expandir();
	}

		public void setEstado(Estado estado) {
		this.estado = estado;
	}

		/** Metodo arrancar.
		 * Este metodo comprueba si el juego no esta en curso o no ha fracasado, si tiene un jugador
		 * y una salida. Si se cumple esas condiciones puede empezar el juego, por lo tanto inicia el 
		 * timer para que empiece a llamar a action performed y hacer que el juego funcione.
		 * 
		 */
		
		public void arrancar(){
		boolean jugador = false;
		boolean salida = false;
		for(Casilla casilla : this.getCasillasGaleria()){
			for(Entidad entidad : casilla.getEntidadesCasilla()){
				if (entidad instanceof Jugador) jugador = true;
			}
			if (casilla instanceof Salida) salida = true;
		}
		if((estado == Estado.FINALIZADO_EXITO || estado == Estado.NO_INICIADO) &&
				jugador == true && salida == true){
		estado = Estado.EN_CURSO;
		timer.start();		
		}
	}
		
		/** Metodo parar.
		 * Este metodo para el timer, por lo tanto para el juego.
		 */
	public void parar(){
		timer.stop();
	}
	
	/** Metodo que apaga la luz en todas las casillas de la Galeria.
	 * 
	 */
	
	public void apagarLuzTodas(){
		for(Casilla casilla : getCasillasGaleria()){
			casilla.apagarLuz();
		}
		alcanzables.clear();
	}
		
}
