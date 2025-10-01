package org.modelo;

public class BloqueLadrillo extends Bloque implements Colisionable{
    private int resistencia;

    public BloqueLadrillo(Vector2D posicion){
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
        this.resistencia = 3;
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
    public boolean destruido(){
        return resistencia == 0;
    }

    @Override
    public boolean bloqueaDisparo() {
        return true;
    }

    @Override
    public boolean esDestructible() {
        return true;
    }

    @Override
    public boolean esColisionable() {
        return true;
    }

}
