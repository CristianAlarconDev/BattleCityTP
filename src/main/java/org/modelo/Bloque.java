package org.modelo;

public abstract class Bloque {
    protected Vector2D posicion;

    public Bloque(double coordenadaX, double coordenadaY){
        this.posicion = new Vector2D(coordenadaX,coordenadaY);
    }


    public Vector2D obtenerPosicion(){
        return new Vector2D(posicion.obtenerCoordenadaX(),
                posicion.obtenerCoordenadaY());
    }

    public abstract boolean bloqueaPasoTanque();
    public abstract boolean bloqueaDisparo();
    public abstract boolean esDestructible();
    public abstract void recibeimpacto();


}
