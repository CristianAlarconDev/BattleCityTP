package org.controlador;

import org.modelo.Direccion;
import org.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

public class JuegoController {

    private final List<Jugador> jugadores;
    public JuegoController(int  cantJugadores) {

        jugadores = new ArrayList<>();
        Jugador jugador1=new Jugador("jugador 1", 100, 100, 5);
        jugadores.add(jugador1);
        if (cantJugadores == 2){
            Jugador jugador2=new Jugador("jugador 2", 200, 100, 5);
            jugadores.add(jugador2);
        }

    }
    public List<Jugador> obtenerJugadores(){
        return this.jugadores;
    }

    public void moverJugador(int jugador ,Direccion direccion){
        jugadores.get(jugador).mover(direccion);

    }

}
