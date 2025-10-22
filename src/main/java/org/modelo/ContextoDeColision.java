package org.modelo;

import java.util.List;

public interface ContextoDeColision {
    List<Colisionable> obtenerColisionables();
    List<Disparo> obtenerDisparos();
    void eliminarColisionable(Colisionable colisionable);
    void eliminarDisparo(Disparo disparo);
    void agregarBloqueTanqueDestruido(Vector2D posicion);
}
