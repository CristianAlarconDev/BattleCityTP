package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class JuegoModel {
    private List<NivelModel> niveles;
    private int nivelActual;
    private NivelModel nivelEnJuego;
    public JuegoModel(int cantJugadores){
        niveles= new ArrayList<>();
        nivelActual=0;

        CargadorDeNivel cargador = new CargadorDeNivel();
        try {
            NivelModel nivel = cargador.cargarNivel("nivel1.xml", "levelConfig.xsd",cantJugadores,"cristian","juan");
            nivelEnJuego = nivel;
            NivelModel nivel2 = cargador.cargarNivel("nivel_de_prueba.xml", "levelConfig.xsd",cantJugadores,"cristian","juan");

            niveles.add(nivel);
            niveles.add(nivel2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void actualizar(){
        if(nivelEnJuego.enCurso()) {
            this.obtenerNivelActual().actualizar();
        } else {
            if (this.obtenerNivelActual().terminoEnVictoria()) {
                this.siguienteNivel();
            } else if (this.obtenerNivelActual().terminoEnDerrota()) {
                //vuelve al menu
            }
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
        if(nivelActual<=niveles.size()){
            nivelEnJuego=niveles.get(nivelActual);
        }
    }

    public boolean juegoTerminado(){
        return nivelActual>=niveles.size();
    }

    public void reiniciarNivel(){
        nivelActual=0;
    }

    /*acciones*/
    public void moverJugador(int nroJugador,Direccion direccion){
        obtenerNivelActual().moverJugador(nroJugador, direccion);
    }

    public void moverEnemigos(){

        obtenerNivelActual().moverEnemigos();
    }

    public void jugadorNroDispara(int nroJugador){
        obtenerNivelActual().jugadorDisparar(nroJugador);
    }


}
