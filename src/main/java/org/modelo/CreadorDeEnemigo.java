package org.modelo;

public class CreadorDeEnemigo {

    public static Enemigo crearEnemigo(String tipo, double coordenadaX, double coordenadaY) {
        return switch (tipo.toLowerCase()) {
            case "regularenemy" -> new EnemigoRegular(coordenadaX, coordenadaY);
            case "fastenemy" -> new EnemigoRapido(coordenadaX, coordenadaY);
            case "powerfulenemy" -> new EnemigoPoderoso(coordenadaX,coordenadaY);
            case "heavyenemy" -> new EnemigoPesado( coordenadaX, coordenadaY);
            default -> throw new IllegalArgumentException("Tipo de enemigo desconocido: " + tipo);
        };
    }
}
