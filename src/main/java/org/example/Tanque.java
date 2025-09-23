package org.example;

public class Tanque {
    protected Vector2D posicion;
    protected  int vidasTotales;
    protected double velocidadBase;


    Tanque(double coordenadaX, double coordenadaY, double velocidadBase){
        this.posicion = new Vector2D(coordenadaX,coordenadaY);
        this.vidasTotales = 3;
        this.velocidadBase = velocidadBase;

    }
    public void mover(Direccion direccion){
        Vector2D desplazamiento =direccion.comoVector();
        desplazamiento.multiplicarPor(velocidadBase);
        this.moverA(desplazamiento);
    }
    protected void moverA(Vector2D desplazamiento){
        posicion.desplazar(desplazamiento);
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
    public Vector2D obtenerPosicion(){
        return new Vector2D(posicion.obtenerCoordenadaX(),
                posicion.obtenerCoordenadaY());
    }
    public boolean estaEnPosicion(double coordenadaX, double coordenadaY){
        return this.posicion.esIgualA(new Vector2D(coordenadaX,coordenadaY));
    }
    /*Esto pensado para powerups*/
    public void cambiarVelocidadBase(double velocidadBase){
        this.velocidadBase = velocidadBase;
    }
    public double obtenerVelocidadBase(){
        return velocidadBase;
    }

}
