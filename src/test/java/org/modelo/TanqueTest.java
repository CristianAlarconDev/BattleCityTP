package org.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TanqueTest {
    private double xInicial;
    private double yInicial;
    private double velocidadBase;
    private double velocidadBoost;
    private int vidas;

    @BeforeEach
    public void setUp(){
        xInicial = 0;
        yInicial = 0;
        velocidadBase = 2;
        velocidadBoost = 5;
        vidas = 3;
    }
    @Test
    public void tanqueTieneEstadoInicial(){
       Tanque tanque = new Tanque(xInicial, yInicial, velocidadBase, vidas);
       assert(tanque.estaEnPosicion(0, 0));
       assert(tanque.estaVivo());
   }
   @Test
   public void moverTanqueALaDerechaConVelocidadBase2() {
       Tanque tanque = new Tanque(xInicial, yInicial, velocidadBase, vidas);
       tanque.mover(Direccion.DERECHA);
       assert(tanque.estaEnPosicion(2,0));
   }
    @Test
    public void testMoverDerechaConVelocidadBase5() {

        Tanque tanque = new Tanque(xInicial, yInicial, velocidadBoost, vidas);
        tanque.mover(Direccion.DERECHA);
        assert(tanque.estaEnPosicion(5, 0));
    }
    @Test
    void testMovimientoCompuesto() {
        Tanque tanque = new Tanque(xInicial, yInicial, velocidadBase, vidas);
        double velocidad = 3;
        tanque.mover(Direccion.ARRIBA);
        tanque.cambiarVelocidadBase(velocidad);
        tanque.mover(Direccion.DERECHA);

        assert(tanque.estaEnPosicion(3, -2));
    }

}