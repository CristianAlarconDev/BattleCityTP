package org.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaColisionableTest {

    @Test
    public void unAreaColisionaConOtraArea(){
        AreaColisionable area1 = new AreaColisionable(new Vector2D(0,0),5);
        AreaColisionable area2 = new AreaColisionable(new Vector2D(1,1),5);
        assertTrue(area1.estaEnArea(area2));
    }

    @Test
    public void unAreaNoColisionaConOtraArea(){
        AreaColisionable area1 = new AreaColisionable(new Vector2D(0,0),5);
        AreaColisionable area2 = new AreaColisionable(new Vector2D(10,0),5);
        assertFalse(area1.estaEnArea(area2));
    }

}