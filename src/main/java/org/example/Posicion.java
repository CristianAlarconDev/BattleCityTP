package org.example;

public class Posicion {
    private int fila;
    private int columna;

    Posicion(int coordenadaX, int coordenadaY) {
        this.fila = coordenadaX;
        this.columna = coordenadaY;
    }

    public int getCoordenadaX() {
        return fila;
    }

    public int getCoordenadaY() {
        return columna;
    }

    public void cambiarA(int coordenadaX, int coordenadaY) {
        this.fila = coordenadaX;
        this.columna = coordenadaY;
    }

    public boolean esIgualA(int coordenadaX, int coordenadaY) {
        return this.fila == coordenadaX
                && this.columna == coordenadaY;

    }
}