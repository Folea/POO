package galerias.partida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.Timer;

import galerias.entidades.Cobarde;
import galerias.entidades.Diamante;
import galerias.entidades.Entidad;
import galerias.entidades.Jugador;
import galerias.entidades.Minero;
import galerias.entidades.Roca;
import galerias.entidades.Temerario;
import galerias.escenario.Casilla;
import galerias.escenario.Direccion;
import galerias.escenario.Estado;
import galerias.escenario.Galeria;
import galerias.escenario.Grieta;
import galerias.escenario.Posicion;
import galerias.escenario.Puerta;
import galerias.escenario.Salida;
import galerias.vista.Dibujable;
import galerias.vista.IControlador;
import galerias.vista.IPantalla;

/** La clase partida es la clase que implementa la partida,crea los niveles, el jugador,
 * crear la interfaz y actualizar los datos de la partida.
 *
 */

public class Partida implements IControlador, ActionListener{



		private Jugador jugador;
		private IPantalla pantalla;
		private Timer temporizador;
		private Galeria nivel;
		private int nivelActual = 0;
		
		/** Constructor de la Partida.
		 * Este metodo crea la partida, crea un nivel, iniciael temporizador y crea el jugador.
		 */
		
		public Partida() {
			nivel =  new Galeria(new Casilla(new Posicion(0,0)));
			temporizador =  new Timer(100,this);
			jugador = new Jugador(new Casilla(new Posicion(0,0)));

		}
		
		public void moverJugador(Direccion direccion){
			
		}
		
		/** Metodo action performed.
		 * Este metodo muesta la informacion del Jugador si la partida esta en curso, en caso contrario
		 * si la partida ha finalizado con exito carga el segundo nivel.Si ha fracasado para la partida. 
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (nivel.getEstado() == Estado.EN_CURSO)
				pantalla.setBarraEstado("Nivel: "+ (nivelActual
				) + " ---" + "Diamantes: " + jugador.getListaDiamantes().size() + " --- Kilates: " + jugador.getKilatesDiamantes());
				
			else if(nivel.getEstado() == Estado.FINALIZADO_EXITO){
					if (nivelActual == 1){			
						pantalla.abrirDialogo(" ", "AVANZA AL NIVEL 2");
						nivel = new Galeria(new Casilla(new Posicion(0,0)));
						this.nivel2();
						nivel.arrancar();
						temporizador.start();
					}
					else{
						temporizador.stop();
						pantalla.abrirDialogo("", "Enhorabuena!!! Has alcanzado la salida de la mina");
					}
				}
			else if(nivel.getEstado() == Estado.FINALIZADO_FRACASO){
					temporizador.stop();
					pantalla.abrirDialogo("", "GAME OVER!");
			}
		}


		@Override
		public void abrir(String fichero) {
			
			
		}
		/** Al pulsar la A, conmuta la puerta.
		 *
		 */
		
		@Override
        public void activar() {
                if(jugador.getCasillaActual() instanceof Puerta){
                        ((Puerta) jugador.getCasillaActual()).conmutar();
                        ((Puerta) jugador.getCasillaActual()).getImagen();
                }
        }

		@Override
		public boolean dibujarEscenario() {
			return true;
		}

		@Override
		public int getAltoEscenario() {
			return nivel.getAlto();
		}

		@Override
		public int getAnchoEscenario() {
			return nivel.getAncho();
		}
		
		/** Dibuja las casillas iluminadas y las entidades que estan en casillas iluminadas.
		 * 
		 */
		
		@Override
		public Collection<Dibujable> getDibujables() {
			LinkedList<Dibujable> dibujables =  new LinkedList<Dibujable>();
			for(Casilla casilla : nivel.getCasillasGaleria()){
				if(casilla.esIluminada())
				dibujables.add(casilla);
				for(Entidad entidad : casilla.getEntidadesCasilla()){
					if(casilla.esIluminada()) 
						dibujables.add(entidad);
					
				}
			}
			dibujables.add(jugador);
			return dibujables;
		}

		@Override
		public void mueveAbajo() {
			jugador.recibir(Direccion.ABAJO);
		}

		@Override
		public void mueveArriba() {
			jugador.recibir(Direccion.ARRIBA);
		}

		@Override
		public void mueveDerecha() {
			jugador.recibir(Direccion.DERECHA);
		}

