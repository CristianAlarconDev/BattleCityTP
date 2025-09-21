package org.example;

public class Bloque {
    private Posicion posicion;
    private int resitencia;
    String tipo;

    Bloque(Posicion posicion, String tipo){
        this.posicion = posicion;
        this.tipo = tipo;

    }
    public Posicion obtenerPosicion(){
        return posicion;
    }

    public boolean bloqueaElMovimiento(){
        if (tipo.equals("bosque")) {
            return false;
        }
        return true;
    }
    public boolean bloqueDisparo(){
        if (tipo.equals("agua")) {
            return false;
        }
        return true;
    }
    public boolean recibirDanio(){
        if (tipo.equals("ladrillo")) {
            resitencia--;
            return true;
        }
        return false;
    }




}
