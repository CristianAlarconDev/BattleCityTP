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
            NivelModel nivel2 = cargador.cargarNivel("nivel_de_prueba.xml", "levelConfig.xsd",cantJugadores,"cristian","juan");
            niveles.add(nivel);
            niveles.add(nivel2);
            nivelEnJuego = nivel;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void actualizar(){
        if (nivelEnJuego.enCurso()) {
            nivelEnJuego.actualizarMovimientos();
        } else if (nivelEnJuego.terminoEnVictoria()) {
            System.out.println("Victoria detectada en JuegoModel");
            siguienteNivel();
        } else if (nivelEnJuego.terminoEnDerrota()) {
            return;
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
        return this.nivelEnJuego.obtenerEnemigos();
    }
    public List<Disparo> obtenerDisparos(){
        return this.nivelEnJuego.obtenerDisparos();
    }
    public List<PowerUp> obtenerPowerUps(){
        return this.nivelEnJuego.obtenerPowerUps();
    }
    public void siguienteNivel(){
        nivelActual++;
        if(nivelActual<niveles.size()){
            nivelEnJuego=niveles.get(nivelActual);
        }
        else{
            System.out.println("Juego terminado");
        }
    }
    public boolean terminoEnVictoria() {
        return nivelEnJuego.terminoEnVictoria();
    }

    public boolean terminoEnDerrota() {
        return nivelEnJuego.terminoEnDerrota();
    }
    public boolean juegoTerminado(){

        return nivelActual>=niveles.size();
    }

    public void reiniciarNivel(){
        nivelActual = 0;
        if (!niveles.isEmpty()) {
            nivelEnJuego = niveles.get(nivelActual);
        }
    }

    public void moverJugador(int nroJugador,Direccion direccion){
        nivelEnJuego.moverJugador(nroJugador, direccion);
    }

    public void jugadorDispara(int nroJugador){

        nivelEnJuego.jugadorDisparar(nroJugador);
    }


}
