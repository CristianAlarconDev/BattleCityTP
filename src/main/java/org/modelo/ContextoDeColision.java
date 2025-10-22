package org.modelo;

import java.util.List;

public interface ContextoDeColision {
    List<Colisionable> obtenerColisionables();
    List<Disparo> obtenerDisparos();
    List<PowerUp> obtenerPowerUps();
    List<Jugador> obtenerJugadores();
    void eliminarColisionable(Colisionable colisionable);
    void eliminarDisparo(Disparo disparo);
    void agregarBloqueTanqueDestruido(Vector2D posicion);
    void eliminarPowerUp(PowerUp powerUp);
}
