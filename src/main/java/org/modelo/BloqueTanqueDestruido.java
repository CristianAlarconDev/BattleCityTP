package org.modelo;

public class BloqueTanqueDestruido extends Bloque implements Colisionable, Obstruible {
    private AreaColisionable areaColisionable;

    public BloqueTanqueDestruido(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY(),true,true);
        areaColisionable = new AreaColisionable(posicion, 10);
    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.TANQUE_DESTRUIDO;
    }

    @Override
    public ResultadoImpacto recibirImpacto(Disparo disparo) {
        return ResultadoImpacto.NADA;
    }
    public AreaColisionable obtenerAreaColisionable(){
        return areaColisionable;
    }

}
