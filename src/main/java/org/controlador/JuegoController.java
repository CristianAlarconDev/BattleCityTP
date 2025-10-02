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

    public boolean terminoEnVictoria() {
        return juego.terminoEnVictoria();
    }

    public boolean terminoEnDerrota() {
        return juego.terminoEnDerrota();
    }

    public void siguienteNivel() {
        juego.siguienteNivel();
    }

    public void jugadorDispara(int jugador) {
        juego.jugadorDispara(jugador);
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

    public void actualizarJuego() {
        juego.actualizar();
    }

    public boolean enemigoEnMovimiento(Enemigo enemigo) {
        return juego.enemigoEnMovimiento(enemigo);
    }
    public boolean jugadorEnMovimiento(Jugador jugador){
        return juego.jugadorEnMovimiento(jugador);
    }
}
