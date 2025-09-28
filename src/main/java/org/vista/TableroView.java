package org.vista;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.controlador.InputController;
import org.controlador.JuegoController;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import org.modelo.Jugador;

public class TableroView {
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    private static final int CELDA = 20;

    private final JuegoController controller;
    private final InputController inputController;

    public TableroView(JuegoController controller) {
        this.controller = controller;
        this.inputController = new InputController(controller);
    }
    public Scene crearTableroView(){
        Canvas canvas = new Canvas(ANCHO, ALTO);
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        actualizarPantalla(graphics);

        Scene scene = new Scene(new StackPane(canvas), ANCHO, ALTO, Color.GRAY);

        scene.setOnKeyPressed(evento->{
          inputController.presionarTecla(evento.getCode());
          actualizarPantalla(graphics);
        });
        scene.setOnKeyReleased(evento->{
            inputController.soltarTecla(evento.getCode());
            actualizarPantalla(graphics);
        });
        return scene;
    }
    private void dibujarJugadores(GraphicsContext graphicsContext){
        int i=0;
        for (Jugador jugador: controller.obtenerJugadores()){
            graphicsContext.setFill(i == 0? Color.BLUE:Color.RED);
            graphicsContext.fillRect(jugador.obtenerCoordenadaX(),
                    jugador.obtenerCoordenadaY(), CELDA, CELDA);
            i++;
        }
    }
    private void actualizarPantalla(GraphicsContext graphics){
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, ANCHO, ALTO);
        dibujarJugadores(graphics);
    }
}
