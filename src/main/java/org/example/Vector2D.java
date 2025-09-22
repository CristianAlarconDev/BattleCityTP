package org.example;

public class Vector2D {

    private double coordenadaX;
    private double coordenadaY;

    Vector2D(double coordenadaX, double coordenadaY){
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }
    public void agregar(double coordenadaX, double coordenadaY){
        this.coordenadaX += coordenadaX;
        this.coordenadaY += coordenadaY;
    }
    public void multiplicarPor(double factor){
        this.coordenadaX *= factor;
        this.coordenadaY *= factor;
    }
    /*prueba para invertir coordenadas
     cuando llegue al limites del tablero*/
    public void invertirCoordenadaX(){
        this.coordenadaX = -this.coordenadaX;
    }
    public void invertirCoordenadaY(){
        this.coordenadaY = -this.coordenadaY;
    }
    @Override
    public String toString() {
        return "(%f, %f)".formatted(coordenadaX, coordenadaY);
    }
    public double obtenerCoordenadaX(){
        return coordenadaX;
    }
    public double obtenerCoordenadaY(){
        return coordenadaY;
    }
}
