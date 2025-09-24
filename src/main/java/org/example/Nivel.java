package org.example;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private List<Jugador> jugadores;
    private List<Enemigo> enemigos;
    //private List<Bloque> bloques;

    Nivel() {
        jugadores = new ArrayList<>();
        enemigos = new ArrayList<>();
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }
    public boolean moverJugador(int numeroJugador, Direccion direccion) {
        if (numeroJugador < 0 || numeroJugador > jugadores.size()) {
            return false;
        }
        /*toda la logica de colisiones aca ya que nivel sabe donde estan los bloques*/
        Jugador jugador = jugadores.get(numeroJugador);
        jugador.mover(direccion);
        return true;
    }
    public void agregarEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
    }
    public void moverEnemigos(){
        for (Enemigo enemigo : enemigos) {
            /*toda la logica de colisiones aca ya que nivel sabe donde estan los bloques*/

            enemigo.mover();
        }
    }
}

