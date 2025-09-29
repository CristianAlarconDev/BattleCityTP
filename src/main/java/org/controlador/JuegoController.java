package org.controlador;

import org.modelo.*;


import java.util.List;

public class JuegoController {
    private final JuegoModel juego;

    public JuegoController(int  cantJugadores) {
        juego = new JuegoModel(cantJugadores);

    }
    public void moverJugador(int jugador ,Direccion direccion){
        juego.moverJugador(jugador, direccion);
    }
    public void jugadorNroDispara(int jugador){
        juego.jugadorNroDispara(jugador);
    }
    public List<Jugador> obtenerJugadores(){
        return juego.obtenerJugadores();
    }
    public List<Disparo> obtenerDisparos(){
        return juego.obtenerDisparos();
    }
    public List<Enemigo> obtenerEnemigos(){
        return juego.obtenerEnemigos();
    }
    public List<Bloque> obtenerBloques(){
        return juego.obtenerBloques();
    }
    public void actualizarJuego(){
        juego.actualizar();
    }

}
