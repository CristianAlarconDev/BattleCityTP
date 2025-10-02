
package org.modelo;
import java.util.List;

public abstract class Enemigo implements Colisionable {
    private Tanque tanque;
    private long ultimoDisparo;
    private  long INTERVALO_MOVIMIENTO = 1000;
    private  long INTERVALO_DISPARO = 2000;
    private Direccion direccionActual;
    //private double velocidad;
    private int pasosMaximos;
    private int vidas;
    private final int tamanioEnemigo;
    private Disparo disparoActivo; // null si no hay disparo en curso
    private long inicioTiempoConducta;   // inicio del comportamiento actual
    private long duracionConducta;       // duración aleatoria 1-5 s
    private Vector2D ultimaPosicion;     // última posición para detectar si está bloqueado
    private long ultimoPosicionCambio;   // tiempo en que la posición cambió
    private double anchoDeCelda;
    private Vector2D siguientePosicion;
    private boolean enMovimiento = false;


    public Direccion obtenerDireccionActual() {
        return direccionActual;
    }
    @Override
    public double obtenerCoordenadaX() {
        return tanque.obtenerPosicion().obtenerCoordenadaX();
    }

    @Override
    public double obtenerCoordenadaY() {
        return tanque.obtenerPosicion().obtenerCoordenadaY();
    }

    public ResultadoImpacto recibirImpacto(Disparo disparo) {
        if (disparo.esPoderoso()&&disparo.esDeJugador()){
            return ResultadoImpacto.ENEMIGO_ELIMINADO;
        }
        vidas--;
        if (vidas <= 0) {
            if (disparo.esDeJugador()) {
                return ResultadoImpacto.ENEMIGO_ELIMINADO;
            }
            return ResultadoImpacto.DESTRUIDO;
        }
        return ResultadoImpacto.NADA;
    }

