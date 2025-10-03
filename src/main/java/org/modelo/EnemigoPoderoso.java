package org.modelo;

public class EnemigoPoderoso extends Enemigo{
    public EnemigoPoderoso(double coordenadaX, double coordenadaY) {
        super(coordenadaX, coordenadaY, 1, 1);
    }
    public TipoEnemigo obtenerTipo(){
        return TipoEnemigo.POWERFULENEMY;
    }
}
