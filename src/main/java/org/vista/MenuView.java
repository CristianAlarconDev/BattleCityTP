package org.vista;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class MenuView {

    public Scene create(Runnable onPlay){
        Label titulo = new Label("Yet Another Battle City");
        titulo.setFont(Font.font("System", 28));
        titulo.setStyle("-fx-text-fill: #ff0000");

        Button jugarBtn = new Button("Jugar");
        Button salirBtn = new Button("Salir");

        jugarBtn.setPrefWidth(220);
        salirBtn.setPrefWidth(220);

        jugarBtn.setOnAction(e -> {
            onPlay.run();
        });
        salirBtn.setOnAction(e -> {
            Platform.exit();
        });

        VBox root = new VBox(titulo, jugarBtn, salirBtn);
        root.setAlignment(Pos.CENTER);

        jugarBtn.setStyle("-fx-background-radius: 12");
        salirBtn.setStyle("-fx-background-radius: 12");

        return new Scene(root, 800, 600);
    }
}
