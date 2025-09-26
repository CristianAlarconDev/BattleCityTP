package org.modelo;

public class Tanque {
    protected Vector2D posicion;
    protected  int vidasTotales;
    protected double velocidadBase;
    protected Direccion direccionActual;

    public Tanque(double coordenadaX, double coordenadaY, double velocidadBase){
        this.posicion = new Vector2D(coordenadaX,coordenadaY);
        this.vidasTotales = 3;
        this.velocidadBase = velocidadBase;
        this.direccionActual = Direccion.ARRIBA;
    }
    protected Direccion obtenerDireccionActual(){
        return direccionActual;
    }
    public void cambiarDireccion(Direccion direccion){
        this.direccionActual = direccion;
    }
    public void mover(Direccion direccion){
        Vector2D dir =direccion.comoVector();
        Vector2D desplazamiento = dir.escalado(velocidadBase);
        this.moverA(desplazamiento);
        cambiarDireccion(direccion);
    }
    private void moverA(Vector2D desplazamiento){
        posicion=posicion.sumadoA(desplazamiento);
    }
    public void moverSegunDireccionActual(){
        mover(this.obtenerDireccionActual());
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
        return posicion.esIgualA(new Vector2D(coordenadaX,coordenadaY));
    }
    /*Esto pensado para powerups*/
    public void cambiarVelocidadBase(double velocidadBase){

        this.velocidadBase = velocidadBase;
    }

    public double obtenerVelocidadBase(){

        return velocidadBase;
    }

}