    public Enemigo(double coordenadaX, double coordenadaY, double velocidadBase, int vidas) {
        tanque = new Tanque(coordenadaX, coordenadaY, velocidadBase);
        this.vidas=vidas;
        direccionActual = Direccion.ABAJO;
        inicioTiempoConducta= System.currentTimeMillis();
        duracionConducta= 1000 + (long)(Math.random() * 4000); // 1-5s
        ultimaPosicion = new Vector2D(tanque.obtenerPosicion().obtenerCoordenadaX(), tanque.obtenerPosicion().obtenerCoordenadaY());
        ultimoPosicionCambio= System.currentTimeMillis();
        tamanioEnemigo=20;

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




    public boolean enemigoEstaEnMovimiento() {
        return enMovimiento;
    }





















    


    public boolean mover(List<Colisionable> colisionables, double anchoNivel, double altoNivel, double radio) {
        long tiempoActual = System.currentTimeMillis();
        actualizarConducta(tiempoActual, colisionables, anchoNivel, altoNivel, radio);
        boolean seMovio = avanzar();
        if(seMovio){
            enMovimiento = true;
        } else {
            enMovimiento = false;
        }
        actualizarBloqueo(tiempoActual);
        return seMovio;
    }

    private void actualizarConducta(long tiempoActual, List<Colisionable> colisionables,
                                    double anchoNivel, double altoNivel, double radio) {
        //si ya llego a la celda destino, calcula la posicion de la siguiente celda a la que se va a mover y actualiza direccion y tiempo de conducta si es necesario

        if (siguientePosicion == null) {

            if (tiempoActual - inicioTiempoConducta >= duracionConducta || bloqueadoMasDe2Segundos(tiempoActual)) {
                direccionActual = elegirDireccionAleatoria();
                inicioTiempoConducta = tiempoActual;
                duracionConducta = 1000 + (long)(Math.random() * 4000);
                siguientePosicion = null; // recalcula nueva posición
            }

            siguientePosicion = calcularSiguientePosicion();

            // Verifica si la siguiente posicion esta libre y si se esta saliendo de los limites del nivel
            int intentos = 0;
            while ((!PosicionLibre(siguientePosicion, colisionables) || !dentroDeLimites(siguientePosicion, anchoNivel, altoNivel, radio))
                    && intentos < 4) {
                direccionActual = elegirDireccionAleatoria();
                siguientePosicion = calcularSiguientePosicion();
                intentos++;
            }
        }
    }

    private boolean avanzar() {
        if (siguientePosicion == null) return false;

        Vector2D posActual = tanque.obtenerPosicion();
        double velocidad = tanque.obtenerVelocidadBase();

        double coordenadaXActual = posActual.obtenerCoordenadaX();
        double coordenadaYActual= posActual.obtenerCoordenadaY();


        if (coordenadaXActual < siguientePosicion.obtenerCoordenadaX()) {
            coordenadaXActual = Math.min(coordenadaXActual + velocidad, siguientePosicion.obtenerCoordenadaX());
        } else if (coordenadaXActual > siguientePosicion.obtenerCoordenadaX()) {
            coordenadaXActual = Math.max(coordenadaXActual - velocidad, siguientePosicion.obtenerCoordenadaX());
        }

        if (coordenadaYActual < siguientePosicion.obtenerCoordenadaY()) {
            coordenadaYActual = Math.min(coordenadaYActual + velocidad, siguientePosicion.obtenerCoordenadaY());
        } else if (coordenadaYActual > siguientePosicion.obtenerCoordenadaY()) {
            coordenadaYActual = Math.max(coordenadaYActual - velocidad, siguientePosicion.obtenerCoordenadaY());
        }

        tanque.setPosicion(new Vector2D(coordenadaXActual, coordenadaYActual));

        Vector2D actual = new Vector2D(coordenadaXActual, coordenadaYActual);
        if (siguientePosicion.esCasiIgualA(actual,0.1)) {
            siguientePosicion = null;
        }

        return true;
    }

    private void actualizarBloqueo(long tiempoActual) {
        Vector2D posActual = tanque.obtenerPosicion();
        if (!posActual.esIgualA(ultimaPosicion)) {
            ultimaPosicion = posActual;
            ultimoPosicionCambio = tiempoActual;
        }
    }

    private boolean bloqueadoMasDe2Segundos(long tiempoActual) {
        return (tiempoActual - ultimoPosicionCambio > 2000);
    }

    private Vector2D calcularSiguientePosicion() {
        Vector2D pos = tanque.obtenerPosicion();
        double paso = tanque.velocidadBase;
        switch (direccionActual) {
            case ARRIBA:     return new Vector2D(pos.obtenerCoordenadaX(), pos.obtenerCoordenadaY() - paso);
            case ABAJO:      return new Vector2D(pos.obtenerCoordenadaX(), pos.obtenerCoordenadaY() + paso);
            case IZQUIERDA:  return new Vector2D(pos.obtenerCoordenadaX() - paso, pos.obtenerCoordenadaY());
            case DERECHA:    return new Vector2D(pos.obtenerCoordenadaX() + paso, pos.obtenerCoordenadaY());
            default:         return null;
        }
    }


    private boolean dentroDeLimites(Vector2D pos, double ancho, double alto, double radio) {
        return pos.obtenerCoordenadaX() - radio >= 0 &&
                pos.obtenerCoordenadaX() + radio <= ancho &&
                pos.obtenerCoordenadaY() - radio >= 0 &&
                pos.obtenerCoordenadaY() + radio <= alto;
    }

    private boolean PosicionLibre(Vector2D pos, List<Colisionable> colisionables) {
        double radioTanque = tamanioEnemigo / 2.0;
        for (Colisionable c : colisionables) {
            if (!c.impideElPaso()) continue;
            double radioBloque = 10;
            Vector2D posBloque = c.obtenerPosicion();
            double dx = pos.obtenerCoordenadaX() - posBloque.obtenerCoordenadaX();
            double dy = pos.obtenerCoordenadaY() - posBloque.obtenerCoordenadaY();
            double distancia = Math.sqrt(dx*dx + dy*dy);
            if (distancia < radioTanque + radioBloque) return false;
        }
        return true;
    }

    @Override
    public boolean impideElPaso() {
        return true;
    }

    public abstract TipoEnemigo obtenerTipo();



}