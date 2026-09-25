import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=================================");
        System.out.println("       JUEGO DEL TRES EN RAYA    ");
        System.out.println("=================================");

        boolean seguirJugando = true;

        try {
            while (seguirJugando) {
                Partida partida = new Partida(3);

                while (!partida.terminada()) {
                    mostrarTableroConCoordenadas(partida.getTablero());
                    System.out.println("\nTurno del jugador: " + partida.getTurno());

                    int fila = pedirEntero(scanner, "Introduce la fila (0, 1, 2): ", 0, 2);
                    int col = pedirEntero(scanner, "Introduce la columna (0, 1, 2): ", 0, 2);

                    try {
                        boolean jugadaExitosa = partida.jugar(fila, col);
                        if (!jugadaExitosa) {
                            System.out.println("\n[!] Casilla no valida o ya ocupada. Por favor, elige otra.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("\n[!] Error en la jugada: " + e.getMessage());
                    }
                }

                // Fin de la partida
                System.out.println("\n=================================");
                System.out.println("        RESULTADO FINAL          ");
                System.out.println("=================================");
                mostrarTableroConCoordenadas(partida.getTablero());

                Ficha ganador = partida.ganador();
                if (ganador != null) {
                    System.out.println("\n*** ¡FELICIDADES! Ha ganado el jugador: " + ganador + " ***");
                } else {
                    System.out.println("\n*** ¡PARTIDA EN TABLAS! Ha sido un empate. ***");
                }

                // Preguntar si desea volver a jugar
                seguirJugando = pedirConfirmacion(scanner, "\n¿Deseas jugar otra partida? (s/n): ");
                System.out.println();
            }

            System.out.println("¡Gracias por jugar al Tres en Raya! Hasta la proxima.");
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("\n\n[!] Se ha cerrado o interrumpido la entrada de datos. Fin de la partida.");
        } finally {
            scanner.close();
        }
    }

    /**
     * Muestra el tablero enriquecido con números de fila y columna para guiar al usuario por consola.
     */
    private static void mostrarTableroConCoordenadas(Tablero tablero) {
        int dim = tablero.getDimension();
        System.out.print("\n    ");
        for (int j = 0; j < dim; j++) {
            System.out.print(j + "   ");
        }
        System.out.println();

        for (int i = 0; i < dim; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < dim; j++) {
                Ficha f = tablero.getCasilla(i, j);
                String valor = (f == null) ? "_" : f.toString();
                System.out.print(valor);
                if (j < dim - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println(" |");
        }
    }

    /**
     * Pide un número entero al usuario garantizando mediante try-catch que no se ingresen
     * caracteres no válidos, letras, cadenas vacías o números fuera del rango [min, max].
     */
    private static int pedirEntero(Scanner scanner, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            String linea = "";
            try {
                linea = scanner.nextLine().trim();
                if (linea.isEmpty()) {
                    System.out.println("[!] No has introducido ningun valor. Debes introducir un numero entre " + min + " y " + max + ".");
                    continue;
                }
                int valor = Integer.parseInt(linea);
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("[!] Rango no valido: el valor debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("[!] Entrada invalida: '" + linea + "' no es un numero entero valido. Intentalo de nuevo.");
            } catch (NoSuchElementException | IllegalStateException e) {
                throw e; // Se propaga para ser manejado en el flujo general de main
            } catch (Exception e) {
                System.out.println("[!] Ha ocurrido un error inesperado al leer la entrada: " + e.getMessage());
            }
        }
    }

    /**
     * Solicita una confirmación (sí o no) al usuario controlando con try-catch
     * que solo se acepten opciones válidas y evitando caracteres incorrectos.
     */
    private static boolean pedirConfirmacion(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                String linea = scanner.nextLine().trim().toLowerCase();
                if (linea.equals("s") || linea.equals("si") || linea.equals("sí") || linea.equals("y") || linea.equals("yes")) {
                    return true;
                } else if (linea.equals("n") || linea.equals("no")) {
                    return false;
                } else {
                    System.out.println("[!] Entrada no valida. Por favor, introduce 's' para SI o 'n' para NO.");
                }
            } catch (NoSuchElementException | IllegalStateException e) {
                throw e; // Se propaga para ser manejado en el flujo general de main
            } catch (Exception e) {
                System.out.println("[!] Error al procesar la respuesta: " + e.getMessage());
            }
        }
    }
}
