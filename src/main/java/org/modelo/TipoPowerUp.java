package org.modelo;

import java.util.Random;

public enum TipoPowerUp {
    EXTRA_VIDA, VELOCIDAD, DISPARO_RAPIDO;
    private static final Random random = new Random();
    public static TipoPowerUp random(){
        TipoPowerUp[] valores= values();
        return valores[random.nextInt(valores.length)];
    }

}
