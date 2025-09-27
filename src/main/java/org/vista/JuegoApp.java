package org.vista;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JuegoApp extends Application {
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Yet Another Battle City");
        mostrarMenu();
        stage.show();
        /*
        Canvas canvas = new Canvas(COLUMNAS * CELDA, FILAS * CELDA);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        dibujarTablero(gc);

       StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Yet Another Battle City");
       stage.setScene(scene);
        stage.show();*/
        //prueba de inputs
        /*
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W ->System.out.println("W");
                case A -> System.out.println("A");
                case S -> System.out.println("S");
                case D -> System.out.println("D");
                default -> System.out.println("No se ha pulsado ninguna tecla");
            }
        });
        */
    }
    private void mostrarMenu()
    {
        Scene menu = MenuView.create(this::mostrarLobby);
        stage.setScene(menu);
    }
    private void mostrarLobby()
    {
        Scene lobby = LobbyView.create(this::inciarPartida,
            this::mostrarMenu);
        stage.setScene(lobby);


    }

    private void inciarPartida(int cantidadJugadores){
        System.out.println("Iniciando partida con " + cantidadJugadores + " jugadores");
        mostrarMenu();
    }
    /*
    private void dibujarTablero(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGRAY);
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                gc.fillRect(columna * CELDA, fila * CELDA, CELDA, CELDA);
            }
        }
    }
*/
    public static void main(String[] args) {
        launch(args);
    }
}