package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private static final int FILAS = 30;
    private static final int COLUMNAS = 40;
    private long inicioNivelMs;
    private long duracionNivelMs;
    private EstadoNivel estadoNivel;

    private final List<Jugador> jugadores;
    private final List<Enemigo> enemigos;
    private final List<Disparo> disparos;
    private final Bloque[][] grilla;


    public Nivel(int cantJugadores, long duracionNivelMs){
        jugadores = new ArrayList<>();
        enemigos = new ArrayList<>();
        disparos = new ArrayList<>();
        this.grilla = new Bloque[FILAS][COLUMNAS];
        inicioNivelMs = System.currentTimeMillis();
        estadoNivel=EstadoNivel.EN_CURSO;
    }


    public boolean enCurso(){
        return estadoNivel==EstadoNivel.EN_CURSO;
    }
    public boolean terminoEnVictoria(){
        return estadoNivel==EstadoNivel.VICTORIA;
    }
    public boolean terminoEnDerrota(){
        return estadoNivel==EstadoNivel.DERROTA;
    }

    public void agregarJugador(Jugador jugador){
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

    public List<Jugador> getJugadores() {
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


    public void jugadorDispara(int jugadorId) {
        Tanque tanque = jugadores.get(jugadorId);
        Disparo d = tanque.disparar();
        disparos.add(d);
    }

    public void activarJugadores(int cantidad) {
        for (int i = 0; i < cantidad && i < jugadores.size(); i++) {
            jugadores.get(i).activarJugador(true);
        }
    }




    public void actualizarNivel() {


        // muevo enemigos
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                enemigo.moverSegunIA();
                Disparo d = enemigo.disparar();
                if (d != null) {
                    disparos.add(d); // se agrega a la lista de disparos del nivel
                }
            }
        }


        // resolver colisiones
        resolverColisiones();

        // actualizar estado del nivel
        if (enemigos.isEmpty()) {
            estadoNivel = EstadoNivel.VICTORIA;
        } else if (jugadores.stream().allMatch(t -> !t.estaVivo())) {
            estadoNivel = EstadoNivel.DERROTA;
        }
    }



}
