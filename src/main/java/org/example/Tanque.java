package org.example;

public class Tanque {
    protected Posicion posicion;
    protected int vidasTotales;
    protected   Direccion direccion;
    Tanque(Posicion posicion){
        this.posicion = posicion;
        this.vidasTotales = 3;
        this.direccion = Direccion.ARRIBA;
    }
    public boolean cambiarDireccion( Direccion direccion){
        if(!estaVivo()){
            /*revisar si direccion deberia validarse
            */
            return false;
        }
        this.direccion = direccion;
        return true;
    }

    public boolean moverseSegunDireccion( int pasos){
        if (!estaVivo()){
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
