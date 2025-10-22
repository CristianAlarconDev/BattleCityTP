package org.modelo;

import java.util.List;

public class GestorDeMovimientos {
    private final EntornoFisico nivel;
    private final ReglasDeMovimiento reglasDeMovimiento;

    public GestorDeMovimientos(EntornoFisico nivel, ReglasDeMovimiento reglasDeMovimiento) {
        this.nivel = nivel;
        this.reglasDeMovimiento = reglasDeMovimiento;
    }

    private boolean estaDentroDeLimites(AreaColisionable areaDePrueba) {
        double xCentro = areaDePrueba.obtenerCentroX();
        double yCentro = areaDePrueba.obtenerCentroY();
        double semilado = areaDePrueba.obtenerSemilado();

        boolean dentroHorizontal = (xCentro - semilado >= 0) && (xCentro + semilado <= nivel.obtenerAncho());
        boolean dentroVertical = (yCentro - semilado >= 0) && (yCentro + semilado <= nivel.obtenerAlto());

        return dentroHorizontal && dentroVertical;
    }

    private boolean hayColisionConObstaculo(Tanque tanque, AreaColisionable areaMovimiento) {
        for (Obstruible obstruccion : nivel.obtenerObstrucciones()) {
            if (obstruccion == tanque) continue;
            if (areaMovimiento.estaEnArea(obstruccion.obtenerAreaColisionable())) {
                return true;
            }
        }
        return false;
    }

    public boolean puedeMoverA(Tanque tanque, AreaColisionable areaMovimiento) {
        return estaDentroDeLimites(areaMovimiento) && !hayColisionConObstaculo(tanque,areaMovimiento);
    }

    public void limpiarDisparosFueraDeLimites() {
        List<Disparo> disparos = nivel.obtenerDisparos();
        for (Disparo d : disparos) {
            if (!estaDentroDeLimites(d.obtenerAreaColisionable())) {
                reglasDeMovimiento.eliminarDisparo(d);
            }
        }
    }
}
