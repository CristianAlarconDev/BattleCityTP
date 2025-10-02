package org.modelo;

public class BloqueBase extends Bloque implements Colisionable {
    private int resistencia;

        public BloqueBase(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
        this.resistencia = 1;
    }
    public TipoBloque obtenerTipo(){
        return TipoBloque.BASE;
    }
    public ResultadoImpacto recibirImpacto(Disparo disparo){
            resistencia--;
            return ResultadoImpacto.BASE_DESTRUIDA;
    }
    public boolean estaDestruida(){
        return resistencia <= 0;
    }

    public double obtenerCoordenadaX(){
        return posicion.obtenerCoordenadaX();
    }
    public double obtenerCoordenadaY(){
        return posicion.obtenerCoordenadaY();
    }

    @Override
    public boolean impideElPaso() {
        return true;
    }

    @Override
    public boolean bloqueaDisparo() {
        return false;
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
