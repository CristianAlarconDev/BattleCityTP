package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NivelTest {

    @Test
    public void nivelAgregaYMueveUnJugador(){

        Nivel nivel = new Nivel(2, 60_000);
        Jugador jugador = new Jugador("Jugador", 0, 0, 2);
        nivel.agregarJugador(jugador);
        assert (nivel.moverJugador(0, Direccion.ARRIBA));

    }
    @Test
    public void nivelAgregaJugadorPeroNoSeMueve(){
        Nivel nivel = new Nivel(2, 60_000);
        Jugador jugador = new Jugador("Jugador", 0, 0, 2);
        nivel.agregarJugador(jugador);
        assertFalse(nivel.moverJugador(1, Direccion.ARRIBA));
    }
    @Test
    public void nivelAgregaYMueveUnEnemigo(){

    }

}