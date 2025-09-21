package org.example;

import java.util.ArrayList;
import java.util.List;

public class Juego {

   private List<Nivel> niveles;
   private int cantJugadores;

   Juego(int cantJugadores){
      this.niveles = new ArrayList<>();
      this.cantJugadores = cantJugadores;

      for (int i = 0; i < 2; i++) {
         niveles.add(new Nivel(cantJugadores, 1));
       }
    }

    public boolean jugar(){
        return cantJugadores > 0;
    }

}
