package org.modelo;

public class EnemigoRegular extends Enemigo{
    public EnemigoRegular(double coordenadaX, double coordenadaY) {
        super(coordenadaX, coordenadaY, 1, 1);
    }
    public TipoEnemigo obtenerTipo(){
        return TipoEnemigo.REGULARENEMY;
    }
}
