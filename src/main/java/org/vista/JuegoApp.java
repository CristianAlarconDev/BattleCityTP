package org.vista;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlador.JuegoController;

public class JuegoApp extends Application {
    private Stage stage;
    private JuegoController juegoController;
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Yet Another Battle City");
        mostrarInicio();
        stage.show();
    }
    private void mostrarInicio(){
        Scene inicio= InicioView.crear(this::mostrarMenu);
        stage.setScene(inicio);
    }
    private void mostrarMenu()
    {
        Scene menu = MenuView.create(this::mostrarLobby);
        stage.setScene(menu);
    }
    private void mostrarLobby()
    {
        Scene lobby = LobbyView.create(this::iniciarJuegoNuevo,
            this::mostrarMenu);
        stage.setScene(lobby);
    }
    private void mostrarTablero() {
        TableroView tableroView = new TableroView(
                this.juegoController,
                this::mostrarNivelCompletado,
                this::mostrarJuegoCompletado,
                this::mostrarDerrota
        );
        Scene tablero = tableroView.crearTableroView();
        stage.setScene(tablero);
    }
    private void iniciarJuegoNuevo(int cantidadJugadores) {
        this.juegoController = new JuegoController(cantidadJugadores);
        mostrarTablero();
    }
    private void prepararSiguienteNivel() {
        juegoController.siguienteNivel();
        mostrarTablero();
    }
    private void mostrarNivelCompletado() {
        Scene scene = NivelCompletadoView.create(
                this::prepararSiguienteNivel,
                this::mostrarMenu
        );
        stage.setScene(scene);
    }
    private void mostrarJuegoCompletado() {
        Scene scene = JuegoCompletadoView.create(
                this::mostrarMenu
        );
        stage.setScene(scene);
    }
    private void mostrarDerrota() {
        Scene scene = DerrotaView.create(
                this::mostrarMenu
        );
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}