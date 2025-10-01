package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class NivelModel {
    private final List<Jugador> jugadores;
    private final List<Enemigo> Enemigos;
    private final List<Bloque> bloques;
    private List<Disparo> disparos;
    private final int tamanioJugador;
    private final int tamanioDisparo;
    private final int anchoNivel, altoNivel;
    private int cantidadDeJugadores;
    private EstadoNivel estadoNivel;


    public NivelModel(String nombreJugador1, String nombreJugador2){
        this(nombreJugador1,nombreJugador2,800,600,2);
    }

    public NivelModel(String nombreJugador1){
        this(nombreJugador1,null,800,600,2);

    }
    public NivelModel(String nombreJugador1, String nombreJugador2, int ancho, int alto, int cantidadDeJugadores){
        this.jugadores=new ArrayList<>();
        this.Enemigos=new ArrayList<>();
        this.bloques=new ArrayList<>();
        this.disparos=new ArrayList<>();

        this.tamanioDisparo=6;
        this.tamanioJugador=20;
        this.anchoNivel=ancho;
        this.altoNivel=alto;
        this.cantidadDeJugadores=cantidadDeJugadores;
        this.estadoNivel=EstadoNivel.EN_CURSO;

    }
    public void agregarBloque(Bloque bloque){
        this.bloques.add(bloque);
    }
    public void agregarEnemigo(Enemigo enemigo){
        this.Enemigos.add(enemigo);
    }
    public void agregarJugador(Jugador jugador){
        this.jugadores.add(jugador);
    }
    private boolean seSuperponen(double x1, double y1, int r1,
                                 double x2, double y2, int r2) {
        boolean colisionX = Math.abs(x1 - x2) < (r1 + r2);
        boolean colisionY = Math.abs(y1 - y2) < (r1 + r2);
        return colisionX && colisionY;
    }
    private boolean hayColisionConObstaculo(double xCentro, double yCentro) {
        for (Bloque bloque : bloques) {
            if(bloque.impideElPaso()){
                double xCentroBloque = bloque.obtenerCoordenadaX();
                double yCentroBloque = bloque.obtenerCoordenadaY();

                if (seSuperponen(xCentro, yCentro, 20 / 2,
                        xCentroBloque, yCentroBloque, 20 / 2)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void moverJugador(int nroJugador,Direccion direccion){
        Jugador jugador=jugadores.get(nroJugador);
        double coordXNueva=(jugador.obtenerCoordenadaX())+
                (direccion.dX()*jugador.obtenerVelocidadBase());
        double coordYNueva=(jugador.obtenerCoordenadaY())+
                (direccion.dY()*jugador.obtenerVelocidadBase());

        if (jugadorDentroDeLimites(coordXNueva,coordYNueva)&&
                !hayColisionConObstaculo(coordXNueva, coordYNueva)){
            jugador.mover(direccion);
        }
    }
    private boolean jugadorDentroDeLimites(double xCentro, double yCentro){
        int radioJugador = tamanioJugador / 2;

        boolean dentroHorizontal = (xCentro - radioJugador >= 0) && (xCentro + radioJugador <= anchoNivel);
        boolean dentroVertical   = (yCentro - radioJugador >= 0) && (yCentro + radioJugador <= altoNivel);

        return dentroHorizontal && dentroVertical;
    }

    private boolean compararPosiciones(Disparo disparo, Colisionable colisionable){
        double xColisionable = colisionable.obtenerCoordenadaX();
        double yColisionable = colisionable.obtenerCoordenadaY();
        double xDisparo = disparo.obtenerCoordenadaX();
        double yDisparo = disparo.obtenerCoordenadaY();
        int radioDisparo = tamanioDisparo / 2;
        int radioColisionable = tamanioJugador / 2;
        boolean colisionaEnX = Math.abs(xDisparo - xColisionable) <= (radioDisparo + radioColisionable);
        boolean colisionaEnY = Math.abs(yDisparo - yColisionable) <= (radioDisparo + radioColisionable);

        return colisionaEnX && colisionaEnY;
    }

    public void actualizarColisionesConDisparos(){
        List<Colisionable>colisionables= new ArrayList<>();
        colisionables.addAll(jugadores);
        colisionables.addAll(Enemigos);
        for (Bloque bloque : bloques) {
            if (bloque.esColisionable()) {
                colisionables.add((Colisionable) bloque);
            }
        }

        for (Disparo disparo: new ArrayList<>(disparos)) {
            for (Colisionable colisionable : colisionables){
                if (compararPosiciones(disparo, colisionable)){
                    colisionable.recibirImpacto(disparo);
                    disparo.desactivar();
                    disparos.remove(disparo);
                    break;
                }
            }
        }
    }

    public void actualizar(){

        for (Disparo disparo: new ArrayList<>(disparos)){
            disparo.mover();
        }

        actualizarColisionesConDisparos();

        for(Disparo disparo: new ArrayList<>(disparos)){
            if (!disparoDentroDeLimites(disparo.obtenerCoordenadaX(), disparo.obtenerCoordenadaY())){
                disparo.desactivar();
                disparos.remove(disparo);
            }
        }
    }

    private boolean disparoDentroDeLimites(double xCentro, double yCentro){
        int radioDisparo = tamanioDisparo / 2;

        boolean dentroHorizontal = (xCentro - radioDisparo >= 0) && (xCentro + radioDisparo <= anchoNivel);
        boolean dentroVertical   = (yCentro - radioDisparo >= 0) && (yCentro + radioDisparo <= altoNivel);

        return dentroHorizontal && dentroVertical;

    }

    public void jugadorDisparar(int nroJugador){
        Jugador jugador=jugadores.get(nroJugador);
        try {
           Disparo disparo=jugador.intentarDisparar();
           disparos.add(disparo);
        }catch (Exception e){
            /*agregar algun cartel en consola o alerta*/
        }
    }

    public List<Disparo> obtenerDisparos(){
        return disparos;
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

    public boolean enCurso(){
        return estadoNivel==EstadoNivel.EN_CURSO;
    }

    public boolean terminoEnVictoria(){
        return estadoNivel==EstadoNivel.VICTORIA;
    }
    public boolean terminoEnDerrota(){
        return estadoNivel==EstadoNivel.DERROTA;
    }
}
