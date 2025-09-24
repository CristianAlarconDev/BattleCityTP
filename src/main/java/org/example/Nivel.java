package org.example;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private List<Jugador> jugadores;
    private List<Enemigo> enemigos;
    private int enemigosGeneradosMaximos;
    private long inicioNivel;
    private long ultimoSpawn;
    private long DURACION_NIVEL_MS;
    private long INTERVAL0_SPAWNS_MS = 20_000;
    //private List<Bloque> bloques;

    Nivel(int enemigosPorSpawnMaximos, long duracionNivelMS) {
        jugadores = new ArrayList<>();
        enemigos = new ArrayList<>();
        enemigosGeneradosMaximos=enemigosPorSpawnMaximos;
        DURACION_NIVEL_MS=duracionNivelMS;

    }
    public void cargarNivel(){
        inicioNivel = System.currentTimeMillis();
        ultimoSpawn = inicioNivel;
        /*se generan 2 enemigos al inicio del nivel, luego se puede
        * refactorizar esto o recibir un parametro para generar mas
        * enemigos de base*/
        agregarEnemigo(new Enemigo(10, 10, 2));
        agregarEnemigo(new Enemigo(20, 20, 2));

    }


    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }
    public boolean moverJugador(int numeroJugador, Direccion direccion) {
        if (numeroJugador < 0 || numeroJugador >= jugadores.size()) {
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

