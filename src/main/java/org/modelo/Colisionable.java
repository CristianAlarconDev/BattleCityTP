package org.modelo;

public interface Colisionable {
    ResultadoImpacto recibirImpacto(Disparo disparo);
    double obtenerCoordenadaX();
    double obtenerCoordenadaY();
    boolean impideElPaso();

    Vector2D obtenerPosicion();
}

