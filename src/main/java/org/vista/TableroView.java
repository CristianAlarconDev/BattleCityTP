package org.vista;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.controlador.InputController;
import org.controlador.JuegoController;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import org.modelo.*;

public class TableroView {
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    private static final int TAMANIO_JUGADOR = 20;
    private static final int TAMANIO_DISPARO = 6;
    private static final int TAMANIO_BLOQUE=20;
    private Image spriteLadrillo, spriteAcero, spriteBosque, spriteAgua, spriteBlanco;
    private Image spriteDisparo, spritePrimerJugador, spriteSegundoJugador;
    private final JuegoController juegoController;
    private final InputController inputController;

    public TableroView(JuegoController juegoController) {
        this.juegoController = juegoController;
        this.inputController = new InputController(juegoController);
    }
    private Image cargarImagen(String ruta){
        var url = getClass().getResource(ruta);
        if (url == null) {
            throw new IllegalArgumentException("No se pudo encontrar la imagen " + ruta);
        }
        return new Image(url.toExternalForm());
    }
    private void iniciarSprites(GraphicsContext graphics){
        graphics.setImageSmoothing(false);
        spriteLadrillo=this.cargarImagen("/sprites/BrickBlock20x20.png");
        spriteAcero=this.cargarImagen("/sprites/SteelBlock20x20.png");
        spriteBosque=this.cargarImagen("/sprites/Forest20x20.png");
        spriteAgua=this.cargarImagen("/sprites/Water20x20.png");
        spriteBlanco=this.cargarImagen("/sprites/WhiteBlock20x20.png");
        spriteDisparo=this.cargarImagen("/sprites/Shot.png");
        spritePrimerJugador=this.cargarImagen("/sprites/Player1Tank0_20x20.png");
        spriteSegundoJugador=this.cargarImagen("/sprites/Player2Tank0_20x20.png");
    }
    private Image obtenerSprite(Bloque bloque){
        if(bloque.esDestructible()){
            return spriteLadrillo;
        } else if (bloque.bloqueaDisparo()) {
            return spriteAcero;
        } else if (bloque.bloqueaPasoTanque()) {
            return spriteAgua;
        }
        else if (!bloque.esColisionable()){
            return spriteBosque;
        }
        return spriteBlanco;
    }


    public Scene crearTableroView(){
        Canvas canvas = new Canvas(ANCHO, ALTO);
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        Scene scene = new Scene(new StackPane(canvas), ANCHO, ALTO, Color.GRAY);
        iniciarSprites(graphics);
        scene.setOnKeyPressed(evento-> inputController.presionarTecla(evento.getCode()));
        scene.setOnKeyReleased(evento-> inputController.soltarTecla(evento.getCode()));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                /*probar si se puede disminuir la velocidad con long now
                * se procesa muy rapido y puede variar en otro SO*/
                inputController.procesarInputs();
                juegoController.actualizarJuego();
                actualizarPantalla(graphics);
            }
        };
        timer.start();
        return scene;
    }
    private void dibujarJugador(GraphicsContext graphicsContext,Jugador jugador, Image sprite){
        double x = jugador.obtenerCoordenadaX();
        double y = jugador.obtenerCoordenadaY();
        int ancho =TAMANIO_JUGADOR;
        int alto =TAMANIO_JUGADOR;
        double angulo=switch(jugador.obtenerDireccionActual()){
            case DERECHA -> 90;
            case IZQUIERDA -> 270;
            case ARRIBA -> 0;
            case ABAJO -> 180;
        };
        graphicsContext.save();
        graphicsContext.translate(x,y);
        graphicsContext.rotate(angulo);
        graphicsContext.drawImage(sprite,-ancho/2.0,-alto/2.0,ancho,alto);
        graphicsContext.restore();
    }
    private void dibujarJugadores(GraphicsContext graphicsContext){
        var jugadores = juegoController.obtenerJugadores();
        for(int nroJugador=0;nroJugador<jugadores.size();nroJugador++){
            Jugador jugador = jugadores.get(nroJugador);
            Image sprite = (nroJugador==0)? spritePrimerJugador:spriteSegundoJugador;
            dibujarJugador(graphicsContext,jugador,sprite);
        }
    }
    private void dibujarDisparos(GraphicsContext graphics){
        graphics.setFill(Color.YELLOW);
        for(Disparo disparo: juegoController.obtenerDisparos()){
            double xEsquina = disparo.obtenerCoordenadaX()-TAMANIO_DISPARO/2.0;
            double yEsquina = disparo.obtenerCoordenadaY()-TAMANIO_DISPARO/2.0;
            graphics.drawImage(spriteDisparo,xEsquina,yEsquina, TAMANIO_DISPARO, TAMANIO_DISPARO);

        }
    }
    private void dibujarBloques(GraphicsContext graphicsContext){
        for (Bloque bloque : juegoController.obtenerBloques()) {
            Image sprite = obtenerSprite(bloque);
            double xEsquina = bloque.obtenerCoordenadaX() - (sprite.getWidth() / 2.0);
            double yEsquina = bloque.obtenerCoordenadaY() - (sprite.getHeight() / 2.0);
            graphicsContext.drawImage(sprite,xEsquina,yEsquina,TAMANIO_BLOQUE,TAMANIO_BLOQUE);
        }
    }
    private void actualizarPantalla(GraphicsContext graphics){
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, ANCHO, ALTO);
        dibujarJugadores(graphics);
        dibujarDisparos(graphics);
        dibujarBloques(graphics);
    }
}
