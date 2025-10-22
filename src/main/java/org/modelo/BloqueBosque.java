package org.modelo;

public class BloqueBosque extends Bloque  {
    public BloqueBosque(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY(),false,false);
    }
    public TipoBloque obtenerTipo() {
        return TipoBloque.BOSQUE;
    }
}
