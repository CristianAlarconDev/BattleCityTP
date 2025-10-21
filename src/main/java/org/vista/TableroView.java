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
import java.util.HashMap;
import java.util.Map;

public  class TableroView {
    private final int ANCHO;
    private final int ALTO;
    private final int TAMANIO_JUGADOR;
    private final int TAMANIO_DISPARO;
    private final int TAMANIO_BLOQUE;
    private Image spriteLadrillo, spriteAcero, spriteBosque, spriteAgua, spriteBlanco,spriteBase;
    private Image spriteDisparo, spritePrimerJugador, spriteSegundoJugador, sprite2PrimerJugador,sprite2SegundoJugador;
    private Image spriteTanqueRegular, spriteTanqueRapido, spriteTanqueBlindado, spriteTanquePotente, spriteTanqueRegular2, spriteTanqueRapido2, spriteTanqueBlindado2, spriteTanquePotente2;
    private Image spritePowerUpHelmet, spritePowerUpStar,spritePowerUpGranada;
    private final JuegoController juegoController;
    private final InputController inputController;

    private final Runnable onDerrota, onNivelCompletado, onJuegoCompletado;
    private Image[] spritesJugador1, spritesJugador2, spritesTanqueRegular, spritesTanqueRapido,  spritesTanqueBlindado, spritesTanquePotente;
    private final Map<Enemigo, Integer> frameEnemigos = new HashMap<>();
    private final Map<Jugador, Integer> frameJugadores = new HashMap<>();

    public TableroView(JuegoController juegoController, Runnable
            onNivelCompletado, Runnable onJuegoCompletado,
                       Runnable onDerrota) {
        this.juegoController = juegoController;
        this.inputController = new InputController(juegoController);
        this.onNivelCompletado = onNivelCompletado;
        this.onJuegoCompletado = onJuegoCompletado;
        this.onDerrota = onDerrota;
        ANCHO = 800;
        ALTO = 600;
        TAMANIO_JUGADOR = 20;
        TAMANIO_DISPARO = 6;
        TAMANIO_BLOQUE=20;
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
        spriteBase=this.cargarImagen("/sprites/Base20x20.png");
        spriteDisparo=this.cargarImagen("/sprites/Shot.png");
        spritePrimerJugador=this.cargarImagen("/sprites/Player1Tank0_20x20.png");
        sprite2PrimerJugador=this.cargarImagen("/sprites/Player1Tank1_20x20.png");
        spriteSegundoJugador=this.cargarImagen("/sprites/Player2Tank0_20x20.png");
        sprite2SegundoJugador=this.cargarImagen("/sprites/Player2Tank1_20x20.png");
        spritePowerUpHelmet=this.cargarImagen("/sprites/PowerUp-Helmet20x20.png");
        spritePowerUpStar=this.cargarImagen("/sprites/PowerUp-Star20x20.png");
        spritePowerUpGranada=this.cargarImagen("/sprites/PowerUp-Grenade20x20.png");
        spriteTanqueRapido= this.cargarImagen("/sprites/EnemyTankFast0_20x20.png");
        spriteTanqueRapido2= this.cargarImagen("/sprites/EnemyTankFast1_20x20.png");
        spriteTanqueBlindado= this.cargarImagen("/sprites/EnemyTankHeavy0_20x20.png");
        spriteTanqueBlindado2= this.cargarImagen("/sprites/EnemyTankHeavy1_20x20.png");
        spriteTanqueRegular=this.cargarImagen("/sprites/EnemyTankRegular0_20x20.png");
        spriteTanqueRegular2=this.cargarImagen("/sprites/EnemyTankRegular1_20x20.png");
        spriteTanquePotente=this.cargarImagen("/sprites/EnemyTankPowerful0_20x20.png");
        spriteTanquePotente2=this.cargarImagen("/sprites/EnemyTankPowerful1_20x20.png");

        spritesJugador1 = new Image[] {
                spritePrimerJugador,
                sprite2PrimerJugador
        };
        spritesJugador2 = new Image[] {
                spriteSegundoJugador,
                sprite2SegundoJugador
        };
        spritesTanqueRegular = new Image[] {
                spriteTanqueRegular,
                spriteTanqueRegular2
        };
        spritesTanqueRapido = new Image[] {
                spriteTanqueRapido,
                spriteTanqueRapido2
        };
        spritesTanqueBlindado = new Image[] {
                spriteTanqueBlindado,
                spriteTanqueBlindado2
        };
        spritesTanquePotente = new Image[] {
                spriteTanquePotente,
                spriteTanquePotente2
        };

    }
    private Image obtenerSprite(Bloque bloque){
        return switch (bloque.obtenerTipo()) {
            case LADRILLO -> spriteLadrillo;
            case ACERO    -> spriteAcero;
            case AGUA     -> spriteAgua;
            case BOSQUE   -> spriteBosque;
            case BASE     -> spriteBase;
            default -> spriteBlanco;
        };
    }

