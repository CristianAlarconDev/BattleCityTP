package org.example;

public class Bloque {
    private Posicion posicion;
    private int resitencia;
    String tipo;
    Bloque(Posicion posicion, String tipo){
        this.posicion = posicion;
        this.tipo = tipo;

    }
    private void setResistencia(String tipo){
        /*Por tipo setea resistencia*/
        if (tipo.equals("facil")){
            resitencia = 1;
        }
        else if (tipo.equals("normal")){
            resitencia = 2;
        }
        else if (tipo.equals("dificil")){
            resitencia = 3;
        }


    }


}
