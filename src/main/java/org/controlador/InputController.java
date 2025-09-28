package org.controlador;

import javafx.scene.input.KeyCode;
import org.modelo.Direccion;

import java.util.HashSet;
import java.util.Set;

public class InputController {
    private final JuegoController juegoController;
    private final Set<KeyCode> teclasActivas;

    public InputController(JuegoController juegoController) {
        this.juegoController = juegoController;
        teclasActivas= new HashSet<>();
    }
    public void presionarTecla(KeyCode tecla){
        teclasActivas.add(tecla);
        procesarTecla(tecla);
    }
    public void  soltarTecla(KeyCode tecla){
        teclasActivas.remove(tecla);
    }

    private void procesarTecla(KeyCode tecla){
        switch (tecla){
            case W -> juegoController.moverJugador(0, Direccion.ARRIBA);
            case A ->  juegoController.moverJugador(0,Direccion.IZQUIERDA);
            case S -> juegoController.moverJugador(0,Direccion.ABAJO);
            case D-> juegoController.moverJugador(0,Direccion.DERECHA);

            case UP -> juegoController.moverJugador(1, Direccion.ARRIBA);
            case LEFT ->  juegoController.moverJugador(1,Direccion.IZQUIERDA);
            case DOWN -> juegoController.moverJugador(1,Direccion.ABAJO);
            case RIGHT-> juegoController.moverJugador(1,Direccion.DERECHA);
            case SPACE -> juegoController.jugadorNroDispara(0);
            case ENTER -> juegoController.jugadorNroDispara(1);
        }
    }
}
