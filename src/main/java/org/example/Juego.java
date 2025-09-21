package org.example;

import java.util.List;

public class Juego {

   // private List<Nivel> niveles;
    private int cantJugadores;

    Juego(int cantJugadores){
       // this.niveles=null;
        this.cantJugadores = cantJugadores;

    }
    public boolean jugar(){
        return cantJugadores > 0;
    }

}
