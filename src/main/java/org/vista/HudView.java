package org.vista;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

public class HudView {
    private BorderPane layout;
    private final MediaPlayer mediaPlayer;
    private int height;
    public HudView(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        this.height = 40;
        crearLayout();
    }
    private void crearLayout() {
        layout = new BorderPane();
        layout.setStyle("-fx-background-color: black;");
        /*el layout del hud quitaba alto del escenario de juego*/
        layout.setPrefHeight(this.height);
        // --- Botón de Mute ---
        ToggleButton muteButton = new ToggleButton("Silenciar 🔇");
        muteButton.setStyle("-fx-font-size: 12px; -fx-padding: 5; -fx-background-radius: 10;" +
                "-fx-text-fill: white;-fx-background-color: transparent;");
        /*daba problemas al iniciar, el boton aparecia enfocado y no permitia otras acciones*/
        muteButton.setFocusTraversable(false);
        if (this.mediaPlayer != null) {
            muteButton.setSelected(mediaPlayer.isMute());
            muteButton.setOnAction(_ -> mediaPlayer.setMute(muteButton.isSelected()));
        }

        // --- Stats (Placeholder para el futuro) ---

        layout.setRight(muteButton);
    }
    public Pane obtenerLayout() {
        return this.layout;
    }
    public int obtenerAlto(){
        return this.height;
    }
}