		@Override
		public void mueveIzquierda() {
			jugador.recibir(Direccion.IZQUIERDA);
		}

		@Override
		public void nueva() {
			nivel.parar();
			nivel = new Galeria(new Casilla(new Posicion(0,0)));
			jugador = new Jugador(new Casilla(new Posicion(0,0)));
			this.nivel1();
			pantalla.abrirDialogo("", "EMPIEZA EL JUEGO!!!!");
			nivel.arrancar();
			temporizador.start();
			nivel.setEstado(Estado.EN_CURSO);
		}

		@Override
		public void setPantalla(IPantalla pantalla) {
			this.pantalla = pantalla;			
		}
		
		/** Metodo nivel 1;
		 *  Este metodo crea el primer nivel, cambiando la galeria nivel.
		 * 
		 */
		
		private void nivel1(){
		nivelActual = 1;
		Casilla[] casillas = new Casilla[29];		
		casillas[0] = new Casilla(new Posicion(1,0));
		casillas[1] = new Casilla(new Posicion(2,0));
		casillas[2] = new Casilla(new Posicion(3,0));
		casillas[3] = new Casilla(new Posicion(4,0));
		casillas[4] = new Casilla(new Posicion(5,0));
		casillas[5] = new Casilla(new Posicion(6,0));
		casillas[6] = new Casilla(new Posicion(6,1));
		casillas[7] = new Casilla(new Posicion(6,2));
		casillas[8] = new Puerta(new Posicion(6,3));
		casillas[9] = new Casilla(new Posicion(6,4));
		casillas[10] = new Casilla(new Posicion(6,5));
		casillas[11] = new Casilla(new Posicion(6,6));
		casillas[12] = new Casilla(new Posicion(6,7));
		casillas[13] = new Casilla(new Posicion(7,4));
		casillas[14] = new Casilla(new Posicion(8,4));
		casillas[15] = new Casilla(new Posicion(9,4));
		casillas[16] = new Casilla(new Posicion(5,3));
		casillas[17] = new Casilla(new Posicion(4,3));
		casillas[18] = new Casilla(new Posicion(3,3));
		casillas[19] = new Casilla(new Posicion(2,3));
		casillas[20] = new Casilla(new Posicion(1,3));
		casillas[21] = new Casilla(new Posicion(1,4));
		casillas[22] = new Casilla(new Posicion(1,5));
		casillas[23] = new Casilla(new Posicion(1,6));
		casillas[24] = new Salida(new Posicion(1,7));
		casillas[25] = new Casilla(new Posicion(3,4));
		casillas[26] = new Grieta(new Posicion(3,5));
		casillas[27] = new Grieta(new Posicion(3,6));
		casillas[28] = new Grieta(new Posicion(3,7));
				
		nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[0]);
		nivel.getCasillaActiva().anadirEntidad(jugador);
		nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[1]);
		nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[2]);
		nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[3]);
		nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[4]);
		nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[5]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[6]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[7]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[8]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[9]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[10]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[11]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[12]);
		nivel.setCasillaActiva(casillas[9]);
		nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[13]);
		nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[14]);
		nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[15]);
		nivel.setCasillaActiva(casillas[8]);
		nivel.insertarCasillaActiva(Direccion.IZQUIERDA, casillas[16]);
		nivel.insertarCasillaActiva(Direccion.IZQUIERDA, casillas[17]);
		nivel.insertarCasillaActiva(Direccion.IZQUIERDA, casillas[18]);
		nivel.insertarCasillaActiva(Direccion.IZQUIERDA, casillas[19]);
		nivel.insertarCasillaActiva(Direccion.IZQUIERDA, casillas[20]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[21]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[22]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[23]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[24]);
		nivel.setCasillaActiva(casillas[18]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[25]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[26]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[27]);
		nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[28]);
		
		
		Roca[] rocas = new Roca[2];
		Diamante[] diamantes = new Diamante[4];
		
		Minero minero = new Minero(casillas[17]);
		jugador.setCasillaActual(casillas[1]);
		diamantes[0] = new Diamante(casillas[0],6);
		diamantes[1] = new Diamante(casillas[7],13);
		diamantes[3] = new Diamante(13);
		rocas[0] = new Roca(casillas[10],diamantes[0]);
		rocas[1] = new Roca(casillas[18], diamantes[3]);
		Cobarde cobarde  = new Cobarde(casillas[8]);
		Temerario temerario = new Temerario(casillas[16]);
		}
		
		/** Este metodo crea el segundo nivel, cambiando la galeria nivel.
		 * 
		 */
		
		private void nivel2(){
			nivelActual = 2;
			Casilla[] casillas = new Casilla[32];
			
			casillas[0] = new Casilla(new Posicion(1,0));
			casillas[1] = new Casilla(new Posicion(2,0));
			casillas[2] = new Casilla(new Posicion(3,0));
			casillas[3] = new Casilla(new Posicion(4,0));
			casillas[4] = new Casilla(new Posicion(5,0));
			casillas[5] = new Grieta(new Posicion(9,0));
			casillas[6] = new Grieta(new Posicion(8,0));
			casillas[7] = new Casilla(new Posicion(8,1));
			casillas[8] = new Casilla(new Posicion(8,2));
			casillas[9] = new Puerta(new Posicion(7,2));
			casillas[10] = new Casilla(new Posicion(6,2));
			casillas[11] = new Casilla(new Posicion(5,2));
			casillas[12] = new Casilla(new Posicion(4,2));
			casillas[13] = new Casilla(new Posicion(3,2));
			casillas[14] = new Casilla(new Posicion(3,1));
			casillas[15] = new Casilla(new Posicion(3,3));
			casillas[16] = new Casilla(new Posicion(2,3));
			casillas[17] = new Casilla(new Posicion(1,3));
			casillas[18] = new Casilla(new Posicion(7,3));
			casillas[19] = new Casilla(new Posicion(7,4));
			casillas[20] = new Casilla(new Posicion(7,5));
			casillas[21] = new Casilla(new Posicion(6,5));
			casillas[22] = new Casilla(new Posicion(5,5));
			casillas[23] = new Casilla(new Posicion(4,5));
			casillas[24] = new Casilla(new Posicion(3,5));
			casillas[25] = new Casilla(new Posicion(2,5));
			casillas[26] = new Casilla(new Posicion(1,5));
			casillas[27] = new Casilla(new Posicion(1,4));
			casillas[28] = new Casilla(new Posicion(1,6));
			casillas[29] = new Casilla(new Posicion(1,7));
			casillas[30] = new Casilla(new Posicion(5,6));
			casillas[31] = new Salida(new Posicion(5,7));

			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[0]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[1]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[2]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[3]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[4]);
			
			nivel.setCasillaActiva(casillas[2]);
			
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[14]);
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[13]);
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[15]);
			nivel.insertarCasillaActiva(Direccion.IZQUIERDA, casillas[16]);
			nivel.insertarCasillaActiva(Direccion.IZQUIERDA, casillas[17]);
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[27]);
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[26]);
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[28]);
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[29]);
			
			nivel.setCasillaActiva(casillas[26]);
			
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[25]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[24]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[23]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[22]);
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[30]);
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[31]);

			nivel.setCasillaActiva(casillas[13]);
			
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[12]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[11]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[10]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[9]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[8]);
			nivel.insertarCasillaActiva(Direccion.ABAJO, casillas[7]);
			nivel.insertarCasillaActiva(Direccion.ABAJO, casillas[6]);
			nivel.insertarCasillaActiva(Direccion.DERECHA, casillas[5]);
			
			nivel.setCasillaActiva(casillas[9]);
			
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[18]);
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[19]);
			nivel.insertarCasillaActiva(Direccion.ARRIBA, casillas[20]);			
			
			jugador.setCasillaActual(casillas[2]);
			
			Roca[] rocas = new Roca[2];
			Diamante[] diamantes = new Diamante[5];
			
			diamantes[0] = new Diamante(casillas[30], 4);
			diamantes[1] = new Diamante(casillas[19], 12);
			diamantes[2] = new Diamante(casillas[4], 11);
			diamantes[3] = new Diamante(6);
			diamantes[4] = new Diamante(11);
			rocas[0] = new Roca(casillas[11], diamantes[3]);
			rocas[1] = new Roca(casillas[28], diamantes[4]);
			Cobarde cobarde  = new Cobarde(casillas[31]);
			Temerario temerario = new Temerario(casillas[15]);
			Minero minero = new Minero(casillas[13]);
		}
}