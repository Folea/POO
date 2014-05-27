package galerias.partida;

import galerias.vista.Pantalla;

public class Inicio {

	public static void main(String[] args) {
		Partida partida = new Partida();

		Pantalla pantalla = new Pantalla(partida);
		partida.setPantalla(pantalla);

	}

}
