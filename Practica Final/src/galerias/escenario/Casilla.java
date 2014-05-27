package galerias.escenario;

/**
 * 
 * @author Cristian Folea y Francisco Javier S�nchez Moreno
 *
 */

/**
 * Las casillas representan los componentes b�sicos de las galer�as y est�n situadas 
 * en una posici�n. Una casilla puede estar conectada a otras casillas situadas en posiciones
 * adyacentes (arriba, abajo, izquierda y derecha). Asimismo, la casilla puede contener entidades 
 * del juego hasta una determina capacidad. Finalmente, las casillas pueden estar llenas 
 * de lava volc�nica (colapsadas).
 */

import galerias.entidades.Entidad;
import galerias.vista.Dibujable;

import java.util.HashSet;
import java.util.LinkedList;
public class Casilla  implements Cloneable,Dibujable {
	private final static int CAPACIDAD_DEFECTO = 5;
	private Posicion posicion;
	private boolean colapsada;
	private int capacidad;
	private Casilla casillasVecinas[] = new Casilla[4];
	private LinkedList<Entidad> listaEntidades = new LinkedList<Entidad> ();
	private boolean iluminada;

	
	/**
	 * Constructor de una Casilla con dos parametros.
	 * Este metodo crea una Casilla pasandole la posicion y la capacidad maxima.
	 * @param posicion La posicion donde se encuentra la casilla.
	 * @param capacidad La capacidad de la casilla , las entidades que caben.
	 */
	
	public Casilla (Posicion posicion, int capacidad){
		this.posicion = posicion;
		this.capacidad = capacidad;
		iluminada = false;
	}
	
	/**
	 * Constructor con un solo parametro.
	 * Este metodo crea una Casilla pasandole un solo parametro que es la posicion.
	 * La capacidad sera la capacidad por defecto que en esta situacion sera 5.
	 * @param posicion Es la posicion donde se encontrara la casilla.
	 */
	
	public Casilla (Posicion posicion){
		//XXX la capacidad por defecto debe definirse como una constante
		this (posicion, CAPACIDAD_DEFECTO);
	}
	
	
	/**
	 * Obtener la casilla vecina. 
	 * Este metodo nos devuelve el valor la Casilla vecina en el valor indicado, 
	 * en el caso de que no hay ninguna devuelve null.
	 * @param direccion La direccion en la que se busca la casilla vecina.
	 * @return null En el caso de que no hay casilla vecina en la direccion indicada.
	 * @return casillasVecinas[direccion.ordinal()] En el caso de encontrar casilla vecina.
	 */
	
	public Casilla getCasillaVecina(Direccion direccion) {
		//XXX no hace falta comprobar si es distinta de null
		return casillasVecinas[direccion.ordinal()];
	}
	
	//XXX Falta: boolean tieneVecina(Direccion)
	public boolean tieneVecina(Direccion direccion){
		if (this.casillasVecinas[direccion.ordinal()] != null) return true;
		else return false;
	}
	
	/**
	 * A�adir una entidad.
	 * Este metodo a�ade una entidad en una casilla. En el caso de que la entidad ya esta devuelve false.
	 * @param entidad La entidad que se quiere introducir.
	 * @return true/false Devuelve true si se ha realizado con exito, false en caso contrario.
	 */
	
	public boolean anadirEntidad(Entidad entidad){
		if (esLlena()) return false;
		else if (listaEntidades.contains(entidad)) return false;
		/*XXX en el else no hay que comprobar que no est� llena, es evidente que se cumple esa condici�n
		 *      porque de no cumplirse ha salido en el return anterior
		 */
		this.listaEntidades.add(entidad);
		return true;
		
	}
	
	/**
	 * Eliminar una entidad.
	 * Este metodo elimina una entidad de una casilla. En el caso de que no este devuelve falso.
	 * @param entidad La entidad que se quiere eliminar
	 * @return true/false Devuelve true en el caso de que se haya eliminado con exito, y false en caso contrario.
	 */
	
	public boolean eliminarEntidad(Entidad entidad){
		/*XXX el m�todo remove ya devuelve un valor boolean indicando si el elemento
		 *    que queremos borrar estaba o no contenido en la colecci�n
		 */
		return listaEntidades.remove(entidad);
	}
	
