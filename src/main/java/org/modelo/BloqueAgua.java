package org.modelo;

public class BloqueAgua extends Bloque implements Colisionable, Obstruible{
    private AreaColisionable areaColisionable;
    public BloqueAgua(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY(),true,false);
        areaColisionable = new AreaColisionable(posicion, 10);
    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.AGUA;
    }

    @Override
    public ResultadoImpacto recibirImpacto(Disparo disparo) {
        return ResultadoImpacto.NADA;
    }
    public AreaColisionable obtenerAreaColisionable(){
        return areaColisionable;
    }

}
