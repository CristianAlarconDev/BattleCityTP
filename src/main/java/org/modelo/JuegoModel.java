package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class JuegoModel {
    private List<NivelModel> niveles;
    private int nivelActual;
    public JuegoModel(int cantJugadores){
        niveles= new ArrayList<>();
        nivelActual=0;
        CargadorDeNivel cargador = new CargadorDeNivel();
        try {
            NivelModel nivel = cargador.cargarNivel("nivel_de_prueba.xml", "levelConfig.xsd",cantJugadores,"cristian","juan");
        /*
        if (cantJugadores == 1) {
            nivelInicial = new NivelModel("jugador 1");
        } else if (cantJugadores == 2) {
            nivelInicial = new NivelModel("jugador 1", "jugador 2");
        } else {
            throw new IllegalArgumentException("Cantidad de jugadores no soportada: " + cantJugadores);
        }*/
            niveles.add(nivel);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
    public List<Disparo> obtenerDisparos(){
        return obtenerNivelActual().obtenerDisparos();
    }
    public void siguienteNivel(){
        nivelActual++;
    }
    public void reiniciarNivel(){
        nivelActual=0;
    }

    /*acciones*/
    public void moverJugador(int nroJugador,Direccion direccion){
        obtenerNivelActual().moverJugador(nroJugador, direccion);
    }
    public void jugadorNroDispara(int nroJugador){
        obtenerNivelActual().jugadorDisparar(nroJugador);
    }
    public void actualizar(){
        this.obtenerNivelActual().actualizar();
    }

}