	/**
	 * Devuelve las entidades que se encuentran en una casilla.
	 * @return listaEntidades Lista de las entidades de una casilla.
	 */
	
	public LinkedList<Entidad> getEntidadesCasilla(){
		//XXX hay que devolver una copia de las colecciones
		return new LinkedList<Entidad>(listaEntidades);
	}
	
	/**
	 * Conecta dos casillas.
	 * Este metodo conecta dos casillas pasandole la direccion en la que se va a conectar y 
	 * la casilla a la que se va a conectar.
	 * En el caso de que una de las casillas esta conectada a otra en dicha direccion 
	 * la desconecta de la anterior y la conecta a la actual.
	 * Para que se puedan conectar dos casillas deben ser adyacentes.
	 * @param direccion La direccion en la que se quiere conectar.
	 * @param casilla La casilla a la que se quiere conectar.
	 * @return true/false True si se ha podido conectar y false en caso contrario.
	 */
	
	public boolean conectar(Direccion direccion, Casilla casilla){
		if(this.posicion.esAdyacente(casilla.posicion, direccion)) {
			if (this.getCasillaVecina(direccion) != null) //XXX reutilizad tieneVecina(direccion) o getVecina(direccion)
				this.desconectar(direccion);
			else if (casilla.getCasillaVecina(direccion.opuesta()) != null) //XXX reutilizad tieneVecina(direccion) o getVecina(direccion)
				casilla.desconectar(direccion.opuesta());
			this.casillasVecinas[direccion.ordinal()] = casilla;
			casilla.casillasVecinas[direccion.opuesta().ordinal()] = this;
					return true;
				}
		else 
			return false;	
	}
	
	
	/**
	 * Desconectar en una direccion.
	 * Este metodo desconecta la casilla sobre la que se aplica de su vecina en la direccion indicada si esa casilla vecina exista.
	 * @param direccion La direccion en la que se quiere desconectar.
	 * @return true/false True si se ha desconectado, false en caso contrario.
	 */
	
	public boolean desconectar(Direccion direccion){
		if (this.casillasVecinas[direccion.ordinal()] == null) return false;
		else{
			this.getCasillaVecina(direccion).casillasVecinas[direccion.opuesta().ordinal()] = null;
			this.casillasVecinas[direccion.ordinal()] = null;
			return true;
				}
	}
	
	/**
	 * Es llena.
	 * @return true/false Este metodo devuelve true o false dependiendo de si se ha alcanzado la capacidad maxima o no.
	 */
	
	public boolean esLlena (){
		if (this.getCapacidadLibre() == 0) return true;
		else return false;
	}
	
	/**
	 * Colapsar.
	 * Este metodo hace que una casilla se llene de lava.
	 */
	
	public void colapsar(){ 
		if (!esColapsada())
		this.colapsada = true;
	}
	
	/**
	 * Es Colapsada.
	 * Este metodo consulta si una casilla esta llena de lava o no.
	 * @return true/false true en caso de que este y false en caso contrario.
	 */
	
	public boolean esColapsada(){
		if (this.colapsada == true) return true;
		else return false;
	}
	
	/**
	 * Capacidad Libre.
	 * Devuelve la capacidad libre de una casilla. 
	 * @return int.
	 */
	
	public int getCapacidadLibre() {
		return (capacidad - listaEntidades.size());
	}
	
	/**
	 * Consulta la posicion de la casilla.
	 * @return posicion.
	 */
	
	public Posicion getPosicion() {
		return posicion;
	}
	/**
	 * Casillas vecinas.
	 * Devuelve una lista que contiene todas las casillas vecina de una casilla.
	 * @return ListaCasillas.
	 */
	
	public LinkedList<Casilla> getCasillasVecinas(){
		//XXX los nombres de las variables empiezan por min�sculas
		LinkedList<Casilla> listaCasillas = new LinkedList<Casilla> ();
		for (Casilla cas: this.casillasVecinas){
			if (cas != null) listaCasillas.add(cas);
		}
		return listaCasillas;
	}
	
	/**
	 * Expandir.
	 * Este metodo expande la lava a todas las casillas vecinas en el caso de que este colapsada, 
	 * en caso contrario no hace nada.
	 */
	