    private Image obtenerSprite(PowerUp powerUp){
        return switch (powerUp.obtenerTipoPowerUp()) {
            case CASCO -> spritePowerUpHelmet;
            case ESTRELLA -> spritePowerUpStar;
            case GRANADA -> spritePowerUpGranada;

        };
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
                inputController.procesarInputs();
                juegoController.actualizarJuego();
                actualizarPantalla(graphics);
                if (juegoController.terminoEnDerrota()) {
                    stop();
                    onDerrota.run();

                } else if (juegoController.terminoNivelEnVictoria()) {
                    stop();
                    onNivelCompletado.run();

                }
                else if(juegoController.terminoJuegoEnVictoria()){
                    stop();
                    onJuegoCompletado.run();
                }
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
    private void dibujarEnemigo(GraphicsContext graphicsContext,Enemigo enemigo, Image sprite){
        double x = enemigo.obtenerCoordenadaX();
        double y = enemigo.obtenerCoordenadaY();
        int ancho =TAMANIO_JUGADOR;
        int alto =TAMANIO_JUGADOR;
        double angulo=switch(enemigo.obtenerDireccionActual()){
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
        for(int nroJugador=0; nroJugador<jugadores.size(); nroJugador++){
            Jugador jugador = jugadores.get(nroJugador);


            int frame = frameJugadores.getOrDefault(jugador, 0);


            if(juegoController.tanqueEnMovimiento(jugador)){
                frame = (frame + 1) % 2;
                frameJugadores.put(jugador, frame);
            }

            Image sprite;
            if(nroJugador == 0){
                sprite = spritesJugador1[frame];
            } else {
                sprite = spritesJugador2[frame];
            }

            dibujarJugador(graphicsContext, jugador, sprite);
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
            double xEsquina = bloque.obtenerCoordenadaX() - (TAMANIO_BLOQUE / 2.0);
            double yEsquina = bloque.obtenerCoordenadaY() - (TAMANIO_BLOQUE / 2.0);
            graphicsContext.drawImage(sprite,xEsquina,yEsquina,TAMANIO_BLOQUE,TAMANIO_BLOQUE);
        }
    }
    private void dibujarPowerUp(GraphicsContext graphicsContext){
        for(PowerUp powerUp: juegoController.obtenerPowerUps()){
            Image sprite = obtenerSprite(powerUp);
            double xEsquina = powerUp.obtenerCoordenadaX()-TAMANIO_BLOQUE/2.0;
            double yEsquina = powerUp.obtenerCoordenadaY()-TAMANIO_BLOQUE/2.0;
            graphicsContext.drawImage(sprite,xEsquina,yEsquina,TAMANIO_BLOQUE,TAMANIO_BLOQUE);
        }
    }

    private void dibujarEnemigos(GraphicsContext graphicsContext){
        for(Enemigo enemigo: juegoController.obtenerEnemigos()){

            int frame = frameEnemigos.getOrDefault(enemigo, 0);
            if (juegoController.tanqueEnMovimiento(enemigo)) {
                frame = (frame + 1) % 2;
                frameEnemigos.put(enemigo, frame);
            }

            Image sprite;
            switch (enemigo.obtenerTipo()) {
                case REGULARENEMY -> sprite = spritesTanqueRegular[frame];
                case FASTENEMY    -> sprite = spritesTanqueRapido[frame];
                case HEAVYENEMY   -> sprite = spritesTanqueBlindado[frame];
                case POWERFULENEMY-> sprite = spritesTanquePotente[frame];
                default -> sprite = spriteTanqueRegular;
            }

            dibujarEnemigo(graphicsContext, enemigo, sprite);
        }
    }

    private void actualizarPantalla(GraphicsContext graphics){
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, ANCHO, ALTO);
        dibujarBloques(graphics);
        dibujarJugadores(graphics);
        dibujarEnemigos(graphics);
        dibujarDisparos(graphics);
        dibujarPowerUp(graphics);
    }
}
