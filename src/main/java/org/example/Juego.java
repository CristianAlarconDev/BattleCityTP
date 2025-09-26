package org.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Juego {
    private List<Nivel> niveles;
    private int nivelActual;

    public Juego() {
        niveles = new ArrayList<>();
        nivelActual = 0;
        /*aca cargar los niveles con cargador de niveles*/
    }
    public void iniciarJuego() {
        while (nivelActual < niveles.size()) {
            Nivel nivel = niveles.get(nivelActual);
            System.out.println("Inicio  de nivel " +nivelActual);
            /*while(nivel.enCurso()){
                //aca irian los inputs de el/los jugadores
                //nivel.actualizar(movimientos)

            }*/
            if (nivel.terminoEnVictoria()){
                System.out.println("Termino en Victoria el nivel " + nivelActual+1);
                nivelActual++;
            }
            else if(nivel.terminoEnDerrota()){
                System.out.println("Termino en Derrota el nivel " + nivelActual+1);
                break;/*regreso al menu*/
            }
        }
        if (nivelActual == niveles.size()) {
            System.out.println("Termino el juego con todos los niveles ganados");
        }
    }
}
