package org.example;

public class Enemigo extends Tanque{
    private String tipo;

    Enemigo(String tipo, double x, double y, double velocidadBase) {
        super(x, y, velocidadBase);
        this.tipo=tipo;
        this.direccionActual = Direccion.ABAJO; /*cambiar de ser necesario, sobreescribe
        a la clase padre*/
    }
    public String obtenerTipo(){
        return tipo;
    }

    /*idem que jugador, revisar si hay mas logica necesaria y si no llevar
     metodo a tanque*/
    public Disparo disparar(){
        return new Disparo(obtenerPosicion(), obtenerDireccionActual(), obtenerVelocidadBase());
    }
/*un comentario*/
/*otro comentario*/
    /*tercer comentario*/
}
