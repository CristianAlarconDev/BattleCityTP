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
}
