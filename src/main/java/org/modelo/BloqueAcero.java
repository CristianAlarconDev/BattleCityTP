package org.modelo;

public class BloqueAcero extends Bloque implements Colisionable, Obstruible {
    private AreaColisionable areaColisionable;
    public BloqueAcero(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY(),true,true);
        /*prueba de uso de nueva clase AreaColisionable*/
        areaColisionable = new AreaColisionable(posicion, 10);
        /**/
    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.ACERO;
    }

    @Override
    public ResultadoImpacto recibirImpacto(Disparo disparo) {
        return ResultadoImpacto.NADA;
    }
    public AreaColisionable obtenerAreaColisionable(){
        return areaColisionable;
    }

}
