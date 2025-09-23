package org.example;

public class Disparo {
    private Vector2D posicion;
    private Vector2D velocidad;
    private boolean activo;
    private Direccion direccion;

    public Disparo(Vector2D posicion, Direccion direccion, double velocidadBase) {
        this.posicion = posicion;
        this.velocidad = direccion.comoVector();
        this.velocidad.multiplicarPor(velocidadBase);
        this.direccion = direccion;
        this.activo = true;
    }

    public void mover() {
        if (!activo) return;
        posicion.desplazar(velocidad);
    }
    /*no se usa pero puede servir para visualizar en capas superiores*/
    public Vector2D obtenerPosicion() {
        return new Vector2D(posicion.obtenerCoordenadaX(),
                posicion.obtenerCoordenadaY());
    }
    /*fin comentario*/

    public boolean estaEnPosicion(Vector2D vector2D){
        return this.posicion.esIgualA(vector2D);
    }
    public boolean estaEnDireccion(Direccion direccion){
        return this.direccion == direccion;
    }

    public boolean estaActivo() {
        return activo;
    }
    /*no se usa pero puede servir para desactivar al disparo en capas superiores*/
    public boolean desactivar() {
        this.activo = false;
        return estaActivo();
    }
    /*fin comentario*/
}
