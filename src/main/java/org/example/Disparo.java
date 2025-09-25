package org.example;

public class Disparo {
    private Vector2D posicion;
    private Vector2D velocidad;
    private boolean activo;
    private Direccion direccion;

    public Disparo(Vector2D posicion, Direccion direccion, double velocidadBase) {
        this.posicion = posicion.copiar();
        this.direccion = direccion;
        this.activo = true;
        this.velocidad = direccion.comoVector().escalado(velocidadBase);

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

    /*no se usa pero puede servir para visualizar en capas superiores*/
    public Vector2D obtenerPosicion() {
        return new Vector2D(posicion.obtenerCoordenadaX(),
                posicion.obtenerCoordenadaY());
    }
    /*fin comentario*/

    public boolean estaEnPosicion(Vector2D vector2D) {
        return this.posicion.esIgualA(vector2D);
    }

    public boolean estaEnDireccion(Direccion direccion) {
        return this.direccion == direccion;
    }

    public boolean estaActivo() {
        return activo;
    }
}
