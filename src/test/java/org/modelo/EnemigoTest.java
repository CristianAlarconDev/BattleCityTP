package org.modelo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/*
class EnemigoTest {

    @Test
    public void enemigoNoSeMueveAntesDeIntervalo() throws InterruptedException{
        Enemigo enemigo = new Enemigo(0, 0,
                2, 5000);

        Thread.sleep(1000);


        assertFalse(enemigo.mover());

    }
    @Test
    public void enemigoSeMueveDespuesDeIntervalo() throws InterruptedException{
        Enemigo enemigo = new Enemigo(0, 0,
                2, 2000);
        Thread.sleep(3000);

        assertTrue(enemigo.mover());
    }

    @Test
    public void enemigoLuegoDeMoverseTieneOtraPosicion() throws InterruptedException{
        Enemigo enemigo = new Enemigo(0, 0,
                2, 2000);
        Thread.sleep(3000);
        enemigo.mover();
        Vector2D ultimaPosicion = enemigo.obtenerPosicion();

        assertFalse(ultimaPosicion.esIgualA(new Vector2D(0,0)));
    }
    @Test
    public void enemigoAlNoMoverseNoCambiaPosicion() throws InterruptedException{
        Enemigo enemigo = new Enemigo(0, 0,
                2, 5000);
        Thread.sleep(1000);
        enemigo.mover();
        Vector2D ultimaPosicion = enemigo.obtenerPosicion();
        assertTrue(ultimaPosicion.esIgualA(new Vector2D(0,0)));
    }
    @Test
    public void enemigoLuegoDeMoverseDebeEsperarParaMoverseNuevamente() throws InterruptedException{
        Enemigo enemigo = new Enemigo(0, 0,
                2, 3000);
        Thread.sleep(3500);
        boolean movimientoExitoso=enemigo.mover();
        assertTrue(movimientoExitoso);
        boolean movimientoFallido=enemigo.mover();
        assertFalse(movimientoFallido);
    }
}*/