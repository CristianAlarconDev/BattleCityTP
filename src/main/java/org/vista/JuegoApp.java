package org.vista;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlador.JuegoController;

public class JuegoApp extends Application {
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Yet Another Battle City");
        mostrarMenu();
        stage.show();


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

        JuegoController controller = new JuegoController(cantidadJugadores);
        Scene tablero = TableroView.crearTableroView(controller);
        stage.setScene(tablero);
    }

    public static void main(String[] args) {
        launch(args);
    }
}