package org.example;

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
        Vector2D vector2D = new Vector2D(10,10);
        vector2D.desplazar(new Vector2D(10,10));
        assertEquals(20,vector2D.obtenerCoordenadaX());
        assertEquals(20,vector2D.obtenerCoordenadaY());
    }
    @Test
    public void unVector2DSeMultiplicaPorUnFactor(){
        Vector2D vector2D = new Vector2D(10,10);
        vector2D.multiplicarPor(2);
        assertEquals(20,vector2D.obtenerCoordenadaX());
        assertEquals(20,vector2D.obtenerCoordenadaY());
    }
    @Test
    public void unVector2DSeInverteCoordenadasX(){
        Vector2D vector2D = new Vector2D(10,10);
        vector2D.invertirCoordenadaX();
        assertEquals(-10,vector2D.obtenerCoordenadaX());
        assertEquals(10,vector2D.obtenerCoordenadaY());
    }

}