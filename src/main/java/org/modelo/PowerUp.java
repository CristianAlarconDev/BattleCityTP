package org.modelo;

public class PowerUp extends Bloque{
    private final TipoPowerUp tipoPowerUp;
    private AreaColisionable areaColisionable;
    public PowerUp(double coordenadaX, double coordenadaY, TipoPowerUp powerUp) {
        super(coordenadaX, coordenadaY,false,false);
        tipoPowerUp=powerUp;
        areaColisionable = new AreaColisionable(posicion, 10);

    }
    public AreaColisionable obtenerAreaColisionable(){
        return areaColisionable;
    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.POWERUP;
    }

    public TipoPowerUp obtenerTipoPowerUp(){
        return tipoPowerUp;
    }
    public void aplicarEfecto(Jugador jugador){
        switch (tipoPowerUp){
            case CASCO ->
                jugador.activarCasco(10000);
            case ESTRELLA ->
                jugador.activarEstrella();
            case GRANADA ->{}


        }
    }

    public boolean esGranada(){
        return tipoPowerUp==TipoPowerUp.GRANADA;
    }
}
