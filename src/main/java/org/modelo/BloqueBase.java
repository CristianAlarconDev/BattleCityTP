package org.modelo;

public class BloqueBase extends Bloque implements Colisionable {
    private int resistencia;
    private AreaColisionable areaColisionable;
    public BloqueBase(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY(),false,true);
        this.resistencia = 1;
        areaColisionable = new AreaColisionable(posicion, 10);
    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.BASE;
    }
    public ResultadoImpacto recibirImpacto(Disparo disparo){
            resistencia--;
            return ResultadoImpacto.BASE_DESTRUIDA;
    }

    public AreaColisionable obtenerAreaColisionable(){
        return areaColisionable;
    }
}
