package org.modelo;

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
    public double dX(){
        return switch (this) {
            case ARRIBA, ABAJO -> 0;
            case IZQUIERDA -> -1;
            case DERECHA -> 1;
        };
    }
    public double dY(){
        return switch(this){
            case ARRIBA -> -1;
            case ABAJO -> 1;
            case IZQUIERDA, DERECHA -> 0;
        };
    }
}

