package org.modelo;

public class BloqueAcero extends Bloque {

    public BloqueAcero(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
    }

    @Override
    public boolean bloqueaPasoTanque() {
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
    public void recibeimpacto() {
        // No hace nada
    }
}
