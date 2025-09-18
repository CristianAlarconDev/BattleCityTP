package org.example;

import org.junit.jupiter.api.Test;

class PosicionTest {

    @Test
    public void sePuedeCambiarPosicion(){

        Posicion posicion = new Posicion(10,10);
        posicion.cambiarA(11,11);
        assert (posicion.esIgualA(11,11));
    }

    @Test
    public void sePuedeComprobarQueNoEsIgualAPosicion(){
        Posicion posicion = new Posicion(10,10);
        assert (!posicion.esIgualA(11,11));
    }
}