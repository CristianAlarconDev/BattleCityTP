package org.modelo;

public class BloqueLadrillo extends Bloque implements Colisionable,  Obstruible{
    private int resistencia;
    private AreaColisionable areaColisionable;

    public BloqueLadrillo(Vector2D posicion){
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY(),true,true);
        this.resistencia = 3;
        areaColisionable = new AreaColisionable(posicion, 10);
    }

    @Override
    public ResultadoImpacto recibirImpacto(Disparo disparo) {
        resistencia-=1;
        if (resistencia==0){
            return ResultadoImpacto.DESTRUIDO;
        }
        return ResultadoImpacto.NADA;
    }

    public TipoBloque obtenerTipo(){
        return TipoBloque.LADRILLO;
    }

    public AreaColisionable obtenerAreaColisionable(){
        return areaColisionable;
    }

}
