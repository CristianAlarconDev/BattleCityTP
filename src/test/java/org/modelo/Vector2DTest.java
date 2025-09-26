package org.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {
    @Test
    public void unVector2DSeCreaConCoordenadas(){
        Vector2D vector2D = new Vector2D(10,10);
        assertEquals(10,vector2D.obtenerCoordenadaX());
        assertEquals(10,vector2D.obtenerCoordenadaY());
    }
    @Test
    public void unVector2DSeAgregaUnaCoordenada(){
        Vector2D vector = new Vector2D(10,10);
       Vector2D vectorSuma=vector.sumadoA(new Vector2D(10,10));
        assertEquals(20,vectorSuma.obtenerCoordenadaX());
        assertEquals(20,vectorSuma.obtenerCoordenadaY());
    }
    @Test
    public void unVector2DSeMultiplicaPorUnFactor(){
        Vector2D vector2D = new Vector2D(10,10);
        Vector2D vectorEscalado=vector2D.escalado(2);
        assertEquals(20,vectorEscalado.obtenerCoordenadaX());
        assertEquals(20,vectorEscalado.obtenerCoordenadaY());
    }
    @Test
    public void unVector2DSeInverteCoordenadasX(){
        Vector2D vector2D = new Vector2D(10,10);
        vector2D.invertirCoordenadaX();
        assertEquals(-10,vector2D.obtenerCoordenadaX());
        assertEquals(10,vector2D.obtenerCoordenadaY());
    }

}