# 🎮 TP1: YABC - Yet Another Battle City

Trabajo Práctico 1 de **Paradigmas de Programación - FIUBA**  
Aplicación de conceptos de **POO, Principios de Programación e Interfaces Gráficas (JavaFX).**

---

## 👥 Integrantes

- Juan Ignacio Martinez
- Cristian Alarcon

---

## 🎯 Objetivo

El jugador controla un tanque cuyo objetivo es **defender la base (águila)** ubicada en el mapa, mientras destruye todos los tanques enemigos.

- El nivel se completa al derrotar a todos los enemigos (incluyendo los que aparecen por *spawning*).
- El juego termina en **victoria** si se completan los 3 niveles.
- El juego termina en **derrota** si:
    - La base es destruida (un solo disparo).
    - Todos los jugadores pierden todas sus vidas.

---

## 👥 Jugabilidad

- Modo **individual** o **cooperativo** (2 jugadores).
- Movimiento en cuatro direcciones: arriba, abajo, izquierda, derecha.
- Cada tanque puede tener **un disparo activo** a la vez.
- Cada jugador inicia cada nivel con **3 vidas**.
- Si un disparo colisiona con otro, ambos se destruyen.
- Si un jugador muere, el otro puede continuar.

**Controles:**
- Jugador 1 → `WASD` para moverse, `ESPACIO` para disparar.
- Jugador 2 → Flechas del teclado para moverse, `ENTER` para disparar.

---



## 🔄 Flujo del juego

1. Pantalla de inicio → selección de partida.
2. Se inicia el **primer nivel**.
3. El juego continúa mientras al menos un jugador esté vivo.
    - Si ambos jugadores mueren → cartel de derrota + vuelta al inicio.
4. Al derrotar todos los enemigos → se pasa al siguiente nivel.
5. Tras completar el **tercer nivel** → cartel de victoria + regreso al inicio.

---