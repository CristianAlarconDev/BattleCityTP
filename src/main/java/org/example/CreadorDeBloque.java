package org.example;

public class CreadorDeBloque {

    public static Bloque crearBloque(String tipo, double coordenadaX, double coordenadaY) {
        Vector2D posicion = new Vector2D(coordenadaX, coordenadaY);
        return switch (tipo.toLowerCase()) {
            case "steelblock" -> new BloqueAcero(posicion);
            case "brickblock" -> new BloqueLadrillo(posicion);
            case "waterblock" -> new BloqueAgua(posicion);
            case "forestblock" -> new BloqueBosque(posicion);
            case "baseblock" -> new BloqueBase(posicion);
            default -> throw new IllegalArgumentException("Tipo de bloque desconocido: " + tipo);
        };
    }
}
