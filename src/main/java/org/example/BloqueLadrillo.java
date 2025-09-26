package org.example;

public class BloqueLadrillo extends Bloque{
    private int resistencia;

    public BloqueLadrillo(Vector2D posicion){
        super(posicion.obtenerCoordenadaX(), posicion.obtenerCoordenadaY());
        this.resistencia = 3;
    }

    @Override
    public boolean bloqueaPasoTanque() {
        return true;
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
    public void recibeimpacto() {
        resistencia--;
        if (resistencia <= 0) {
            // destruir bloque: actualizar grilla o estado
            // El bloque es destruido, se puede implementar la lógica para eliminarlo del juego
            System.out.println("Bloque de ladrillo destruido en la posición: " + posicion);
        } else {
            System.out.println("Bloque de ladrillo impactado, resistencia restante: " + resistencia);
        }
    }
}
