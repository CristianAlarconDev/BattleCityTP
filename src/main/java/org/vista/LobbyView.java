package org.vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.function.IntConsumer;



public class LobbyView {
    public static Scene create(IntConsumer onStart, Runnable onBack){
        Label titulo= new Label("Configuracion de partida");
        titulo.setFont(Font.font("System", 24));
        titulo.setStyle("-fx-text-fill: #e2e8f0");

        Label labelJugadores = new Label("Numero de jugadores");
        labelJugadores.setFont(Font.font("System", 18));
        labelJugadores.setStyle("-fx-text-fill: #cbd5e1");
        ChoiceBox<Integer> jugadores = new ChoiceBox<>();
        jugadores.getItems().addAll(1,2);
        jugadores.setValue(1);

        VBox jugadoresBox = new VBox(10,labelJugadores, jugadores);
        jugadoresBox.setAlignment(Pos.CENTER);

        Button regresarBtn = new Button("Regresar");
        Button comenzarBtn = new Button("Comenzar");

        regresarBtn.setOnAction(e -> {
            onBack.run();
        });

        comenzarBtn.setOnAction(e -> {
            onStart.accept(jugadores.getValue());
        });

        HBox botonesBox = new HBox(10,regresarBtn,comenzarBtn);
        botonesBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(titulo,jugadoresBox,botonesBox);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 40; -fx-font-size: 16px;" +
                " -fx-background-color: #000000;");

        return new Scene(root, 800, 600);

    }



}
