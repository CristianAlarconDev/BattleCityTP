package org.modelo;

public class Jugador extends Tanque implements Colisionable {
    private String nombre;
    private ArmaUnDisparo arma;
    private final int tamanio;
    private boolean congelado;
    private long tiemporDescongelacion;
    private final int  tiempoCongelado;
    private boolean invulnerable;
    private long tiempoInvulnerable;
    private boolean disparoMejorado;
    private boolean enMovimiento;

    public Jugador(String nombre, double x, double y, double velocidadMovBase) {
        super(x, y, velocidadMovBase);
        this.nombre = nombre;
        arma = new ArmaUnDisparo(velocidadMovBase);
        this.tamanio = 20;
        this.congelado = false;
        this.tiemporDescongelacion = 0;
        tiempoCongelado=2000;
        invulnerable=false;
        tiempoInvulnerable=0;
        enMovimiento=false;


    }
    public void mover(Direccion direccion){
        if(estaCongelado()){
            enMovimiento = false;
            return;
        }
        if (direccion != null) {
            super.mover(direccion);
            enMovimiento = true;
        } else {
            enMovimiento = false;
        }
    }


    public ResultadoImpacto recibirImpacto(Disparo disparo) {
        if (estaInvulnerable()) {
            return ResultadoImpacto.NADA;
        }
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
    public void activarCasco(int duracionMilisegundos){
        this.invulnerable=true;
        this.tiempoInvulnerable=System.currentTimeMillis()+duracionMilisegundos;

    }
    public boolean estaInvulnerable(){
        if (invulnerable&&System.currentTimeMillis()>tiempoInvulnerable){
            invulnerable=false;
        }
        return invulnerable;
    }
    public void activarEstrella(){
        this.disparoMejorado=true;
        System.out.println(nombre + " ahora tiene disparos mejorados!");
    }

    public Disparo intentarDisparar() {
        Vector2D posicionCentro = this.posicion;
        Vector2D direccionActual = obtenerDireccionActual().comoVector();
        Vector2D posicionDisparo = posicionCentro.sumadoA(
                direccionActual.escalado((tamanio / 2.0) + arma.obtenerTamanioDisparo()/3.0));
        Disparo disparo= arma.disparar(posicionDisparo, obtenerDireccionActual(), OrigenDisparo.JUGADOR);
        if(disparoMejorado)
        {
            disparo.hacerPoderoso();
        }
        return disparo;
    }

    public String obtenerNombre() {
        return nombre;
    }

    @Override
    public boolean impideElPaso() {
        return true;
    }

    public boolean jugadorEstaEnMovimiento() {
        return enMovimiento;
    }



}
