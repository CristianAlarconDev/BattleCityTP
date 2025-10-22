
package org.modelo;
import java.util.List;

public abstract class Enemigo extends Tanque{
    private long inicioTiempoConducta;
    private long duracionConducta;
    private Vector2D ultimaPosicion;
    private long ultimoPosicionCambio;
    private Vector2D siguientePosicion;
    private boolean impideElPaso;

    public Enemigo(double coordenadaX, double coordenadaY, double velocidadBase, int vidas) {
        super(coordenadaX, coordenadaY, velocidadBase, vidas, ConstantesJuego.RADIO_AREA_COLISION_ENEMIGO);
        inicioTiempoConducta= System.currentTimeMillis();
        duracionConducta= 1000 + (long)(Math.random() * 4000); // 1-5s
        ultimaPosicion = new Vector2D(this.obtenerCoordenadaX(), this.obtenerCoordenadaY());
        ultimoPosicionCambio= System.currentTimeMillis();
        impideElPaso=true;
    }

    public ResultadoImpacto recibirImpacto(Disparo disparo) {
        if (disparo.esPoderoso()&&disparo.esDeJugador()){
            return ResultadoImpacto.ENEMIGO_ELIMINADO;
        }
        if (disparo.esDeJugador()){
            this.perderVida();
            if (!estaVivo()) {
                return ResultadoImpacto.ENEMIGO_ELIMINADO;
            }
        }
        return ResultadoImpacto.NADA;
    }

    private Direccion elegirDireccionAleatoria() {
        Direccion[] direcciones = Direccion.values();
        return direcciones[(int)(Math.random() * direcciones.length)];
    }

    public Disparo intentarDisparar() {
            return prepararDisparo(OrigenDisparo.ENEMIGO);
    }

    public boolean mover(List<Obstruible> obstrucciones, double anchoNivel, double altoNivel, double radio) {
        long tiempoActual = System.currentTimeMillis();
        actualizarConducta(tiempoActual, obstrucciones, anchoNivel, altoNivel, radio);
        boolean seMovio = avanzar();
        setEnMovimiento(seMovio);
        actualizarBloqueo(tiempoActual);
        return seMovio;
    }

    private void actualizarConducta(long tiempoActual, List<Obstruible> obstrucciones,
                                    double anchoNivel, double altoNivel, double radio) {
        //si ya llego a la celda destino, calcula la posicion de la siguiente celda a la que se va a mover y actualiza direccion y tiempo de conducta si es necesario

        if (siguientePosicion == null) {

            if (tiempoActual - inicioTiempoConducta >= duracionConducta || bloqueadoMasDe2Segundos(tiempoActual)) {
                this.cambiarDireccion(elegirDireccionAleatoria());
                inicioTiempoConducta = tiempoActual;
                duracionConducta = 1000 + (long)(Math.random() * 4000);
                siguientePosicion = null; // recalcula nueva posición
            }

            siguientePosicion = calcularSiguientePosicion();

            // Verifica si la siguiente posicion esta libre y si se esta saliendo de los limites del nivel
            int intentos = 0;
            while ((!PosicionLibre(siguientePosicion, obstrucciones) || !dentroDeLimites(siguientePosicion, anchoNivel, altoNivel, radio))
                    && intentos < 4) {
                this.cambiarDireccion(elegirDireccionAleatoria());
                siguientePosicion = calcularSiguientePosicion();
                intentos++;
            }
        }
    }

    private boolean avanzar() {
        if (siguientePosicion == null) return false;

        Vector2D posActual = this.obtenerPosicion();
        double velocidad = this.obtenerVelocidadBase();

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

        this.setPosicion(new Vector2D(coordenadaXActual, coordenadaYActual));
        /*cambios en area colisionable*/
        this.obtenerAreaColisionable().cambiarCentro(this.obtenerPosicion());
        Vector2D actual = new Vector2D(coordenadaXActual, coordenadaYActual);
        if (siguientePosicion.esCasiIgualA(actual,0.1)) {
            siguientePosicion = null;
        }

        return true;
    }

    private void actualizarBloqueo(long tiempoActual) {
        Vector2D posActual = this.obtenerPosicion();
        if (!posActual.esIgualA(ultimaPosicion)) {
            ultimaPosicion = posActual;
            ultimoPosicionCambio = tiempoActual;
        }
    }

    private boolean bloqueadoMasDe2Segundos(long tiempoActual) {
        return (tiempoActual - ultimoPosicionCambio > 2000);
    }

    private Vector2D calcularSiguientePosicion() {
        Vector2D pos = this.obtenerPosicion();
        double paso = this.obtenerVelocidadBase();
        switch (this.obtenerDireccionActual()) {
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

    private boolean PosicionLibre(Vector2D pos, List<Obstruible> obstrucciones) {
        double radioTanque = this.obtenerTamanio() / 2.0;
        AreaColisionable areaPrueba = new AreaColisionable(pos, radioTanque);
       for (Obstruible obstruccion : obstrucciones) {
           if (areaPrueba.estaEnArea(obstruccion.obtenerAreaColisionable())) {
               return false;
           }
       }
        /*for (Colisionable c : colisionables) {
            if (!c.impideElPaso()) continue;
            double radioBloque = 10;
            Vector2D posBloque = c.obtenerPosicion();
            double dx = pos.obtenerCoordenadaX() - posBloque.obtenerCoordenadaX();
            double dy = pos.obtenerCoordenadaY() - posBloque.obtenerCoordenadaY();
            double distancia = Math.sqrt(dx*dx + dy*dy);
            if (distancia < radioTanque + radioBloque) return false;
        }*/
        return true;
    }

    public abstract TipoEnemigo obtenerTipo();

}