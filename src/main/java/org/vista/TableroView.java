package org.vista;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.controlador.JuegoController;
import org.modelo.Direccion;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import org.modelo.Jugador;

public class TableroView {
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    private static final int CELDA = 20;

    public static Scene crearTableroView(JuegoController controller){
        Canvas canvas = new Canvas(ANCHO, ALTO);
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        /*intento dibujar jugador*/

        dibujarJugadores(graphics, controller);

        Scene scene = new Scene(new StackPane(canvas), ANCHO, ALTO, Color.GRAY);

        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, ANCHO, ALTO);
        dibujarJugadores(graphics, controller);

        scene.setOnKeyPressed(e->
                {
                    switch(e.getCode()) {
                        case W -> controller.moverJugador(0,Direccion.ARRIBA);
                        case A ->  controller.moverJugador(0,Direccion.IZQUIERDA);
                        case S -> controller.moverJugador(0,Direccion.ABAJO);
                        case D-> controller.moverJugador(0,Direccion.DERECHA);

                        case UP -> controller.moverJugador(1,Direccion.ARRIBA);
                        case LEFT ->  controller.moverJugador(1,Direccion.IZQUIERDA);
                        case DOWN -> controller.moverJugador(1,Direccion.ABAJO);
                        case RIGHT-> controller.moverJugador(1,Direccion.DERECHA);
                        default -> System.out.println("No se ha pulsado ninguna tecla valida");

                    }
                    graphics.setFill(Color.BLACK);
                    graphics.fillRect(0, 0, ANCHO, ALTO);
                    dibujarJugadores(graphics, controller);
                }
        );
        return scene;


    }
    private static void dibujarJugadores(GraphicsContext graphicsContext, JuegoController controller){
        int i=0;
        for (Jugador jugador: controller.obtenerJugadores()){
            graphicsContext.setFill(i == 0? Color.BLUE:Color.RED);
            graphicsContext.fillRect(jugador.obtenerPosicion().obtenerCoordenadaX(),
                    jugador.obtenerPosicion().obtenerCoordenadaY(), CELDA, CELDA);
            i++;
        }
    }
}
