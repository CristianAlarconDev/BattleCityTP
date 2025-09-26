package org.example;

public enum Direccion {
    ARRIBA,
    ABAJO,
    IZQUIERDA,
    DERECHA;

    public Vector2D comoVector() {
        return switch (this) {
            case ARRIBA -> new Vector2D(0, -1);
            case ABAJO -> new Vector2D(0, 1);
            case IZQUIERDA -> new Vector2D(-1, 0);
            case DERECHA -> new Vector2D(1, 0);
        };
    }
}

