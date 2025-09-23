package org.example;

public enum Direccion {
    ARRIBA(0.0, -1.0),
    ABAJO(0.0, 1.0),
    IZQUIERDA(-1.0, 0.0),
    DERECHA(1.0, 0.0);
    private final Double coordenadaX;
    private final Double coordenadaY;

    Direccion(Double coordenadaX, Double coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public Vector2D comoVector() {
        return new Vector2D(coordenadaX, coordenadaY);
    }
}

