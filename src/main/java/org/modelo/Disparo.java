package org.modelo;

public class Disparo {
    private Vector2D posicion;
    private Vector2D velocidad;
    private boolean activo;
    private Direccion direccion;
    private OrigenDisparo origenDisparo;
    private boolean poderoso;


    public Disparo(Vector2D posicion, Direccion direccion, double velocidadBase) {
        this.posicion = posicion.copiar();
        this.direccion = direccion;
        this.activo = true;
        this.velocidad = direccion.comoVector().escalado(velocidadBase);
        this.poderoso=false;

    }

    public Disparo(Vector2D posicion, Direccion direccion, double velocidadBase, OrigenDisparo origenDisparo) {
        this.posicion = posicion.copiar();
        this.direccion = direccion;
        this.activo = true;
        this.velocidad = direccion.comoVector().escalado(velocidadBase);
        this.origenDisparo = origenDisparo;
    }
    public void hacerPoderoso(){
        this.poderoso=true;
    }
    public boolean esPoderoso(){
        return poderoso;
    }
    public boolean esDeJugador(){
        return origenDisparo == OrigenDisparo.JUGADOR;
    }


    public void mover(double tiempo){
        if (!activo) return;
        Vector2D delta = velocidad.escalado(tiempo);
        this.posicion = posicion.sumadoA(delta);
    }
    public void mover() {
        if (!activo) return;
        mover(1);
    }
    public void desactivar(){
        this.activo=false;
    }

    public boolean estaEnPosicion(Vector2D vector2D) {
        return this.posicion.esIgualA(vector2D);
    }

    public boolean estaEnDireccion(Direccion direccion) {
        return this.direccion == direccion;
    }

    public boolean estaActivo() {
        return activo;
    }

    public double obtenerCoordenadaX(){
        return posicion.obtenerCoordenadaX();
    }
    public double obtenerCoordenadaY(){
        return posicion.obtenerCoordenadaY();
    }
}
