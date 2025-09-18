package org.example;

import org.junit.jupiter.api.Test;

class TanqueTest {



   @Test
   public void sePuedeCambiarDireccion(){
       Tanque tanque = new Tanque(new Posicion(10,10));
       Direccion arriba = Direccion.ARRIBA;
       boolean cambioExitoso = tanque.cambiarDireccion(arriba);
       assert (cambioExitoso);

   }
   @Test
   public void sePuedeMoverTanqueSegunDireccion(){
        Tanque tanque = new Tanque(new Posicion(10,10));

        tanque.cambiarDireccion(Direccion.ARRIBA);

        boolean movimientoExitoso=tanque.moverseSegunDireccion(1);

        assert (movimientoExitoso);
   }
   @Test
   public void luegoDeMoverseTieneOtraPosicion(){
       Tanque tanque = new Tanque(new Posicion(10,10));
       tanque.cambiarDireccion(Direccion.ARRIBA);
       tanque.moverseSegunDireccion(1);

       assert (tanque.estaEnPosicion(10,11));
   }
   @Test
   public void unTanqueTieneVidasTotales(){
       Tanque tanque = new Tanque(new Posicion(10,10));
       assert (tanque.estaVivo());
   }
   @Test
   public void unTanquePuedeRecibirDanio(){
       Tanque tanque = new Tanque(new Posicion(10,10));
       boolean recibioDanio=tanque.recibirDanio();
       assert (recibioDanio);

   }

   @Test
    public void unTanqueYaNoTieneVidas(){
       Tanque tanque = new Tanque(new Posicion(10,10));
       tanque.recibirDanio();
       tanque.recibirDanio();
       tanque.recibirDanio();
       assert (!tanque.estaVivo());
   }
}