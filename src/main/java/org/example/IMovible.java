package org.example;

public interface IMovible {
    Vector2D posicion ();
    Direccion direccion ();
    double velocidad ();
    void aplicarDesplazamiento();
}
