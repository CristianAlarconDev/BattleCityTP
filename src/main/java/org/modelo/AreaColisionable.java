package org.modelo;

public class AreaColisionable {
    private Vector2D centro;
    private double semilado;

    public AreaColisionable(Vector2D centro, double semilado){
        this.centro = centro;
        this.semilado = semilado;
    }
    public boolean estaEnArea(AreaColisionable otraArea){
        double distanciaX = Math.abs(centro.obtenerCoordenadaX() - otraArea.obtenerCentroX());
        double distanciaY = Math.abs(centro.obtenerCoordenadaY() - otraArea.obtenerCentroY());

        double sumaSemilados = this.semilado + otraArea.obtenerSemilado();

        boolean colisionaX = distanciaX < sumaSemilados;
        boolean colisionaY = distanciaY < sumaSemilados;

        return colisionaX && colisionaY;
    }
    public double obtenerSemilado(){
        return semilado;
    }
    public double obtenerCentroX(){
        return centro.obtenerCoordenadaX();
    }
    public double obtenerCentroY(){
        return centro.obtenerCoordenadaY();
    }


}
