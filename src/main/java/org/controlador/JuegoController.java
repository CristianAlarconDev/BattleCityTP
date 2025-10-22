package org.controlador;

import org.modelo.*;


import java.util.List;

public class JuegoController {
    private final JuegoModel juego;

    public JuegoController(int cantJugadores) {
        juego = new JuegoModel(cantJugadores);

    }

    public void moverJugador(int nroJugador, Direccion direccion) {
        juego.moverJugador(nroJugador, direccion);
    }

    public void siguienteNivel() {
        juego.siguienteNivel();
    }

    public void jugadorDispara(int jugador) {
        juego.jugadorDispara(jugador);
    }
    public boolean terminoNivelEnVictoria() {
        return juego.terminoNivelEnVictoria();
    }
    public boolean terminoJuegoEnVictoria(){
        return juego.terminoJuegoEnVictoria();
    }
    public boolean terminoEnDerrota(){
        return juego.terminoEnDerrota();
    }
    public List<Jugador> obtenerJugadores() {
        return juego.obtenerJugadores();
    }

    public List<Disparo> obtenerDisparos() {
        return juego.obtenerDisparos();
    }

    public List<Enemigo> obtenerEnemigos() {
        return juego.obtenerEnemigos();
    }

    public List<Bloque> obtenerBloques() {
        return juego.obtenerBloques();
    }

    public List<PowerUp> obtenerPowerUps() {
        return juego.obtenerPowerUps();
    }

    public List<Integer> vidasJugadores(){
        return juego.obtenerVidasDeJugadores();
    }
    public void actualizarJuego() {
        juego.actualizar();
    }

    public boolean tanqueEnMovimiento(Tanque tanque) {
        return juego.tanqueEnMovimiento(tanque);
    }
}
