package org.example;

public class Jugador extends Tanque{
    private String nombre;
    Jugador(Posicion posicion, String nombre){
        super(posicion);
        this.nombre = nombre;
    }
    public Disparo atacar(Direccion direccion){
        /*logica de ataque, revisar la de enemigo para
        * polimorfismo*/
        this.cambiarDireccion(direccion);
        Disparo disparo= new Disparo(this.posicion,this.direccion);
        return disparo;

    }
    public Disparo atacar(){
        Disparo disparo= new Disparo(this.posicion,this.direccion);
        return disparo;
    }

    public String obtenerNombre(){
        return nombre;
    }
}
