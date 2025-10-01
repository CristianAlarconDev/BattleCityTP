package org.controlador;

import javafx.scene.input.KeyCode;
import org.modelo.Direccion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InputController {
    private final JuegoController juegoController;
    private final Map<Integer,Direccion> direccionesActivas;
    public InputController(JuegoController juegoController) {
        this.juegoController = juegoController;
        direccionesActivas= new HashMap<>();
    }
    public void presionarTecla(KeyCode tecla){
        switch (tecla){
            case W -> direccionesActivas.put(0, Direccion.ARRIBA);
            case A ->  direccionesActivas.put(0,Direccion.IZQUIERDA);
            case S -> direccionesActivas.put(0,Direccion.ABAJO);
            case D-> direccionesActivas.put(0,Direccion.DERECHA);

            case UP -> direccionesActivas.put(1, Direccion.ARRIBA);
            case LEFT ->  direccionesActivas.put(1,Direccion.IZQUIERDA);
            case DOWN -> direccionesActivas.put(1,Direccion.ABAJO);
            case RIGHT-> direccionesActivas.put(1,Direccion.DERECHA);
            case SPACE -> juegoController.jugadorDispara(0);
            case ENTER -> juegoController.jugadorDispara(1);
        }
    }
    public void  soltarTecla(KeyCode tecla){
        switch (tecla){
            case W,A,S,D -> direccionesActivas.remove(0);
            case UP,LEFT,DOWN,RIGHT -> direccionesActivas.remove(1);
        }
    }


    public void procesarInputs(){
        for (Map.Entry<Integer,Direccion> entrada: direccionesActivas.entrySet()){
            int jugador=entrada.getKey();
            Direccion direccion=entrada.getValue();
            juegoController.moverJugador(jugador,direccion);
        }
    }
}
