package org.modelo;

public class Vector2D {

    private double coordenadaX;
    private double coordenadaY;


    Vector2D(double coordenadaX, double coordenadaY){
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    //metodos nuevos de vector2d basado en la forma de uso o para mejor legibilidad
    public Vector2D copiar(){
        return new Vector2D(coordenadaX,coordenadaY);
    }
    public Vector2D escalado(double factor){
        return new Vector2D(coordenadaX*factor,
                coordenadaY*factor);
    }
    public Vector2D sumadoA(Vector2D vector2D){
        return new Vector2D(coordenadaX+vector2D.obtenerCoordenadaX(),
                coordenadaY+vector2D.obtenerCoordenadaY());
    }
    //fin metodos

    public void desplazar(Vector2D vector2D){
        this.coordenadaX += vector2D.obtenerCoordenadaX();
        this.coordenadaY += vector2D.obtenerCoordenadaY();
    }

    /*prueba para invertir coordenadas
     cuando llegue al limite del tablero*/
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
    public boolean esIgualA(Vector2D vector2D){
        return coordenadaX == vector2D.obtenerCoordenadaX()&&
        coordenadaY == vector2D.obtenerCoordenadaY();
    }
    public double obtenerCoordenadaY(){
        return coordenadaY;
    }

    public boolean esCasiIgualA(Vector2D vector2D, double epsilon){
        return Math.abs(coordenadaX - vector2D.obtenerCoordenadaX()) < epsilon &&
                Math.abs(coordenadaY - vector2D.obtenerCoordenadaY()) < epsilon;
    }
}
