package org.controlador;

import javafx.scene.Scene;

import org.modelo.JuegoBattle;

public class JuegoController {
    private final JuegoBattle juego;
    public JuegoController(int  cantJugadores) {
        this.juego = new JuegoBattle(cantJugadores);
    }

}
