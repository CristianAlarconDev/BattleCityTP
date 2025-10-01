package org.modelo;
import java.io.InputStream;


public class CargadorDeNivelTest {
    public static void main(String[] args) {
        try {
            CargadorDeNivel cargador = new CargadorDeNivel();
            NivelModel nivel = cargador.cargarNivel("nivel_de_prueba.xml", "levelConfig.xsd",2,"cristian","juan");

            System.out.println("Jugadores:");
            for (Tanque j : nivel.obtenerJugadores()) {
                System.out.println("- " + j.getClass().getSimpleName()+ " en (" + j.obtenerPosicion().obtenerCoordenadaX() + "," + j.obtenerPosicion().obtenerCoordenadaY() + ")");

            }

            System.out.println("Enemigos:");
            for (Enemigo e : nivel.obtenerEnemigos()) {
                System.out.println("- " + e.getClass().getSimpleName()+ " en (" + e.obtenerPosicion().obtenerCoordenadaX() + "," + e.obtenerPosicion().obtenerCoordenadaY() + ")");
            }

            System.out.println("Bloques en la grilla:");
            for (Bloque b : nivel.obtenerBloques()) {
                System.out.println("- " + b.getClass().getSimpleName()+ " en (" + b.obtenerPosicion().obtenerCoordenadaX() + "," + b.obtenerPosicion().obtenerCoordenadaY() + ")");
            }

            /*
            for (int f = 0; f < nivel.getFilas(); f++) {
                for (int c = 0; c < nivel.getColumnas(); c++) {
                    Posicion pos = new Posicion(f, c);
                    Bloque b = nivel.getBloqueEnPosicion(pos);
                    if (b != null) {
                        System.out.println("- " + b.getClass().getSimpleName() + " en (" + f + "," + c + ")");
                    }
                }
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
