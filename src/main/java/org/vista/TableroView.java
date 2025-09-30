package org.vista;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.controlador.InputController;
import org.controlador.JuegoController;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import org.modelo.*;

public class TableroView {
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    private static final int TAMANIO_JUGADOR = 20;
    private static final int TAMANIO_DISPARO = 6;

    private final JuegoController juegoController;
    private final InputController inputController;

    public TableroView(JuegoController juegoController) {
        this.juegoController = juegoController;
        this.inputController = new InputController(juegoController);
    }
    public Scene crearTableroView(){
        Canvas canvas = new Canvas(ANCHO, ALTO);
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        //actualizarPantalla(graphics);

        Scene scene = new Scene(new StackPane(canvas), ANCHO, ALTO, Color.GRAY);

        scene.setOnKeyPressed(evento->{
          inputController.presionarTecla(evento.getCode());
          //actualizarPantalla(graphics);
        });
        scene.setOnKeyReleased(evento->{
            inputController.soltarTecla(evento.getCode());
            //actualizarPantalla(graphics);
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                /*probar si se puede disminuir la velocidad con long now
                * se procesa muy rapido y puede variar en otro SO*/
                inputController.procesarInputs();
                juegoController.actualizarJuego();
                actualizarPantalla(graphics);
            }
        };
        timer.start();
        return scene;
    }
    private void dibujarJugadores(GraphicsContext graphicsContext){
        int i=0;
        for (Jugador jugador: juegoController.obtenerJugadores()){
            graphicsContext.setFill(i == 0? Color.BLUE:Color.RED);
            double xEsquina = jugador.obtenerCoordenadaX() - (TAMANIO_JUGADOR  / 2.0);
            double yEsquina = jugador.obtenerCoordenadaY() - (TAMANIO_JUGADOR  / 2.0);
            graphicsContext.fillRect(xEsquina,yEsquina, TAMANIO_JUGADOR , TAMANIO_JUGADOR );
            i++;
        }
    }
    private void dibujarDisparos(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.YELLOW);
        for(Disparo disparo: juegoController.obtenerDisparos()){
            double xEsquina = disparo.obtenerCoordenadaX()-TAMANIO_DISPARO/2.0;
            double yEsquina = disparo.obtenerCoordenadaY()-TAMANIO_DISPARO/2.0;
            graphicsContext.fillRect(xEsquina,yEsquina, TAMANIO_DISPARO, TAMANIO_DISPARO);
        }
    }
    private void dibujarBloques(GraphicsContext graphicsContext){
        for (Bloque bloque : juegoController.obtenerBloques()) {
            if (bloque instanceof BloqueLadrillo) {
                graphicsContext.setFill(Color.ORANGE);
            } else if (bloque instanceof BloqueAcero) {
                graphicsContext.setFill(Color.GRAY);
            } else {
                graphicsContext.setFill(Color.DARKGREEN);
            }
            graphicsContext.fillRect(
                    bloque.obtenerCoordenadaX(),
                    bloque.obtenerCoordenadaY(),
                    20, 20
            );
        }
    }
    private void actualizarPantalla(GraphicsContext graphics){
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, ANCHO, ALTO);
        dibujarJugadores(graphics);
        dibujarDisparos(graphics);
        dibujarBloques(graphics);
    }
}
