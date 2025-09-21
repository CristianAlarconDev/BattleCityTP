package org.example;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private List<Tanque> jugadores;
    private List<Tanque> enemigos;
    private List<Bloque> bloques;
    private int dificultad;
    Nivel(int cantJugadores, int dificultad ){
        jugadores = new ArrayList<>();
        enemigos = new ArrayList<>();
        this.dificultad = dificultad;
        this.bloques= new ArrayList<>();

        generarJugadores(cantJugadores);
        generarEnemigos(dificultad);
    }


    public void generarBloque(String tipo){
        for(int i = 0; i < dificultad; i++){
            this.bloques.add(new Bloque(new Posicion(0,0),tipo));
        }
    }

    public void generarJugadores(int cantJugadores){
        for (int i = 0; i < cantJugadores; i++) {
            Tanque tanque = new Tanque(new Posicion(0, 0));
            jugadores.add(tanque);
        }
    }
    public void generarEnemigos(int cantEnemigos){
        for (int i = 0; i <cantEnemigos; i++){
            this.enemigos.add(new Tanque(new Posicion(0,0)));

        }
    }

}
