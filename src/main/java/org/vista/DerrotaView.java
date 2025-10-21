package org.vista;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
public class DerrotaView {

    public static Scene create(Runnable onGoToMenu) {
        Label titulo = new Label("GAME OVER");
        titulo.setFont(Font.font("System", 32));
        titulo.setTextFill(Color.web("#ef4444"));

        Button menuBtn = new Button("Volver al Menú");

        menuBtn.setPrefWidth(220);

        menuBtn.setOnAction(e -> onGoToMenu.run());

        VBox root = new VBox(20, titulo, menuBtn);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 40; -fx-background-color: #000000; -fx-font-size: 16px;");

        menuBtn.setStyle("-fx-background-radius: 12;");

        return new Scene(root, 800, 600);
    }

}
