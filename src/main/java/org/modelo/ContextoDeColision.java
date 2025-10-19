package org.modelo;

import java.util.List;

public interface ContextoDeColision {
    List<Colisionable> obtenerColisionables();
    List<Disparo> obtenerDisparos();
}
