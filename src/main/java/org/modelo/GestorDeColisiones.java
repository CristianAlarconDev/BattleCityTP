package org.modelo;

import java.util.List;

public class GestorDeColisiones {
    private final ContextoDeColision nivel;
    private final ReglasDeNivel reglasDeNivel;
    public GestorDeColisiones(ContextoDeColision nivel, ReglasDeNivel reglasDeNivel){
        this.nivel = nivel;
        this.reglasDeNivel = reglasDeNivel;
    }
    private void manejarColision(ResultadoImpacto resultadoImpacto, Colisionable colisionable) {
        switch (resultadoImpacto) {
            case JUGADOR_ELIMINADO, DESTRUIDO -> nivel.eliminarColisionable(colisionable);
            case ENEMIGO_ELIMINADO -> {
                nivel.agregarBloqueTanqueDestruido(colisionable.obtenerPosicion());
                nivel.eliminarColisionable(colisionable);
                reglasDeNivel.intentarGenerarPowerUp();
            }
        }
    }
    private boolean baseDestruida(ResultadoImpacto resultadoImpacto){
        return resultadoImpacto==ResultadoImpacto.BASE_DESTRUIDA;
    }
    public void comprobarColisiones (){
        List<Colisionable> colisionables= nivel.obtenerColisionables();
        List<Disparo> disparos = nivel.obtenerDisparos();

        for (Disparo disparo : disparos) {
            for (Colisionable colisionable : colisionables) {
                if (disparo.impactaA(colisionable)) {
                    ResultadoImpacto resultado = colisionable.recibirImpacto(disparo);
                    if (baseDestruida(resultado)) {
                        nivel.eliminarDisparo(disparo);
                        this.reglasDeNivel.finalizarNivel();
                        return;
                    }
                    manejarColision(resultado, colisionable);
                    nivel.eliminarDisparo(disparo);
                    break;
                }
            }
        }

    }
    public void comprobarColisionesJugadorPowerUp() {
        List<Jugador> jugadores = nivel.obtenerJugadores();

        List<PowerUp> powerUps = nivel.obtenerPowerUps();

        for (Jugador jugador : jugadores) {
            AreaColisionable areaJugador = jugador.obtenerAreaColisionable();

            for (PowerUp powerUp : powerUps) {
                if (areaJugador.estaEnArea(powerUp.obtenerAreaColisionable())) {
                    if (powerUp.esGranada()) {
                        reglasDeNivel.activarEfectoGranada();
                    } else {
                        powerUp.aplicarEfecto(jugador);
                    }

                    System.out.println("PowerUp " + powerUp.obtenerTipoPowerUp() +
                            " consumido por " + jugador.obtenerNombre());
                    nivel.eliminarPowerUp(powerUp);
                    break;
                }
            }
        }
    }
}
