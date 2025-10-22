package org.vista;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HudView {
    private BorderPane layout;
    private final MediaPlayer mediaPlayer;
    private final int  height;
    private Label vidasLabel;

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
        ToggleButton muteButton = getMuteButton();

        vidasLabel = new Label();
        vidasLabel.setFont(Font.font("System", 16));
        vidasLabel.setTextFill(Color.WHITE);
        layout.setLeft(vidasLabel);
        layout.setRight(muteButton);
    }

    private @NotNull ToggleButton getMuteButton() {
        ToggleButton muteButton = new ToggleButton("Silenciar 🔇");
        muteButton.setStyle("-fx-font-size: 12px; -fx-padding: 5; -fx-background-radius: 10;" +
                "-fx-text-fill: white;-fx-background-color: transparent;");
        /*daba problemas al iniciar, el boton aparecia enfocado y no permitia otras acciones*/
        muteButton.setFocusTraversable(false);
        if (this.mediaPlayer != null) {
            muteButton.setSelected(mediaPlayer.isMute());
            muteButton.setOnAction(_ -> mediaPlayer.setMute(muteButton.isSelected()));
        }
        return muteButton;
    }

    public void actualizarStats(List<Integer> vidas){
        StringBuilder statsTexto = new StringBuilder();

        if (!vidas.isEmpty()) {
            statsTexto.append("Jugador 1 Vidas: ").append(vidas.getFirst());
        }
        if (vidas.size() >= 2) {
            statsTexto.append("  |  Jugador 2 Vidas: ").append(vidas.get(1));
        }

        vidasLabel.setText(statsTexto.toString());
    }
    public Pane obtenerLayout() {
        return this.layout;
    }
    public int obtenerAlto(){
        return this.height;
    }
}
