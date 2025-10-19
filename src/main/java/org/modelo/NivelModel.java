package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class NivelModel implements EntornoFisico{
    private final List<Jugador> jugadores;
    private final List<Enemigo> enemigos;
    private final List<Bloque> bloques;
    private final List<PowerUp>powerUps;
    private List<Disparo> disparos;
    private final int tamanioCelda;
    private final int tamanioDisparo;
    private final int anchoNivel, altoNivel;
    private int cantidadDeJugadores;
    private EstadoNivel estadoNivel;
    private GestorDeMovimientos gestorDeMovimientos;


    public NivelModel(String nombreJugador1, String nombreJugador2, int ancho, int alto, int cantidadDeJugadores){
        this.jugadores=new ArrayList<>();
        this.enemigos=new ArrayList<>();
        this.bloques=new ArrayList<>();
        this.disparos=new ArrayList<>();
        this.powerUps=new ArrayList<>();
        this.tamanioDisparo=6;
        this.tamanioCelda=20;
        this.anchoNivel=ancho;
        this.altoNivel=alto;
        this.cantidadDeJugadores=cantidadDeJugadores;
        this.estadoNivel=EstadoNivel.EN_CURSO;
        this.gestorDeMovimientos= new GestorDeMovimientos(this);
    }
    public void agregarBloque(Bloque bloque){
        this.bloques.add(bloque);
    }
    public void agregarEnemigo(Enemigo enemigo){
        this.enemigos.add(enemigo);
    }
    public void agregarJugador(Jugador jugador){
        this.jugadores.add(jugador);
    }

    public List<Obstruible> obtenerObstrucciones() {
        List<Obstruible> obstrucciones = new ArrayList<>();

        for (Bloque bloque : this.bloques) {
            if (bloque.impideElPaso()) {

                obstrucciones.add((Obstruible) bloque);
            }
        }
        return obstrucciones;
    }

    private void verificarColisionConPowerUps(Jugador jugador) {
        AreaColisionable areaJugador= jugador.obtenerAreaColisionable();
        for (PowerUp powerUp : new ArrayList<>(powerUps)) {
            if (areaJugador.estaEnArea(powerUp.obtenerAreaColisionable()))
            {
                if(powerUp.esGranada()){
                    enemigos.clear();
                    estadoNivel=EstadoNivel.VICTORIA;
                }
                powerUp.aplicarEfecto(jugador);
                powerUps.remove(powerUp);
                System.out.println("PowerUp " + powerUp.obtenerTipoPowerUp() + " consumido por " + jugador.obtenerNombre());
            }
        }
    }
    public void moverJugador(int nroJugador,Direccion direccion){
        Jugador jugador=jugadores.get(nroJugador);
        double xDesplazado=(jugador.obtenerCoordenadaX())+
                (direccion.dX()*jugador.obtenerVelocidadBase());
        double yDesplazado=(jugador.obtenerCoordenadaY())+
                (direccion.dY()*jugador.obtenerVelocidadBase());
        AreaColisionable destino= new AreaColisionable(new Vector2D(xDesplazado, yDesplazado),
                10);
        if (gestorDeMovimientos.puedeMoverA(destino)){
            verificarColisionConPowerUps(jugador);
            jugador.mover(direccion);
        }

    }

    public void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
           // enemigo.mover( obtenerBloquesColisionables() , anchoNivel,altoNivel, tamanioCelda/2);
            enemigo.mover(obtenerObstrucciones(), anchoNivel, altoNivel, tamanioCelda/2);
        }
    }


    private List<Colisionable> obtenerColisionables(){
        List<Colisionable> colisionables = new ArrayList<>();
        colisionables.addAll(jugadores);
        colisionables.addAll(enemigos);
        for (Bloque bloque : bloques) {
            if (bloque.esColisionable()) {
                colisionables.add((Colisionable) bloque);
            }
        }
        return colisionables;
    }
    public double obtenerAncho(){
        return anchoNivel;
    }

    public double obtenerAlto() {
        return this.altoNivel;
    }

    private void intentarGenerarPowerUp(){
        if (Math.random() < 0.80) {
            TipoPowerUp tipoPowerUp = TipoPowerUp.random();
            double x = Math.random() * anchoNivel;
            double y = Math.random() * altoNivel;
            PowerUp powerUp = new PowerUp(x, y, tipoPowerUp);
            this.powerUps.add(powerUp);
        }
    }
    private void actualizarColisionesConDisparos(){
        List<Colisionable>colisionables= obtenerColisionables();

        for (Disparo disparo: new ArrayList<>(disparos)) {
            for (Colisionable colisionable : colisionables){
                if (disparo.impactaA(colisionable)) {
                    ResultadoImpacto resultado = colisionable.recibirImpacto(disparo);
                    if (resultado == ResultadoImpacto.ENEMIGO_ELIMINADO) {
                        this.enemigos.remove(colisionable);
                        intentarGenerarPowerUp();
                    }

                    if (resultado == ResultadoImpacto.DESTRUIDO) {
                        this.bloques.remove(colisionable);
                    }

                    if (resultado == ResultadoImpacto.JUGADOR_ELIMINADO) {
                        this.jugadores.remove(colisionable);
                    }
                    if (resultado == ResultadoImpacto.BASE_DESTRUIDA) {
                        estadoNivel = EstadoNivel.DERROTA;
                        return;
                    }

                    disparo.desactivar();
                    disparos.remove(disparo);
                    break;
                }
            }
        }

    }

    public void actualizarMovimientos(){

        moverDisparos();
        actualizarColisionesConDisparos();
        gestorDeMovimientos.limpiarDisparosFueraDeLimites();
        moverEnemigos();
        enemigosDisparan();
        verificarEstadoNivel();

    }
    private void enemigosDisparan(){
        for (Enemigo enemigo : enemigos) {
            Disparo disparo =enemigo.disparar();
            if (disparo!=null){
                disparos.add(disparo);
            }
        }
    }
    private void moverDisparos(){
        for (Disparo disparo: new ArrayList<>(disparos)){
            disparo.mover();
        }
    }

    private void verificarEstadoNivel(){
        //System.out.println("Verificar estado: enemigos=" + enemigos.size());
        if(this.enemigos.isEmpty()){
          //  System.out.println("ganaste el nivel");
            this.estadoNivel=EstadoNivel.VICTORIA;
        }
        if (jugadores.isEmpty()){
            this.estadoNivel=EstadoNivel.DERROTA;
        }
    }

    public void jugadorDisparar(int nroJugador){
        Jugador jugador=jugadores.get(nroJugador);
        try {
           Disparo disparo=jugador.intentarDisparar();
           disparos.add(disparo);
        }catch (Exception e){
        }
    }

    public boolean enemigoEnMovimiento(Enemigo enemigo){
        return enemigo.enemigoEstaEnMovimiento();
    }

    public boolean jugadorEnMovimiento(Jugador jugador){
        return jugador.jugadorEstaEnMovimiento();
    }

    public List<Disparo> obtenerDisparos(){
        return disparos;
    }
    public List<Jugador> obtenerJugadores(){
        return this.jugadores;
    }

    public List<Enemigo> obtenerEnemigos() {
        return enemigos;
    }
    public List<Bloque> obtenerBloques(){
        return bloques;
    }
    public List<PowerUp> obtenerPowerUps(){
        return powerUps;
    }
    public boolean enCurso(){
        return estadoNivel==EstadoNivel.EN_CURSO;
    }

    public boolean terminoEnVictoria(){
        return estadoNivel==EstadoNivel.VICTORIA;
    }
    public boolean terminoEnDerrota(){
        return estadoNivel==EstadoNivel.DERROTA;
    }

}
