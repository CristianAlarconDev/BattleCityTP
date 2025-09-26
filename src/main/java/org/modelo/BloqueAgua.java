package org.modelo;

public class BloqueAgua extends Bloque{
    public BloqueAgua(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
    }

    @Override
    public boolean bloqueaPasoTanque() {
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
    public void recibeimpacto() {
        // No hace nada
    }
}
