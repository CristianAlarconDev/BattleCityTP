package org.modelo;

public class ArmaUnDisparo {
    private Disparo disparoEnCurso;
    private double velocidadDisparo;

    public ArmaUnDisparo(double velocidadDisparo){
        disparoEnCurso = null;
        this.velocidadDisparo=velocidadDisparo;
    }
    private void reiniciarDisparo(){
        if (disparoEnCurso != null && !disparoEnCurso.estaActivo()) {
            disparoEnCurso = null;
        }
    }
    public boolean puedeDisparar(){
        reiniciarDisparo();
        return disparoEnCurso == null;
    }
    public Disparo disparar(Vector2D origen, Direccion direccion){
        if(!puedeDisparar()){
            throw new IllegalStateException("No se puede disparar aun");
        }
        Disparo disparo = new Disparo(origen, direccion, velocidadDisparo);
        disparoEnCurso = disparo;
        return disparo;
    }
    public void cambiarVelocidadDisparo(double velocidadDisparo){
        this.velocidadDisparo=velocidadDisparo;
    }
    public void disparoFinalizado(){
        disparoEnCurso = null;
    }
    public double obtenerVelocidadDisparo(){
        return velocidadDisparo;
    }
}
