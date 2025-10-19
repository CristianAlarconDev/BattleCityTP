package org.modelo;

import java.util.List;

public interface EntornoFisico {
    double obtenerAlto();
    double obtenerAncho();
    List<Obstruible> obtenerObstrucciones();
    List<Disparo> obtenerDisparos();
}
