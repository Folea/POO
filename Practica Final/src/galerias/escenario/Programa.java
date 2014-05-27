package galerias.escenario;


import galerias.entidades.Minero;

public class Programa {


	public static void main(String[] args) {
		Posicion posicion1 = new Posicion();
		System.out.println("Constructor por defecto sin parametros, los valores son x = " + posicion1.getX() + " y = " + posicion1.getY());
		Grieta c1 = new Grieta(posicion1);
		
		Posicion posicion2 = new Posicion(1,0);
		System.out.println("Valores posicion2 construida a partir de coordenadas x = " + posicion2.getX() + " y = " + posicion2.getY());
		Casilla c2 = new Casilla(posicion2);
		
		Posicion posicion3 = new Posicion(2,0);
		Casilla c3 = new Casilla(posicion3);
		
		Posicion posicion4 = new Posicion(2,1);
		Casilla c4 = new Casilla(posicion4);
		
		Posicion posicion5 = new Posicion(2,2);
		Casilla c5 = new Casilla(posicion5);
		
		Posicion posicion6 = new Posicion(2,3);
		Casilla c6 = new Casilla(posicion6);
		
		Posicion posicion7 = new Posicion(1,2);
		Casilla c7 = new Casilla(posicion7);
		
		Posicion posicion8 = new Posicion(3,2);
		Casilla c8 = new Casilla(posicion8);
		
		Posicion posicion9 = new Posicion(4,2);
		Casilla c9 = new Casilla(posicion9);
		
		Posicion posicion10 = new Posicion(4,1);
		Casilla c10 = new Casilla(posicion10);
		
		//Pruebas Posicion
		System.out.println("posicion2 relativa a posicion1 = " + posicion1.relativa(posicion2)); //Muestra en que direccion se encuentra c2 con respecto a c1	
		System.out.println("posicion3 relativa a posicion1 = " + posicion1.relativa(posicion3)); 
		System.out.println("Distancia entre posicion1 y posicion7 = " + posicion1.distancia(posicion7)); //Distancia entre posicion1 y posicion7
		System.out.println("posicion2 es adyacente por la derecha de posicion1? = " + posicion1.esAdyacente(posicion2, Direccion.DERECHA)); //Prueba es adyacente deberia devolver true
		System.out.println("posicion3 es adyacente por la derecha a posicion1? = " + posicion1.esAdyacente(posicion3, Direccion.DERECHA)); //Prueba es adyacente deberia devolver false
		
		Posicion posicion11 = new Posicion(posicion1);
		System.out.println("Valor posicion 11 construido con el constructor de copia al crearla  x = " + posicion11.getX() + " y = " + posicion11.getY());
		
		posicion11.adyacente(Direccion.ARRIBA);
		System.out.println("Valor posicion 11 despues de aplicar adyacente x = " + posicion11.getX() + " y = " + posicion11.getY()); // Despues de aplicar adyacente
		
		System.out.println("\nTo string posicion 11 " + posicion11.toString());
		Posicion posicion12 = posicion11.clone();
		System.out.println("posicion11 == posicion12?= " + (posicion11 == posicion12));
		System.out.println("posicion11 es igual a posicion12, comparacion con equals? = " + posicion11.equals(posicion12));
		System.out.println("Hash Code posicion11 = " + posicion11.hashCode() + " Hash Code posicion12 = " + posicion12.hashCode());
		
		//Pruebas Casilla
		Casilla c = new Casilla (posicion1, 3);
		System.out.println("\nConstructor de casilla c pasandole posicion y capacidad = " + c.getCapacidadLibre());
		System.out.println("Constructor de casilla c2 pasandole solo posicion. capacidad = " + c2.getCapacidadLibre());
		//Conectar
		Casilla c11 = new Casilla (posicion2);
		System.out.println("\nConectar dos casillas que no tienen vecina = " + c.conectar(Direccion.DERECHA, c11) );
		System.out.println("Conectar las mimas casillas deberia devolver true en caso de que se pueda conectar casillas que tienen vecinas = " +
				c.conectar(Direccion.DERECHA, c11));
		//System.out.println("To string de una casilla = " + c.toString());
		Casilla c12 = new Casilla (posicion3);
		System.out.println("Conectar dos casillas que no son adyacentes = " + c.conectar(Direccion.DERECHA, c12));
		Posicion posicion = new Posicion(0,1);
		Casilla c13 = new Casilla (posicion);
		c.conectar(Direccion.ARRIBA, c13); // Conecto una casilla por arriba para comprobar si esta en la lista de casillasvecinas
		for (Casilla cas : c.getCasillasVecinas()){ //Compruebo las vecinas de c
			System.out.println("Lista casillas vecinas" + cas);
		}
		
		//Desconectar
		System.out.println("\nCasilla vecina por la derecha de c = " + c.getCasillaVecina(Direccion.DERECHA).getPosicion().toString());
		System.out.println("Resultado de desconectar dos casillas = " + c.desconectar(Direccion.DERECHA));
		System.out.println("Casilla vecina por la derecha de c = " + c.getCasillaVecina(Direccion.DERECHA));
		System.out.println("Resultado de desconectar en una direccion donde no hay vecina = " + c.desconectar(Direccion.DERECHA)) ;
		
		System.out.println("Es colapsada c? = " + c.esColapsada());
		c.colapsar();
		System.out.println("Es colapsada c tras utilizar c.colapsar? = " + c.esColapsada());
		
		System.out.println("CapacidadLibre = " + c.getCapacidadLibre());
		Minero entidad = new Minero();
		
		System.out.println("\nAnadir entidad que no esta = " + c.anadirEntidad(entidad));
		System.out.println("Esta la entidad en la lista de entidades? = " + c.getEntidadesCasilla().contains(entidad));
		System.out.println("Anadir entidad que ya esta = " + c.anadirEntidad(entidad));
		System.out.println("Eliminar entidad = " + c.eliminarEntidad(entidad));
		System.out.println("Esta la entidad eliminada? = " + c.getEntidadesCasilla().contains(entidad));
		System.out.println("Eliminar una entidad que no esta en la lista = " + c.eliminarEntidad(entidad));
		
		c.conectar(Direccion.DERECHA, c11);
		System.out.println("\nEstan colapsadas las casillas vecinas antes de expandir? c11 = " + c11.esColapsada() + " y c13 = " + c13.esColapsada());
		c.expandir();
		System.out.println("Estan colapsadas las casillas vecinas despues de expandir? c11 = " + c11.esColapsada() + " y c13 = " + c13.esColapsada());
		
		System.out.println("Esta colapsada la grieta? = " + c1.esColapsada());
		
		Puerta c14 = new Puerta(posicion4);
		c14.colapsar();
		c14.conectar(Direccion.ABAJO, c12);
		System.out.println("\nEs abierta c14? = " + c14.esAbierta());
		c14.conmutar();
		System.out.println("Es abierta c14 tras conmutar? = " +c14.esAbierta());
		System.out.println("Es colapsada la c12? = " + c12.esColapsada());
		c14.expandir();
		System.out.println("Se expandio la lava a c12 con la puerta cerada? = " + c12.esColapsada());
		c14.conmutar();
		c14.expandir();

		
		Salida c15 = new Salida(posicion5);
		System.out.println("\nCapacidadLibre antes de a�adir entidad = " + c15.getCapacidadLibre() );
		c15.anadirEntidad(entidad);
		System.out.println("CapacidadLibre despues de a�adir entidad = " + c15.getCapacidadLibre());
		
		System.out.println("\nTo string de una grieta = " + c1.toString());
		System.out.println("To string de una puerta = " + c14.toString());
		Casilla c16 = c2.clone();
		System.out.println("c2 == c16? = " + (c2 == c16));
		System.out.println("c2 es igual a c16? comparado con equals? = " + c2.equals(c16));
		System.out.println("Hash Code c2 = " + c2.hashCode() + " Hash Code c16 = " + c16.hashCode());
		System.out.println("Hash Code c2 = " + c2.hashCode() + " Hash Code c3 = " + c3.hashCode());
		
		
		//Creamos la Galeria
		Galeria galeria = new Galeria(c1);
		//Insertamos las casillas de la galeria
		galeria.insertarCasillaActiva(Direccion.DERECHA, c1);
		galeria.insertarCasillaActiva(Direccion.DERECHA, c2);
		galeria.insertarCasillaActiva(Direccion.DERECHA, c3);
		galeria.insertarCasillaActiva(Direccion.ARRIBA, c4);
		galeria.insertarCasillaActiva(Direccion.ARRIBA, c5);
		galeria.insertarCasillaActiva(Direccion.ARRIBA, c6);
		//Ponemos la casilla activa
		galeria.setCasillaActiva(c5);
		System.out.println("\nLa casilla activa despues de utilzar el metodo setCasillaActiva es el mismo que el parametro que se le pasa? = " +galeria.getCasillaActiva().equals(c5));
		galeria.insertarCasillaActiva(Direccion.IZQUIERDA, c7);
		System.out.println("Cambiar la casilla activa por la que su vecina a la derecha" + galeria.avanzarCasillaActiva(Direccion.DERECHA));
		galeria.avanzarCasillaActiva(Direccion.DERECHA);
		galeria.insertarCasillaActiva(Direccion.DERECHA, c8);
		galeria.insertarCasillaActiva(Direccion.DERECHA, c9);
		galeria.insertarCasillaActiva(Direccion.ABAJO, c10);
		//calculamos ancho y alto de la galeria
		System.out.println("El ancho de la galeria es " + galeria.getAncho());
		System.out.println("La altura de la galeria es " + galeria.getAlto());
		//Pruebas de expandir y de colapsar casillas
		c1.expandir();
		System.out.println("Expandir lava, se expande de c1 a c2? = " + c2.esColapsada()); 
		c2.expandir();
		System.out.println("Expandir lava de c2 a c3 = " + c3.esColapsada());
		System.out.println("Es colapsada c4? = " + c4.esColapsada());
		c3.expandir();
		System.out.println("Es colapsada c4, despues de c3.expandir? = " + c4.esColapsada());
		System.out.println("Recorremos las casillas que tiene la galeria para ver si se han insertado bien todas las casillas:");
		//mostramos las casillas de la galeria
		for (Casilla cas : galeria.getCasillasGaleria()){
			System.out.println("x = " + cas.getPosicion().getX() + " y = " + cas.getPosicion().getY());
		}
		//Mostramos donde esta la casilla inicial
		System.out.println("To string galeria = " + galeria.toString());
		
	}

}
