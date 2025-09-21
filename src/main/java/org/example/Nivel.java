package org.example;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private List<Tanque> jugadores;
    private List<Tanque> enemigos;
    private int dificultad;
    Nivel(int cantJugadores, int dificultad){

        jugadores = new ArrayList<>();
        for (int i = 0; i < cantJugadores; i++){
            Tanque tanque = new Tanque(new Posicion(0,0));
            jugadores.add(tanque);
        }

    }

}
