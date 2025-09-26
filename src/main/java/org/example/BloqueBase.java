package org.example;

public class BloqueBase extends Bloque {
    private int resistencia;

        public BloqueBase(Vector2D posicion) {
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
        this.resistencia = 1;
    }

    @Override
    public boolean bloqueaPasoTanque() {
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
    public void recibeimpacto() {
        resistencia--;
        if (resistencia <= 0) {
            System.out.println("La base ha sido destruida!");
            // Aquí podrías agregar lógica adicional para manejar la destrucción de la base
        } else {
            System.out.println("La base ha recibido un impacto! Vida restante: " + resistencia);
        }
    }


}
