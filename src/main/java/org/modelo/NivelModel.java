package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class NivelModel {
    private final List<Jugador> jugadores;
    private final List<Enemigo> Enemigos;
    private final List<Bloque> bloques;

    public NivelModel(String nombreJugador1, String nombreJugador2){
        this.jugadores=new ArrayList<>();
        this.Enemigos=new ArrayList<>();
        this.bloques=new ArrayList<>();
        jugadores.add(new Jugador(nombreJugador1, 100, 100, 5));
        if (nombreJugador2!=null){
            jugadores.add(new Jugador(nombreJugador2, 200, 100, 5));
        }
    }
    public NivelModel(String nombreJugador1){
        this.jugadores=new ArrayList<>();
        this.Enemigos=new ArrayList<>();
        this.bloques=new ArrayList<>();
        jugadores.add(new Jugador(nombreJugador1, 100,100,5));

    }
    public void moverJugador(int jugador,Direccion direccion){
        jugadores.get(jugador).mover(direccion);
    }
    public void jugadorDisparar(int jugador){
        jugadores.get(jugador).intentarDisparar();
    }
    public List<Jugador> obtenerJugadores(){
        return this.jugadores;
    }

    public List<Enemigo> obtenerEnemigos() {
        return Enemigos;
    }
    public List<Bloque> obtenerBloques(){
        return bloques;
    }
}
