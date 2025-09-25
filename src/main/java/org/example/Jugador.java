package org.example;

public class Jugador extends Tanque{
    private String nombre;
    private ArmaUnDisparo arma;

    public Jugador(String nombre, double x, double y, double velocidadMovBase) {
        super(x, y, velocidadMovBase);
        this.nombre = nombre;
        //cambiar luego en constructor de hacer falta
        //this.velocidadDeDisparo = velocidadMovBase;
        arma = new ArmaUnDisparo(velocidadMovBase);
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
}
