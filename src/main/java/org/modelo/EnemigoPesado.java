package org.modelo;

public class EnemigoPesado extends Enemigo{
    public EnemigoPesado(double coordenadaX, double coordenadaY) {
        super(coordenadaX, coordenadaY, 1, 3);
    }
    public TipoEnemigo obtenerTipo(){
        return TipoEnemigo.HEAVYENEMY;
    }
}
