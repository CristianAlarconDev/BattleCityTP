package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JugadorTest {

    @Test
    public void jugadorTieneNombre(){
        Jugador jugador= new Jugador(new Posicion(10,10),"Carlos");
        assertEquals("Carlos",jugador.obtenerNombre());
    }

    @Test
    public void jugadorPuedeAtacar(){
        Jugador jugador= new Jugador(new Posicion(10,10),"Carlos");
        Disparo disparo= jugador.atacar();
        assertNotNull(disparo);
    }

}