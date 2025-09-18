package org.example;

public enum Direccion {
    ARRIBA,
    ABAJO,
    IZQUIERDA,
    DERECHA,
    STANDBY;
    public boolean estaEnStandBy(){
        return this == STANDBY;
    }

}