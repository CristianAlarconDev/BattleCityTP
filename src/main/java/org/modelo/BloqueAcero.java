package org.modelo;

public class BloqueAcero extends Bloque {

    public BloqueAcero(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.ACERO;
    }
    @Override
    public boolean impideElPaso() {
        return true;
    }

    @Override
    public boolean bloqueaDisparo() {
        return true;
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
