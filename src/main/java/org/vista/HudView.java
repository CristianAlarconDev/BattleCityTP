package org.vista;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

public class HudView {
    private BorderPane layout;
    private final MediaPlayer mediaPlayer;
    public HudView(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        crearLayout();
    }
    private void crearLayout() {
        layout = new BorderPane();
        layout.setStyle("-fx-background-color: black;");
        // --- Botón de Mute ---
        ToggleButton muteButton = new ToggleButton("Silenciar 🔇");
        muteButton.setStyle("-fx-font-size: 12px; -fx-padding: 5; -fx-background-radius: 10;" +
                "-fx-text-fill: white;-fx-background-color: transparent;");

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
}
