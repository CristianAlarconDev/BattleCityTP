package org.modelo;

public interface Colisionable {
    boolean recibirImpacto(Disparo disparo);
    double obtenerCoordenadaX();
    double obtenerCoordenadaY();

}
