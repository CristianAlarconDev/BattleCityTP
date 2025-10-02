package org.modelo;

public class BloqueAgua extends Bloque implements Colisionable{
    public BloqueAgua(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.AGUA;
    }
    @Override
    public boolean impideElPaso() {
        return true;
    }

    @Override
    public boolean esColisionable() {
        return true;
    }

    @Override
    public ResultadoImpacto recibirImpacto(Disparo disparo) {
        return ResultadoImpacto.NADA;
    }

}
