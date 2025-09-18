package org.example;

public class Posicion {
    private int coordenadaX;
    private int coordenadaY;
    Posicion(int coordenadaX, int coordenadaY){
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }
    public void cambiarA(int coordenadaX, int coordenadaY){
        /*validar que las coordenadas esten dentro del tablero*/
        /*validar que la nueva posicion no sea la misma*/
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public boolean esIgualA(int coordenadaX, int coordenadaY){
        return this.coordenadaX == coordenadaX
                && this.coordenadaY == coordenadaY;

    }
    public int obtenerCoordenadaX(){
        return coordenadaX;
    }
    public int obtenerCoordenadaY(){
        return coordenadaY;
    }
}
