package org.modelo;

public class PowerUp extends Bloque{
    private final TipoPowerUp tipoPowerUp;
    public PowerUp(double coordenadaX, double coordenadaY, TipoPowerUp powerUp) {
        super(coordenadaX, coordenadaY);
        tipoPowerUp=powerUp;

    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.POWERUP;
    }
    @Override
    public boolean impideElPaso() {
        return false;
    }
    @Override
    public boolean esColisionable() {
        /*asi los disparos no se pueden colisionar con los powerups*/
        return false;
    }
    public TipoPowerUp obtenerTipoPowerUp(){
        return tipoPowerUp;
    }

}
