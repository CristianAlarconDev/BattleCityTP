package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class JuegoModel {
    private List<NivelModel> niveles;
    private int nivelActual;
    public JuegoModel(int cantJugadores){
        niveles= new ArrayList<>();
        nivelActual=0;
        NivelModel nivelInicial = new NivelModel("jugador 1", "jugador 2");
        niveles.add(nivelInicial);
    }
    private NivelModel obtenerNivelActual(){
        return niveles.get(nivelActual);
    }
    public List<Jugador> obtenerJugadores(){
        return obtenerNivelActual().obtenerJugadores();
    }
    public List<Bloque> obtenerBloques(){
        return obtenerNivelActual().obtenerBloques();
    }
    public List<Enemigo> obtenerEnemigos(){
        return obtenerNivelActual().obtenerEnemigos();
    }

    /*acciones*/
    public void moverJugador(int nroJugador,Direccion direccion){
        obtenerNivelActual().moverJugador(nroJugador, direccion);
    }
    public void jugadorNroDispara(int nroJugador){
        obtenerNivelActual().jugadorDisparar(nroJugador);
    }
    public void actualizar(){

    }

}
