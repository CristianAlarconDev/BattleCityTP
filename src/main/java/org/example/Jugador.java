package org.example;

public class Jugador extends Tanque{
    private String nombre;
    Jugador(Posicion posicion, String nombre){
        super(posicion);
        this.nombre = nombre;
    }
    public void atacar(){
        /*logica de ataque, revisar la de enemigo para
        * polimorfismo*/

    }
    public String obtenerNombre(){
        return nombre;
    }
}
