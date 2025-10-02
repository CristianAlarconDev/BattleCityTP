package org.modelo;

public class BloqueBosque extends Bloque {
    public BloqueBosque(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
    }


    public TipoBloque obtenerTipo() {
        return TipoBloque.BOSQUE;
    }

    @Override
    public boolean impideElPaso() {
        return false;
    }


    @Override
    public boolean esColisionable() {
        return false;
    }

}
