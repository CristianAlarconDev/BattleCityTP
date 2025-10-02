package org.modelo;

import java.util.Random;

public enum TipoPowerUp {
    GRANADA, CASCO, ESTRELLA;
    private static final Random random = new Random();
    public static TipoPowerUp random(){
        TipoPowerUp[] valores= values();
        return valores[random.nextInt(valores.length)];
    }

}
