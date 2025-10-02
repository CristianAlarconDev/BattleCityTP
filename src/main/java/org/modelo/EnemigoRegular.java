package org.modelo;

public class EnemigoRegular extends Enemigo{
    //agregar velocidad de disparo
    public EnemigoRegular(double coordenadaX, double coordenadaY) {
        super(coordenadaX, coordenadaY, 1, 1);
    }
    public TipoEnemigo obtenerTipo(){
        return TipoEnemigo.REGULARENEMY;
    }
}
