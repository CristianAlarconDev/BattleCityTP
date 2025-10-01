package org.modelo;

public class Jugador extends Tanque implements Colisionable {
    private String nombre;
    private ArmaUnDisparo arma;
    private boolean activo;
    private int id;
    private int tamanio;

    public Jugador(String nombre, double x, double y, double velocidadMovBase) {
        super(x, y, velocidadMovBase);
        this.nombre = nombre;
        //cambiar luego en constructor de hacer falta
        //this.velocidadDeDisparo = velocidadMovBase;
        arma = new ArmaUnDisparo(velocidadMovBase);
        this.tamanio=20;
        activo= false;
    }


    public boolean recibirImpacto(Disparo disparo) {
        if (disparo.esDeJugador()) {
            //se congela el jugador que recibe el disparo
            return false;
        }
        this.recibirDanio();
        return true;
    }


    public boolean estaActivo() {
        return activo;
    }

    public int getId() {
        return id;
    }



    public void cambiarVelocidadDeDisparo(double velocidadDeDisparo){
        arma.cambiarVelocidadDisparo(velocidadDeDisparo);
    }
    public double obtenerVelocidadDeDisparo(){
        return arma.obtenerVelocidadDisparo();
    }
    public Disparo intentarDisparar() {
        Vector2D posicionCentro= this.posicion;
        Vector2D direccionActual = obtenerDireccionActual().comoVector();
        Vector2D posicionDisparo = posicionCentro.sumadoA(
                direccionActual.escalado((tamanio / 2.0) + 3.0));
        return arma.disparar(posicionDisparo, obtenerDireccionActual(), OrigenDisparo .JUGADOR);
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void activarJugador(boolean activo) {
        this.activo = activo;
    }
}
