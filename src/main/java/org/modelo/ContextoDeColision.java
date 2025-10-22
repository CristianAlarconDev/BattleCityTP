package org.modelo;

import java.util.List;

public interface ContextoDeColision {
    List<Colisionable> obtenerColisionables();
    List<Disparo> obtenerDisparos();
    List<Jugador> obtenerJugadores();
    List<PowerUp> obtenerPowerUps();
    void eliminarColisionable(Colisionable colisionable);
    void eliminarDisparo(Disparo disparo);
    void eliminarPowerUp(PowerUp powerUp);
}
