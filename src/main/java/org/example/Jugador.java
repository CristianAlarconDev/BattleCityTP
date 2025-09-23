package org.example;

public class Jugador extends Tanque{
    private String nombre;
    public Jugador(String nombre, double x, double y, double velocidadBase) {
        super(x, y, velocidadBase);
        this.nombre = nombre;
    }

    public Disparo disparar() {
        return new Disparo(obtenerPosicion(), obtenerDireccionActual(), obtenerVelocidadBase());
    }

    public String getNombre() {
        return nombre;
    }
}
