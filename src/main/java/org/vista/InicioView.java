package org.vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Objects;

public class InicioView {

    public static Scene crear(Runnable inicio){
        Image imagenInicio= new Image(InicioView.class.
                getResource("/sprites/logo.png").toExternalForm());
        ImageView imagenView=new ImageView(imagenInicio);
        imagenView.setFitWidth(250);
        imagenView.setPreserveRatio(true);

        Text presionarEnter= new Text("Presiona  ENTER para comenzar");
        presionarEnter.setFill(Color.WHITE);
        BorderPane root = new BorderPane();
        root.setCenter(imagenView);
        root.setBottom(presionarEnter);
        BorderPane.setAlignment(presionarEnter, Pos.CENTER);
        Scene scene = new Scene(root, 800, 600, Color.BLACK);

        scene.setOnKeyPressed(evento-> {
            if (Objects.requireNonNull(evento.getCode()) == KeyCode.ENTER) {
                inicio.run();
            }

        });
        return scene;
    }




}
