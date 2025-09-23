package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisparoTest {

    @Test
    public void disparoSeMueveEnConVelocidadConstante() {

        Vector2D origen = new Vector2D(0, 0);
        Disparo disparo = new Disparo(origen, Direccion.DERECHA, 2);

        assert(disparo.estaEnPosicion(new Vector2D(0, 0)));

        disparo.mover();
        assert(disparo.estaEnPosicion(new Vector2D(2, 0)));

        disparo.mover();
        assert(disparo.estaEnPosicion(new Vector2D(4, 0)));

    }



}