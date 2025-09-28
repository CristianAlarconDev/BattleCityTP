package org.controlador;

import javafx.scene.input.KeyCode;

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
    }
    public void  soltarTecla(KeyCode tecla){
        teclasActivas.remove(tecla);
    }

}
