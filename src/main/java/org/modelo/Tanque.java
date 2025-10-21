package org.modelo;

public abstract class Tanque implements Colisionable{
    private Vector2D posicion;
    private int vidasTotales;
    private double velocidadBase;
    private Direccion direccionActual;
    private int tamanio;
    private ArmaUnDisparo arma;
    private boolean enMovimiento;
    private AreaColisionable areaColisionable;

    public Tanque(double coordenadaX, double coordenadaY, double velocidadBase, int vidas, int radioColision) {
        this.posicion = new Vector2D(coordenadaX, coordenadaY);
        this.vidasTotales = vidas;
        this.velocidadBase = velocidadBase;
        this.direccionActual = Direccion.ARRIBA;
        this.tamanio = 20;
        this.arma=new ArmaUnDisparo(this.obtenerVelocidadBase());
        enMovimiento=false;
        areaColisionable = new AreaColisionable(this.obtenerPosicion(), radioColision);

    }

    public abstract ResultadoImpacto recibirImpacto(Disparo disparo);

    public Vector2D obtenerPosicion() {
        return posicion;
    }

    public int obtenerVidasTotales() {
        return vidasTotales;
    }

    public double obtenerVelocidadBase() {
        return velocidadBase;
    }

    public Direccion obtenerDireccionActual() {
        return direccionActual;
    }

    public int obtenerTamanio() {
        return tamanio;
    }

    public boolean estaEnMovimiento() {
        return enMovimiento;
    }

    public double obtenerCoordenadaX() {
        return posicion.obtenerCoordenadaX();
    }

    public double obtenerCoordenadaY() {
        return posicion.obtenerCoordenadaY();
    }

    public AreaColisionable obtenerAreaColisionable(){
        return areaColisionable;
    }




    public void setPosicion(Vector2D nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }

    public void setVidasTotales(int vidas) {
        this.vidasTotales = vidas;
    }

    public void cambiarVelocidadBase(double velocidadBase) {
        this.velocidadBase = velocidadBase;
    }

    public void cambiarDireccion(Direccion direccion) {
        this.direccionActual = direccion;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public void setEnMovimiento(boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }

    public void perderVida() {
        this.vidasTotales = Math.max(0, this.vidasTotales - 1);
    }

    public void setAreaColisionable(AreaColisionable areaColisionable) {
        this.areaColisionable = areaColisionable;
    }



    public void mover(Direccion direccion) {
        Vector2D dir = direccion.comoVector();
        Vector2D desplazamiento = dir.escalado(velocidadBase);
        this.moverA(desplazamiento);
        cambiarDireccion(direccion);
    }

    private void moverA(Vector2D desplazamiento) {
        //posicion=posicion.sumadoA(desplazamiento);
        double nuevaX = posicion.obtenerCoordenadaX() + desplazamiento.obtenerCoordenadaX();
        double nuevaY = posicion.obtenerCoordenadaY() + desplazamiento.obtenerCoordenadaY();
        posicion.cambiarCoordenadas(nuevaX, nuevaY);
    }

    public boolean estaVivo() {
        return vidasTotales > 0;
    }

    public boolean estaEnPosicion(double coordenadaX, double coordenadaY) {
        return posicion.esIgualA(new Vector2D(coordenadaX, coordenadaY));
    }

    public Disparo prepararDisparo(OrigenDisparo origen) {
        if (!arma.puedeDisparar() && origen == OrigenDisparo.ENEMIGO) {
            return null;
        }
        Vector2D posicionCentro = this.obtenerPosicion();
        Vector2D direccionVector = this.obtenerDireccionActual().comoVector();
        double desplazamiento = (this.obtenerTamanio() / 2.0) + (arma.obtenerTamanioDisparo() / 3.0);
        Vector2D posicionDisparo = posicionCentro.sumadoA(direccionVector.escalado(desplazamiento));
        Disparo disparoActual = arma.disparar(posicionDisparo, this.obtenerDireccionActual(), origen);
        return disparoActual ;
    }



}
