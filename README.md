# Tres en Raya en Java (Modo Terminal)

Juego clásico del Tres en Raya implementado en Java siguiendo los principios de la Programación Orientada a Objetos (POO).

## Estructura del Proyecto

* **`Ficha.java`**: Tipo enumerado (`enum`) con los valores `X` y `O`, y el método `siguiente()` para la alternancia de turnos.
* **`Tablero.java`**: Modela la matriz del tablero, validación de casillas libres, colocación de fichas, verificación de tablero lleno y comprobación de victorias (filas, columnas y diagonales).
* **`Partida.java`**: Orquesta el flujo del juego, controlando los turnos de los jugadores, verificando si la partida ha finalizado, determinando el ganador y permitiendo reiniciar.
* **`Main.java`**: Interfaz de usuario interactiva por consola/terminal con validación de entradas numéricas, visualización con coordenadas y repetición de partidas.

## Cómo compilar y jugar

### 1. Compilar el proyecto en la carpeta `bin/`
Abre tu terminal en la carpeta del repositorio y ejecuta:
```bash
javac -d bin *.java
```

### 2. Ejecutar el juego
```bash
java -cp bin Main
```