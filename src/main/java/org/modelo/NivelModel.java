package org.modelo;

import java.util.ArrayList;
import java.util.List;

public class NivelModel implements EntornoFisico, ContextoDeColision, ReglasDeNivel, ReglasDeMovimiento{
    private final List<Jugador> jugadores;
    private final List<Enemigo> enemigos;
    private final List<Bloque> bloques;
    private final List<PowerUp>powerUps;
    private final List<Disparo> disparos;
    private final int anchoNivel, altoNivel;
    private EstadoNivel estadoNivel;
    private final GestorDeMovimientos gestorDeMovimientos;
    private final GestorDeColisiones gestorDeColisiones;

    public NivelModel(int ancho, int alto){
        this.jugadores=new ArrayList<>();
        this.enemigos=new ArrayList<>();
        this.bloques=new ArrayList<>();
        this.disparos=new ArrayList<>();
        this.powerUps=new ArrayList<>();
        this.anchoNivel=ancho;
        this.altoNivel=alto;
        this.estadoNivel=EstadoNivel.EN_CURSO;
        this.gestorDeMovimientos= new GestorDeMovimientos(this, this);
        this.gestorDeColisiones=new GestorDeColisiones(this, this);
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
            /*cambiar aca de donde obtiene enemigo el tamanio de la celda,
            en jguador delegue a la clase AreaColisionable*/
            enemigo.mover(obtenerObstrucciones(), anchoNivel, altoNivel, 10);
        }
    }


    public List<Colisionable> obtenerColisionables(){
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

    public void intentarGenerarPowerUp(){
        if (Math.random() < 0.80) {
            TipoPowerUp tipoPowerUp = TipoPowerUp.random();
            double x = Math.random() * anchoNivel;
            double y = Math.random() * altoNivel;
            PowerUp powerUp = new PowerUp(x, y, tipoPowerUp);
            this.powerUps.add(powerUp);
        }
    }

    public void actualizarMovimientos(){
        moverDisparos();
        gestorDeColisiones.comprobarColisiones();
        gestorDeMovimientos.limpiarDisparosFueraDeLimites();
        moverEnemigos();
        enemigosDisparan();
        verificarEstadoNivel();
    }

    private void enemigosDisparan(){
        for (Enemigo enemigo : enemigos) {
            Disparo disparo =enemigo.intentarDisparar();
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
        if(this.enemigos.isEmpty()){
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
        }catch (Exception _){
        }
    }
    public void eliminarColisionable(Colisionable colisionable){
        jugadores.remove(colisionable);
        enemigos.remove(colisionable);
        bloques.remove(colisionable);
    }
    public void eliminarDisparo(Disparo disparo){
        disparo.desactivar();
        disparos.remove(disparo);
    }

    public boolean tanqueEnMovimiento(Tanque tanque) {
        return tanque.estaEnMovimiento();
    }

    public List<Disparo> obtenerDisparos(){
        return new ArrayList<>(disparos);
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

    @Override
    public void finalizarNivel() {
        estadoNivel=EstadoNivel.DERROTA;
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
