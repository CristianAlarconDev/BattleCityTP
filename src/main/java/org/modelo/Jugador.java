package org.modelo;

public class Jugador extends Tanque implements Colisionable {
    private String nombre;
    private ArmaUnDisparo arma;
    private final int tamanio;
    private boolean congelado;
    private long tiemporDescongelacion;
    private final int  tiempoCongelado;

    public Jugador(String nombre, double x, double y, double velocidadMovBase) {
        super(x, y, velocidadMovBase);
        this.nombre = nombre;
        //cambiar luego en constructor de hacer falta
        //this.velocidadDeDisparo = velocidadMovBase;
        arma = new ArmaUnDisparo(velocidadMovBase);
        this.tamanio = 20;
        this.congelado = false;
        this.tiemporDescongelacion = 0;
        tiempoCongelado=2000;
    }
    public void mover(Direccion direccion){
        if(estaCongelado()){
            return;
        }
        super.mover(direccion);
    }

    public ResultadoImpacto recibirImpacto(Disparo disparo) {

        if (disparo.esDeJugador()) {
            this.congelar(this.tiempoCongelado);
            return ResultadoImpacto.CONGELADO;
        }
        this.vidasTotales--;
        if (vidasTotales <= 0) {
            return ResultadoImpacto.JUGADOR_ELIMINADO;
        }
        return ResultadoImpacto.NADA;
    }
    public boolean estaCongelado(){
        if (congelado&&System.currentTimeMillis()>tiemporDescongelacion){
            congelado=false;
        }
        return congelado;
    }
    private void congelar(int tiempoMilisegundos){
        this.congelado=true;
        this.tiemporDescongelacion=System.currentTimeMillis()+tiempoMilisegundos;
    }
    public void cambiarVelocidadDeDisparo(double velocidadDeDisparo) {
        arma.cambiarVelocidadDisparo(velocidadDeDisparo);
    }

    public double obtenerVelocidadDeDisparo() {
        return arma.obtenerVelocidadDisparo();
    }

    public Disparo intentarDisparar() {
        Vector2D posicionCentro = this.posicion;
        Vector2D direccionActual = obtenerDireccionActual().comoVector();
        Vector2D posicionDisparo = posicionCentro.sumadoA(
                direccionActual.escalado((tamanio / 2.0) + 3.0));
        return arma.disparar(posicionDisparo, obtenerDireccionActual(), OrigenDisparo.JUGADOR);
    }

    public String obtenerNombre() {
        return nombre;
    }

}
