
package org.modelo;

public class Enemigo implements Colisionable {
    private Tanque tanque;
    private long ultimoMovimiento;
    private long ultimoDisparo;
    private  long INTERVALO_MOVIMIENTO = 1000;
    private  long INTERVALO_DISPARO = 2000;
    private Direccion direccionActual;
    //private double velocidad;
    private int pasosMaximos;



    private Disparo disparoActivo; // null si no hay disparo en curso
    private long inicioTiempoConducta;   // inicio del comportamiento actual
    private long duracionConducta;       // duración aleatoria 1-5 s
    private Vector2D ultimaPosicion;     // última posición para detectar si está bloqueado
    private long ultimoPosicionCambio;   // tiempo en que la posición cambió



    @Override
    public double obtenerCoordenadaX() {
        return tanque.obtenerPosicion().obtenerCoordenadaX();
    }

    @Override
    public double obtenerCoordenadaY() {
        return tanque.obtenerPosicion().obtenerCoordenadaY();
    }

    public boolean recibirImpacto(Disparo disparo) {
        if (disparo.esDeJugador()) {
            return recibirDanio();
        }

        return false;
    }

    public Enemigo(double coordenadaX, double coordenadaY, double velocidadBase) {
        tanque = new Tanque(coordenadaX, coordenadaY, velocidadBase);

        direccionActual = Direccion.ABAJO;
        inicioTiempoConducta= System.currentTimeMillis();
        duracionConducta= 1000 + (long)(Math.random() * 4000); // 1-5s
        ultimaPosicion= tanque.obtenerPosicion();
        ultimoPosicionCambio= System.currentTimeMillis();
        ultimoMovimiento= System.currentTimeMillis();
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

    private boolean noSeMovioRecientemente() {
        Vector2D posicionActual = tanque.obtenerPosicion();
        return posicionActual.esIgualA(ultimaPosicion) || (System.currentTimeMillis() - ultimoPosicionCambio > 2000);
    }

    public boolean mover() {
        long tiempoActual = System.currentTimeMillis();
        if ((tiempoActual - ultimoMovimiento >= INTERVALO_MOVIMIENTO) && noSeMovioRecientemente()) {
            Vector2D ultimaPosicion = tanque.obtenerPosicion();
            direccionActual = elegirDireccionAleatoria();
            actualizarPosicion(direccionActual);
            ultimoMovimiento = tiempoActual;
            ultimoPosicionCambio = System.currentTimeMillis();
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

    //lo siguiente no hace falta:
    public boolean estaVivo() {
        return tanque.estaVivo();
    }

    public Disparo disparar() {
        if (disparoActivo == null) {
            // Crear un nuevo disparo
            disparoActivo = new Disparo(obtenerPosicion(), direccionActual, tanque.obtenerVelocidadBase());
            return disparoActivo;
        }
        return null; // Ya hay un disparo activo
    }


}