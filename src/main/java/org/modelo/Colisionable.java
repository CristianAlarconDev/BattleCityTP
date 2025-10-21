package org.modelo;

public interface Colisionable {
    ResultadoImpacto recibirImpacto(Disparo disparo);

    double obtenerCoordenadaX();

    double obtenerCoordenadaY();

    Vector2D obtenerPosicion();

    AreaColisionable obtenerAreaColisionable();
}