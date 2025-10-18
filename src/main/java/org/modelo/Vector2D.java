package org.modelo;

public class Vector2D {

    private double coordenadaX;
    private double coordenadaY;


    Vector2D(double coordenadaX, double coordenadaY){
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

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
    public void cambiarCoordenadas(double coordenadaX, double coordenadaY){
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }
    public boolean esCasiIgualA(Vector2D vector2D, double epsilon){
        return Math.abs(coordenadaX - vector2D.obtenerCoordenadaX()) < epsilon &&
                Math.abs(coordenadaY - vector2D.obtenerCoordenadaY()) < epsilon;
    }
}
