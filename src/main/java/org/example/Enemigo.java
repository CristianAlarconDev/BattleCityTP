package org.example;
public class Enemigo {
    private Tanque tanque;
    private long ultimoMovimiento;
    private long ultimoDisparo;
    private final long INTERVALO_MOVIMIENTO = 1000; // 1 segundo
    private final long INTERVALO_DISPARO = 2000;    // 2 segundos
    private Direccion direccionActual;
    private double velocidad;

    public Enemigo(double x, double y, double velocidadBase) {
        tanque = new Tanque(x, y, velocidadBase);
        ultimoMovimiento = System.currentTimeMillis();
        ultimoDisparo = System.currentTimeMillis();
        direccionActual = Direccion.ABAJO;
        velocidad=tanque.obtenerVelocidadBase();

    }

    public void mover() {
        long tiempoActual = System.currentTimeMillis();

        if (tiempoActual - ultimoMovimiento >= INTERVALO_MOVIMIENTO) {
            direccionActual = elegirDireccionAleatoria();
            tanque.mover(direccionActual);
            ultimoMovimiento = tiempoActual;
        }
    }

    public Disparo disparar() {
        long tiempoActual = System.currentTimeMillis();

        if (tiempoActual - ultimoDisparo >= INTERVALO_DISPARO) {
            ultimoDisparo = tiempoActual;
            return new Disparo(obtenerPosicion(),
                    direccionActual, tanque.obtenerVelocidadBase());
        }
        return null;
    }


    public Vector2D obtenerPosicion() {
        return tanque.obtenerPosicion();
    }

    public boolean estaVivo() {
        return tanque.estaVivo();
    }

    public boolean recibirDanio() {
        return tanque.recibirDanio();
    }

    private Direccion elegirDireccionAleatoria() {
        Direccion[] direcciones = Direccion.values();
        return direcciones[(int)(Math.random() * direcciones.length)];
    }
}