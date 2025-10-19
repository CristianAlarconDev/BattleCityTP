package org.modelo;

public class BloqueLadrillo extends Bloque implements Colisionable,  Obstruible{
    private int resistencia;
    private AreaColisionable areaColisionable;

    public BloqueLadrillo(Vector2D posicion){
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
        this.resistencia = 3;
        areaColisionable = new AreaColisionable(posicion, 10);
    }

    @Override
    public double obtenerCoordenadaX() {
        return posicion.obtenerCoordenadaX();
    }

    @Override
    public double obtenerCoordenadaY() {
        return posicion.obtenerCoordenadaY();
    }

    @Override
    public ResultadoImpacto recibirImpacto(Disparo disparo) {
        resistencia-=1;
        if (resistencia==0){
            return ResultadoImpacto.DESTRUIDO;
        }
        return ResultadoImpacto.NADA;
    }
    public boolean impideElPaso(){
        return true;
    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.LADRILLO;
    }

    @Override
    public boolean esColisionable() {
        return true;
    }
    public AreaColisionable obtenerAreaColisionable(){
        return areaColisionable;
    }

}
