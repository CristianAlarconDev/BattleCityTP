package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class NivelModel {
    private final List<Jugador> jugadores;
    private final List<Enemigo> Enemigos;
    private final List<Bloque> bloques;

    private final double ancho;
    private final double alto;
    private final double celda;

    public NivelModel(String nombreJugador1, String nombreJugador2){
        this(nombreJugador1,nombreJugador2,800,600);
    }
    public NivelModel(String nombreJugador1){
        this(nombreJugador1,null,800,600);

    }
    public NivelModel(String nombreJugador1, String nombreJugador2, int ancho, int alto){
        this.jugadores=new ArrayList<>();
        this.Enemigos=new ArrayList<>();
        this.bloques=new ArrayList<>();
        this.ancho=ancho;
        this.alto=alto;
        this.celda=20;
        jugadores.add(new Jugador(nombreJugador1, 100,100,5));
        if (nombreJugador2!=null){
            jugadores.add(new Jugador(nombreJugador2, 200,100,5));
        }
    }
    public void moverJugador(int nroJugador,Direccion direccion){
        Jugador jugador=jugadores.get(nroJugador);
        /*chequeo con coord parciales si pasa el limite;
        mover a alguna clase o delegar esto, refactorizar*/
        double coordXNueva=(jugador.obtenerCoordenadaX())+
                (direccion.dX()*jugador.obtenerVelocidadBase());
        double coordYNueva=(jugador.obtenerCoordenadaY())+
                (direccion.dY()*jugador.obtenerVelocidadBase());

        if (jugadorDentroDeLimites(coordXNueva,coordYNueva)){
            jugador.mover(direccion);
        }
    }
    public boolean jugadorDentroDeLimites(double x, double y){
        return (x>=0&&x<=(ancho-celda))&&(y>=0&&y<=(alto-celda));
    }
    public void jugadorDisparar(int jugador){
        jugadores.get(jugador).intentarDisparar();
    }
    public List<Jugador> obtenerJugadores(){
        return this.jugadores;
    }

    public List<Enemigo> obtenerEnemigos() {
        return Enemigos;
    }
    public List<Bloque> obtenerBloques(){
        return bloques;
    }
}
