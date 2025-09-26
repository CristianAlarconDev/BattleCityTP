package org.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JugadorTest {
    private double xInicial;
    private double yInicial;
    private double velocidadBase;
    private Direccion derecha;
    private Direccion arriba;

    @BeforeEach
    public void setUp(){
        xInicial = 0;
        yInicial = 0;
        velocidadBase = 2;
        derecha = Direccion.DERECHA;
        arriba = Direccion.ARRIBA;
    }

    @Test
    public void disparoSeGeneraEnPosicionJugador(){
        Jugador jugador = new Jugador("Jugador", xInicial, yInicial, velocidadBase);
        Disparo disparo = jugador.intentarDisparar();

        assert(disparo.estaEnPosicion(jugador.obtenerPosicion()));
    }
    @Test
    public void disparoSeMueveEnDireccionDelJugador(){
        Jugador jugador = new Jugador("Jugador", xInicial, yInicial, velocidadBase);
        jugador.mover(derecha);
        Disparo disparo = jugador.intentarDisparar();

        assert (disparo.estaEnDireccion(derecha));

    }

    @Test
    public void disparoTieneLaDireccionCorrecta(){
        Jugador jugador = new Jugador("Jugador", xInicial, yInicial, velocidadBase);

        jugador.mover(arriba);
        Disparo disparo = jugador.intentarDisparar();
        jugador.mover(derecha);

        assert(disparo.estaEnDireccion(arriba));
    }
}