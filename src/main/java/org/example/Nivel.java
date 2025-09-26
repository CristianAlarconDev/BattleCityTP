package org.example;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private static final int FILAS = 30;
    private static final int COLUMNAS = 40;

    private final List<Tanque> jugadores;
    private final List<Enemigo> enemigos;
    private final Bloque[][] grilla;

    public Nivel(int cantJugadores){
        jugadores = new ArrayList<>();
        enemigos = new ArrayList<>();
        this.grilla = new Bloque[FILAS][COLUMNAS];
    }

    public void agregarJugador(Tanque jugador){
        if (jugadores.size() < 2) {
            jugadores.add(jugador);
        }
    }

    public void agregarEnemigo(Enemigo enemigo){
        enemigos.add(enemigo);
    }

    public void agregarBloque(Bloque bloque){
        Vector2D pos = bloque.obtenerPosicion();
        if (pos.obtenerCoordenadaX() >= 0 && pos.obtenerCoordenadaX() < FILAS
                && pos.obtenerCoordenadaY() >= 0 && pos.obtenerCoordenadaY() < COLUMNAS) {
            int x = (int) pos.obtenerCoordenadaX();
            int y = (int) pos.obtenerCoordenadaY();
            grilla[x][y] = bloque;
        }
    }

    public Bloque getBloqueEnPosicion(Posicion posicion){
        if (posicion.getCoordenadaX() >= 0 && posicion.getCoordenadaX() < FILAS
                && posicion.getCoordenadaY() >= 0 && posicion.getCoordenadaY() < COLUMNAS) {
            return grilla[posicion.getCoordenadaX()][posicion.getCoordenadaX()];
        }
        return null;
    }

    public List<Tanque> getJugadores() {
        return jugadores;
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public int getFilas(){
        return FILAS;
    }

    public int getColumnas(){
        return COLUMNAS;
    }
}
