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
    }
    private void mostrarMenu()
    {
        Scene menu = MenuView.create(this::mostrarLobby);
        stage.setScene(menu);
    }
    private void mostrarLobby()
    {
        Scene lobby = LobbyView.create(this::iniciarPartida,
            this::mostrarMenu);
        stage.setScene(lobby);
    }

    private void iniciarPartida(int cantidadJugadores){
        JuegoController controller = new JuegoController(cantidadJugadores);
        Scene tablero = new TableroView(controller, this::mostrarMenu, stage::setScene).crearTableroView();

        stage.setScene(tablero);
    }

    public static void main(String[] args) {
        launch(args);
    }
}