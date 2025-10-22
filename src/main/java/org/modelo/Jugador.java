package org.modelo;

public class Jugador extends Tanque{
    private String nombre;
    private boolean congelado;
    private long tiemporDescongelacion;
    private final int  tiempoCongelado;
    private boolean invulnerable;
    private long tiempoInvulnerable;
    private boolean disparoMejorado;

    public Jugador(String nombre, double x, double y, double velocidadMovBase, int radioColision) {
        super(x, y, velocidadMovBase,3, radioColision);
        this.nombre = nombre;
        this.congelado = false;
        this.tiemporDescongelacion = 0;
        tiempoCongelado=2000;
        invulnerable=false;
        tiempoInvulnerable=0;
    }

    public void mover(Direccion direccion){
        if(estaCongelado()){
            setEnMovimiento(false);
            return;
        }
        if (direccion != null) {
            super.mover(direccion);
            setEnMovimiento(true);
        } else {
            setEnMovimiento(false);
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
        this.perderVida();
        if (!this.estaVivo()) {
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
        Disparo disparo = prepararDisparo(OrigenDisparo.JUGADOR);
        if(disparoMejorado)
        {
            disparo.hacerPoderoso();
        }
        return disparo;
    }

    public String obtenerNombre() {
        return nombre;
    }

}
