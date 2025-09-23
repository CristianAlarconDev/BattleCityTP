package org.example;

public class Disparo {
    private Vector2D posicion;
    private Vector2D velocidad;
    private boolean activo;

    public Disparo(Vector2D posicion, Direccion direccion, double velocidadBase) {
        this.posicion = posicion;
        this.velocidad = direccion.comoVector();
        this.velocidad.multiplicarPor(velocidadBase);
        this.activo = true;
    }

    public void mover() {
        if (!activo) return;
        posicion.desplazar(velocidad);
    }

    public Vector2D obtenerPosicion() {
        return new Vector2D(posicion.obtenerCoordenadaX(),
                posicion.obtenerCoordenadaY());
    }

    public boolean estaActivo() {
        return activo;
    }

    public boolean desactivar() {
        this.activo = false;
        return estaActivo();
    }

    public void verificarFueraDeLimite(double ancho, double alto) {
        if (posicion.obtenerCoordenadaX() < 0 || posicion.obtenerCoordenadaX() > ancho ||
                posicion.obtenerCoordenadaY() < 0 || posicion.obtenerCoordenadaY() > alto) {
            this.activo = false;
        }
    }
}
