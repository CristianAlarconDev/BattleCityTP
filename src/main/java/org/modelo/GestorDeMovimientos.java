package org.modelo;

import java.util.Iterator;
import java.util.List;

public class GestorDeMovimientos {
    private  EntornoFisico nivel;
     public GestorDeMovimientos(EntornoFisico nivel){
        this.nivel = nivel;
    }
    private boolean estaDentroDeLimites(AreaColisionable areaDePrueba) {
        double xCentro = areaDePrueba.obtenerCentroX();
        double yCentro = areaDePrueba.obtenerCentroY();
        int semilado = (int)areaDePrueba.obtenerSemilado();

        boolean dentroHorizontal = (xCentro - semilado >= 0) && (xCentro + semilado <= nivel.obtenerAncho());
        boolean dentroVertical   = (yCentro - semilado >= 0) && (yCentro + semilado <= nivel.obtenerAlto());

        return dentroHorizontal && dentroVertical;
    }
    private boolean hayColisionConObstaculo(AreaColisionable areaMovimiento) {
        for (Obstruible obstruccion : nivel.obtenerObstrucciones()) {
            if (areaMovimiento.estaEnArea(obstruccion.obtenerAreaColisionable())) {
                return true;
            }
        }
        return false;
    }
    public boolean puedeMoverA(AreaColisionable areaMovimiento) {
        return estaDentroDeLimites(areaMovimiento) && !hayColisionConObstaculo(areaMovimiento);
    }
    public void limpiarDisparosFueraDeLimites() {
        List<Disparo> disparos = nivel.obtenerDisparos();
        Iterator<Disparo> iterador = disparos.iterator();
        while (iterador.hasNext()) {
            Disparo disparo = iterador.next();
            AreaColisionable areaDisparo = disparo.obtenerAreaColisionable();
            if (!estaDentroDeLimites(areaDisparo)) {
                disparo.desactivar();
                iterador.remove();
            }
        }
    }
}
