package org.modelo;

public enum OrigenDisparo {
    JUGADOR,
    ENEMIGO;

    public boolean esDisparoEnemigo(){
        return this == ENEMIGO;
    }
}
