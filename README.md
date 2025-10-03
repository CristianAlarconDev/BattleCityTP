Universidad de Buenos Aires
Facultad de Ingeniería (FIUBA)
Carrera: Ingenieria Informatica
Materia: Paradigmas de Programación (TB025)

---

Docentes
Diego Essaya
Santiago Maraggi

Docente corrector: Alberto Alejandro Carmona

---

Integrantes del Grupo "Instanciados"
Alarcon Cristian - Padrón: 101803
Martinez Juan Ignacio - Padrón: 94785

---

Proyecto: Yet Another Battle City
Este proyecto consiste en el desarrollo de una versión del videojuego Battle City, utilizando Java y JavaFX.

El trabajo tiene como objetivo principal fortalecer conceptos de Programación Orientada a Objetos (POO), tales como:
Herencia y polimorfismo
Principios de diseño y buenas prácticas
Separación en capas (modelo y vista)
Programación orientada a eventos


---

Instrucciones de Ejecución
Clonar el repositorio:```bash
git clone https://github.com/paradigmas-tb025-essaya/tp1-instanciados
``
2- Abrir el proyecto con intellij
3-dirigirse al simbolo de Maven que se encuentra situado arriba a la derecha de la pantalla -> plugins -> JavaFX -> JavaFX.run

---

## Instrucciones de Juego

Uno o dos jugadores controlan su propio tanque cuyo objetivo es defender la base (águila) ubicada en el mapa, mientras destruye todos los tanques enemigos.

El nivel se completa al derrotar a todos los enemigos.
El juego termina en victoria si se completan los 3 niveles.
El juego termina en derrota si:
La base es destruida (un solo disparo).
Todos los jugadores pierden todas sus vidas.

### Controles

- **Jugador 1:**
    - ↑ (W) → Mover arriba
    - ↓ (S) → Mover abajo
    - ← (A) → Mover izquierda
    - → (D) → Mover derecha
    - Espacio → Disparar

- **Jugador 2:**
    - ↑ → Mover arriba
    - ↓ → Mover abajo
    - ← → Mover izquierda
    - → → Mover derecha
    - Enter` → Disparar