	public void expandir(){
		try {
			if (this.esColapsada()){
				for (Casilla cas :  casillasVecinas){ 
					//XXX sustituir por el bucle forEach sobre la lista de casillas vecinas que ya ha quitado las que son null
					if (cas != null)cas.colapsar();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	     /*XXX en caso contrario hay que lanzar una excepci�n
		 *      En el enunciado dice que es un requisito el que la casilla est� 
		 *      colapsada. El incumplimiento de un requisito se notifica lanzando
		 *      una excepci�n.
		 */
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacidad;
		result = prime * result + (colapsada ? 1231 : 1237);
		result = prime * result
				+ ((posicion == null) ? 0 : posicion.hashCode());
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
		Casilla other = (Casilla) obj;
		if (capacidad != other.capacidad)
			return false;
		if (colapsada != other.colapsada)
			return false;
		if (posicion == null) {
			if (other.posicion != null)
				return false;
		} else if (!posicion.equals(other.posicion))
			return false;
		return true;
	}
	
	@Override
	public Casilla clone(){
		Casilla copia = null;
		try {
			copia = (Casilla)super.clone();
			//XXX los atributos primitivos se copian en la copia superficial
			copia.posicion = posicion;
			copia.casillasVecinas = new Casilla[4];
			copia.listaEntidades = new LinkedList<Entidad>();
			/*XXX el clone de una casilla debe dejar la colecci�n de entidades y vecinas
			 *      de la copia apuntando a una colecci�n vac�a
			 */
		} catch (CloneNotSupportedException e) {
			System.out.println("No se ha podido copiar la casilla");
		}
		
		return copia;
	}

	@Override
	public String toString() {
		//XXX quitar el nombre de la clase de la cadena puesto que se obtiene con getClass().getName()
		return getClass().getName() + "[posicion=" + posicion + ", colapsada=" + colapsada
				+ ", numero vecinas = " + this.getCasillasVecinas().size() +", capacidad=" + capacidad  + "]";
		//XXX no muestra ninguna informaci�n acerca de las vecinas
	}

	@Override
	public String getImagen() {
		if(!colapsada)
			return "iluminada";
		else return "lava";
	}

	@Override
	public int getPosicionX() {
		return this.getPosicion().getX();
	}

	@Override
	public int getPosicionY() {
		return this.getPosicion().getY();
	}
	
	/** Metodo que apaga la luz de la casilla.
	 * 
	 */
	public void apagarLuz(){
		this.iluminada = false;
	}
	
	/** Metodo que enciende la luz de la casilla;
	 * 
	 */
	
	public void encenderLuz(){
		this.iluminada = true;
	}
	
	/** Metodo que comprueba si es iluminada una casilla.
	 * 
	 * @return true o false dependiendo de si es o no iluinada la casilla.
	 */
	
	public boolean esIluminada(){
		return iluminada;
	}
	
	/** Metodo privado que calcula las casillas a las que llega la luz.
	 * Este metodo calcula las casillas a las que llega la luz en funcion de la profundidad que se le
	 * pasa. 
	 * @param profundidad cuantas casillas se van a iluminar.
	 * @param casillasAIluminar casillas a las que debe llegar la luz.
	 * @param actual la casilla desde la que se empieza a buscar las casillas que se deben iluinar
	 * @return devuelve un conjunto de casillas.
	 */
	
	private HashSet<Casilla> alcance(int profundidad, HashSet<Casilla> casillasAIluminar, Casilla actual){
		while(profundidad > 0){
			for(Casilla casilla : actual.getCasillasVecinas()){
				casillasAIluminar.add(casilla);
				if(casilla.getCasillasVecinas().size() != 0)
					alcance(profundidad-1,casillasAIluminar, casilla);
		}
		profundidad--;
		}
		return casillasAIluminar;

	}
	
	/** Metodo expandir Luz.
	 * Este metodo expande la luz a las casillas a la que llega el alcance. 
	 * @param profundidad A cuantas vecinas debe expandir la Luz.
	 */
	public void expandirLuz(int profundidad){
		if(profundidad > 0){
			HashSet<Casilla> casillasSinIluminar = new HashSet<Casilla>();
			this.encenderLuz();
			for(Casilla casilla : alcance(profundidad-1, casillasSinIluminar, this)){
				casilla.encenderLuz();
			}
		}
	}
	
}
