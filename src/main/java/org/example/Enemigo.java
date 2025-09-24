
package org.example;

public class Enemigo {
    private Tanque tanque;
    private long ultimoMovimiento;
    private long ultimoDisparo;
    private  long INTERVALO_MOVIMIENTO = 1000;
    private  long INTERVALO_DISPARO = 2000;
    private Direccion direccionActual;
    //private double velocidad;
    private int pasosMaximos;

    public Enemigo(double coordenadaX, double coordenadaY, double velocidadBase) {
        tanque = new Tanque(coordenadaX, coordenadaY, velocidadBase);
        ultimoMovimiento = System.currentTimeMillis();
        ultimoDisparo = System.currentTimeMillis();
        //velocidad=tanque.obtenerVelocidadBase();
        direccionActual = Direccion.ABAJO;

    }
    public Enemigo(double coordenadaX, double coordenadaY,
                   double velocidadBase, long intervaloMovimiento) {
        this(coordenadaX, coordenadaY, velocidadBase);
        INTERVALO_MOVIMIENTO = intervaloMovimiento;


    }
    public Enemigo(double coordenadaX, double coordenadaY, double velocidadBase,
                   long intervaloMovimiento, int pasosMaximos) {
        this(coordenadaX, coordenadaY, velocidadBase, intervaloMovimiento);
        this.pasosMaximos = pasosMaximos;
    }
    public boolean mover() {
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - ultimoMovimiento >= INTERVALO_MOVIMIENTO) {
            direccionActual = elegirDireccionAleatoria();
            actualizarPosicion(direccionActual);
            ultimoMovimiento = tiempoActual;
            return true;
        }
        return false;
    }
    private void actualizarPosicion(Direccion direccion) {
        int pasos = 1 + (int)(Math.random() * pasosMaximos);
        for (int i = 0; i < pasos; i++) {
            tanque.mover(direccion);
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

    public boolean recibirDanio() {
        return tanque.recibirDanio();
    }

    private Direccion elegirDireccionAleatoria() {
        Direccion[] direcciones = Direccion.values();
        return direcciones[(int)(Math.random() * direcciones.length)];
    }
}