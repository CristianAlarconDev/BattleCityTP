package org.modelo;

public abstract class Bloque  {
    protected Vector2D posicion;

    public Bloque(double coordenadaX, double coordenadaY){
        this.posicion = new Vector2D(coordenadaX,coordenadaY);
    }

    public Vector2D obtenerPosicion(){
        return new Vector2D(posicion.obtenerCoordenadaX(),
                posicion.obtenerCoordenadaY());
    }
    public double obtenerCoordenadaX(){
        return posicion.obtenerCoordenadaX();
    }
    public double obtenerCoordenadaY(){
        return posicion.obtenerCoordenadaY();
    }
    public abstract TipoBloque obtenerTipo();
    public abstract boolean impideElPaso();
    public abstract boolean bloqueaDisparo();
    public abstract boolean esDestructible();
    public abstract boolean esColisionable();



}
