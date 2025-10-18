package org.modelo;

public interface Colisionable {
    ResultadoImpacto recibirImpacto(Disparo disparo);
    double obtenerCoordenadaX();
    double obtenerCoordenadaY();
    boolean impideElPaso();
    Vector2D obtenerPosicion();
    /*prueba de uso de nueva clase AreaColisionable*/
    AreaColisionable obtenerAreaColisionable();
    default boolean colisionaCon(Colisionable colisionable){
        return this.obtenerAreaColisionable().estaEnArea(colisionable.obtenerAreaColisionable());
    }
}

