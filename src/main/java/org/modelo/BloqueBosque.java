package org.modelo;

public class BloqueBosque extends Bloque {
    public BloqueBosque(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
    }

    @Override
    public boolean bloqueaPasoTanque() {
        return false;
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

    public boolean ocultaTanque() {
        return true; // la vista puede usar esto para dibujar tanques ocultos
    }

}
