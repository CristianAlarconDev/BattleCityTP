package org.modelo;

public class EnemigoRapido extends Enemigo{
    //agregar velocidad de movimiento
    public EnemigoRapido(double coordenadaX, double coordenadaY) {
        super(coordenadaX, coordenadaY, 4, 1);
    }
    public TipoEnemigo obtenerTipo(){
        return TipoEnemigo.FASTENEMY;
    }
}
