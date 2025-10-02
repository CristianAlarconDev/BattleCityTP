package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class NivelModel {
    private final List<Jugador> jugadores;
    private final List<Enemigo> enemigos;
    private final List<Bloque> bloques;
    private final List<PowerUp>powerUps;
    private List<Disparo> disparos;
    private final int tamanioCelda;
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
        this.enemigos=new ArrayList<>();
        this.bloques=new ArrayList<>();
        this.disparos=new ArrayList<>();
        this.powerUps=new ArrayList<>();
        this.tamanioDisparo=6;
        this.tamanioCelda=20;
        this.anchoNivel=ancho;
        this.altoNivel=alto;
        this.cantidadDeJugadores=cantidadDeJugadores;
        this.estadoNivel=EstadoNivel.EN_CURSO;

    }
    public void agregarBloque(Bloque bloque){
        this.bloques.add(bloque);
    }
    public void agregarEnemigo(Enemigo enemigo){
        this.enemigos.add(enemigo);
    }
    public void agregarJugador(Jugador jugador){
        this.jugadores.add(jugador);
    }
    private boolean colisionan(double x1, double y1, int r1,
                               double x2, double y2, int r2) {
        return Math.abs(x1 - x2) < (r1 + r2) &&
                Math.abs(y1 - y2) < (r1 + r2);
    }

    private boolean estaDentroDeLimites(double xCentro, double yCentro, int radio) {
        boolean dentroHorizontal = (xCentro - radio >= 0) && (xCentro + radio <= anchoNivel);
        boolean dentroVertical   = (yCentro - radio >= 0) && (yCentro + radio <= altoNivel);
        return dentroHorizontal && dentroVertical;
    }
    private boolean hayColisionConObstaculo(double xCentro, double yCentro, int radio) {
        for (Bloque bloque : bloques) {
            if(bloque.impideElPaso()){
                if (colisionan(xCentro, yCentro, radio,
                        bloque.obtenerCoordenadaX(), bloque.obtenerCoordenadaY(), tamanioCelda / 2)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean puedeMoverA(double x, double y, int radio) {
        return estaDentroDeLimites(x, y, radio) && !hayColisionConObstaculo(x, y, radio);
    }
    private void verificarColisionConPowerUps(Jugador jugador) {
        for (PowerUp powerUp : new ArrayList<>(powerUps)) {
            if (colisionan(
                    jugador.obtenerCoordenadaX(), jugador.obtenerCoordenadaY(), tamanioCelda/2,
                    powerUp.obtenerCoordenadaX(), powerUp.obtenerCoordenadaY(), tamanioCelda/2
            )) {
                powerUp.aplicarEfecto(jugador);
                powerUps.remove(powerUp);
                System.out.println("PowerUp " + powerUp.obtenerTipoPowerUp() + " consumido por " + jugador.obtenerNombre());
            }
        }
    }
    public void moverJugador(int nroJugador,Direccion direccion){
        Jugador jugador=jugadores.get(nroJugador);
        double coordXNueva=(jugador.obtenerCoordenadaX())+
                (direccion.dX()*jugador.obtenerVelocidadBase());
        double coordYNueva=(jugador.obtenerCoordenadaY())+
                (direccion.dY()*jugador.obtenerVelocidadBase());

        if (puedeMoverA(coordXNueva,coordYNueva,tamanioCelda/2)){
            verificarColisionConPowerUps(jugador);
            jugador.mover(direccion);
        }
    }

    public void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            enemigo.mover( obtenerBloquesColisionables() , anchoNivel,altoNivel, tamanioCelda/2);
        }
    }

    private boolean compararPosiciones(Disparo disparo, Colisionable colisionable) {
        int radioDisparo = tamanioDisparo / 2;
        int radioColisionable = tamanioCelda / 2;
        return colisionan(
                disparo.obtenerCoordenadaX(), disparo.obtenerCoordenadaY(), radioDisparo,
                colisionable.obtenerCoordenadaX(), colisionable.obtenerCoordenadaY(), radioColisionable
        );
    }

    private List<Colisionable> obtenerBloquesColisionables(){
        List<Colisionable> bloquesColisionables = new ArrayList<>();
        for (Bloque bloque : bloques) {
            if (bloque.esColisionable()) {
                bloquesColisionables.add((Colisionable) bloque);
            }
        }
        return bloquesColisionables;
    }


    private List<Colisionable> obtenerColisionables(){
        List<Colisionable> colisionables = new ArrayList<>();
        colisionables.addAll(jugadores);
        colisionables.addAll(enemigos);
        for (Bloque bloque : bloques) {
            if (bloque.esColisionable()) {
                colisionables.add((Colisionable) bloque);
            }
        }
        return colisionables;
    }
    private void intentarGenerarPowerUp(){
        if (Math.random() < 0.80) {
            TipoPowerUp tipoPowerUp = TipoPowerUp.random();
            double x = Math.random() * anchoNivel;
            double y = Math.random() * altoNivel;
            PowerUp powerUp = new PowerUp(x, y, tipoPowerUp);
            this.powerUps.add(powerUp);
        }
    }
    public void actualizarColisionesConDisparos(){
        List<Colisionable>colisionables= obtenerColisionables();

        for (Disparo disparo: new ArrayList<>(disparos)) {
            for (Colisionable colisionable : colisionables){
                if (compararPosiciones(disparo, colisionable)){
                    ResultadoImpacto resultado =colisionable.recibirImpacto(disparo);

                    if (resultado == ResultadoImpacto.ENEMIGO_ELIMINADO) {
                        this.enemigos.remove(colisionable);
                        intentarGenerarPowerUp();
                       // System.out.println("Enemigo eliminado, quedan: " + this.enemigos.size());
                    }

                    if (resultado == ResultadoImpacto.DESTRUIDO) {
                        bloques.remove(colisionable);
                    }

                    if (resultado == ResultadoImpacto.JUGADOR_ELIMINADO) {
                        jugadores.remove(colisionable);
                    }
                    if (resultado == ResultadoImpacto.BASE_DESTRUIDA) {
                        estadoNivel=EstadoNivel.DERROTA;
                        return;
                    }

                    disparo.desactivar();
                    disparos.remove(disparo);
                    break;
                }
            }
        }

    }

    public void actualizarMovimientos(){

        moverDisparos();
        actualizarColisionesConDisparos();
        disparoFueraDeLimites();
        moverEnemigos();
        enemigosDisparan();
        verificarEstadoNivel();

    }
    private void enemigosDisparan(){
        for (Enemigo enemigo : enemigos) {
            Disparo disparo =enemigo.disparar();
            if (disparo!=null){
                disparos.add(disparo);
            }
        }
    }
    private void moverDisparos(){
        for (Disparo disparo: new ArrayList<>(disparos)){
            disparo.mover();
        }
    }

    private void disparoFueraDeLimites(){
        for(Disparo disparo: new ArrayList<>(disparos)){
            if (!disparoDentroDeLimites(disparo.obtenerCoordenadaX(), disparo.obtenerCoordenadaY())){
                disparo.desactivar();
                disparos.remove(disparo);
            }
        }
    }

    private void verificarEstadoNivel(){
        //System.out.println("Verificar estado: enemigos=" + enemigos.size());
        if(this.enemigos.isEmpty()){
          //  System.out.println("ganaste el nivel");
            this.estadoNivel=EstadoNivel.VICTORIA;
        }
        if (jugadores.isEmpty()){
            this.estadoNivel=EstadoNivel.DERROTA;
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
        return enemigos;
    }
    public List<Bloque> obtenerBloques(){
        return bloques;
    }
    public List<PowerUp> obtenerPowerUps(){
        return powerUps;
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
