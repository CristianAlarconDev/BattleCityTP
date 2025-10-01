package org.modelo;

public class BloqueAgua extends Bloque{
    public BloqueAgua(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.AGUA;
    }
    @Override
    public boolean impideElPaso() {
        return true;
    }

    @Override
    public boolean bloqueaDisparo() {
        return false;
    }

    @Override
    public boolean esDestructible() {
        return false;
    }


    @Override
    public boolean esColisionable() {
        return false;
    }

}
