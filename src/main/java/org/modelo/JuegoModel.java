package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class JuegoModel {
    private List<NivelModel> niveles;
    private int nivelActual;
    private NivelModel nivelEnJuego;
    private EstadoJuego estadoJuego;

    public JuegoModel(int cantJugadores){
        niveles= new ArrayList<>();
        nivelActual=0;
        CargadorDeNivel cargador = new CargadorDeNivel();
        estadoJuego=EstadoJuego.EN_CURSO;
        try {
            NivelModel nivel = cargador.cargarNivel("nivel4.xml", "levelConfig.xsd",cantJugadores,"cristian","juan");
            NivelModel nivel2 = cargador.cargarNivel("nivel5.xml", "levelConfig.xsd",cantJugadores,"cristian","juan");
            NivelModel nivel3 = cargador.cargarNivel("nivel3.xml", "levelConfig.xsd",cantJugadores,"cristian","juan");
            niveles.add(nivel);
            niveles.add(nivel2);
            niveles.add(nivel3);
            nivelEnJuego = nivel;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void actualizar(){
        if (estadoJuego!=EstadoJuego.EN_CURSO){
            return;
        }
        nivelEnJuego.actualizarMovimientos();
        if (nivelEnJuego.terminoEnDerrota()){
            estadoJuego=EstadoJuego.DERROTA;
        }
        else if (nivelEnJuego.terminoEnVictoria()){
            if (nivelActual +1>=niveles.size()){
                estadoJuego=EstadoJuego.VICTORIA;
            }
            else{
                estadoJuego=EstadoJuego.NIVEL_GANADO;
            }
        }

    }



    public List<Jugador> obtenerJugadores(){
        return nivelEnJuego.obtenerJugadores();
    }
    public List<Bloque> obtenerBloques(){
        return nivelEnJuego.obtenerBloques();
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


    public boolean tanqueEnMovimiento(Tanque tanque) {
        return this.nivelEnJuego.tanqueEnMovimiento(tanque);
    }

    public void siguienteNivel(){
        if (estadoJuego == EstadoJuego.NIVEL_GANADO) {
            nivelActual++;
            if(nivelActual < niveles.size()){
                nivelEnJuego = niveles.get(nivelActual);
                estadoJuego = EstadoJuego.EN_CURSO;
            }
            else{
                System.out.println("Juego terminado (esto no debería pasar nunca)");
            }
        } else {
            System.out.println("ERROR: Se llamó a siguienteNivel() con el estado: " + estadoJuego);
        }
    }
    public void moverJugador(int nroJugador,Direccion direccion){
        nivelEnJuego.moverJugador(nroJugador, direccion);
    }

    public void jugadorDispara(int nroJugador){

        nivelEnJuego.jugadorDisparar(nroJugador);
    }


    public boolean terminoNivelEnVictoria() {
        return this.estadoJuego == EstadoJuego.NIVEL_GANADO;
    }

    public boolean terminoJuegoEnVictoria() {
        return this.estadoJuego == EstadoJuego.VICTORIA;
    }

    public boolean terminoEnDerrota() {
        return this.estadoJuego == EstadoJuego.DERROTA;
    }


}
