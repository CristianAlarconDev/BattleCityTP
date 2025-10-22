package org.modelo;

public abstract class Bloque  {
    protected Vector2D posicion;
    private int tamanio;
    private final boolean impideElPaso;
    private final boolean esColisionable;


    public Bloque(double coordenadaX, double coordenadaY, boolean impideElPaso, boolean esColisionable){
        this.posicion = new Vector2D(coordenadaX,coordenadaY);
        this.tamanio = ConstantesJuego.TAMANIO_BLOQUE;
        this.impideElPaso= impideElPaso;
        this.esColisionable= esColisionable;
    }

    public Vector2D obtenerPosicion(){
        return new Vector2D(posicion.obtenerCoordenadaX(),
                posicion.obtenerCoordenadaY());
    }
    public int obtenerTamanio(){
        return tamanio;
    }
    public double obtenerCoordenadaX(){
        return posicion.obtenerCoordenadaX();
    }
    public double obtenerCoordenadaY(){
        return posicion.obtenerCoordenadaY();
    }
    public abstract TipoBloque obtenerTipo();

    public boolean esColisionable(){
        return esColisionable;
    };
    public boolean impideElPaso(){
        return impideElPaso;
    };
}


