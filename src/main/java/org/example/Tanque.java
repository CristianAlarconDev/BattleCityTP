package org.example;

public class Tanque {
    private Posicion posicion;
    private int vidasTotales;
    private  Direccion direccion;
    Tanque(Posicion posicion){
        this.posicion = posicion;
        this.vidasTotales = 3;
        this.direccion = Direccion.STANDBY;
    }
    public boolean cambiarDireccion( Direccion direccion){
        if(direccion.estaEnStandBy()||!estaVivo()){
            /*revisar si direccion deberia validarse
            a si misma en standby o lo debe hacer tanque*/
            return false;
        }
        this.direccion = direccion;
        return true;
    }

    public boolean moverseSegunDireccion( int pasos){
        if (direccion.estaEnStandBy()||!estaVivo()){
            return false;
        }
        int ultimaPosicionX= posicion.obtenerCoordenadaX();
        int ultimaPosicionY = posicion.obtenerCoordenadaY();
        switch (direccion){
            case ARRIBA ->ultimaPosicionY+= pasos;
            case ABAJO -> ultimaPosicionY-= pasos;
            case IZQUIERDA -> ultimaPosicionX-= pasos;
            case DERECHA -> ultimaPosicionX+= pasos;
        }
        posicion.cambiarA(ultimaPosicionX,ultimaPosicionY);
        return true;
    }
    public boolean estaEnPosicion(int coordenadaX, int coordenadaY){
        return posicion.esIgualA(coordenadaX,coordenadaY);
    }

    public boolean estaVivo(){
        return vidasTotales > 0;
    }

    public boolean recibirDanio(){
        if (!estaVivo()){
            return false;
        }
        vidasTotales--;
        return estaVivo();
    }
}
