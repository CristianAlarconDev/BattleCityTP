package org.modelo;

public class Jugador extends Tanque{
    private String nombre;
    private ArmaUnDisparo arma;
    private boolean activo;
    private int id;


    public Jugador(String nombre, double x, double y, double velocidadMovBase) {
        super(x, y, velocidadMovBase);
        this.nombre = nombre;
        //cambiar luego en constructor de hacer falta
        //this.velocidadDeDisparo = velocidadMovBase;
        arma = new ArmaUnDisparo(velocidadMovBase);
        activo= false;
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
        return arma.disparar(obtenerPosicion(), obtenerDireccionActual());
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void activarJugador(boolean activo) {
        this.activo = activo;
    }
}
