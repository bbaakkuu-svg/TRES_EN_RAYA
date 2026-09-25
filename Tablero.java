public class Tablero {

    private int dimension;
    private Ficha[][] casillas;

    public Tablero(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("La dimensión del tablero debe ser mayor que 0.");
        }
        this.dimension = dimension;
        casillas = new Ficha[dimension][dimension];
        inicializarTablero();
    }

    public int getDimension() {
        return dimension;
    }

    public Ficha getCasilla(int fila, int col) {
        if (fila >= 0 && fila < dimension && col >= 0 && col < dimension) {
            return casillas[fila][col];
        }
        return null;
    }

    private void inicializarTablero() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                casillas[i][j] = null;
            }
        }
    }

    /**
     * Intenta colocar una ficha en la posición indicada.
     * Retorna true si la jugada fue válida y se realizó, false en caso contrario.
     */
    public boolean jugar(Ficha ficha, int fila, int col) {
        if (ficha == null) {
            throw new IllegalArgumentException("La ficha a colocar no puede ser nula.");
        }
        if (posicionValida(fila, col)) {
            casillas[fila][col] = ficha;
            return true;
        }
        return false;
    }

    /**
     * Mantiene compatibilidad con versiones previas delegando en jugar.
     */
    public boolean colocarFicha(int fila, int col, Ficha ficha) {
        return jugar(ficha, fila, col);
    }

    public boolean posicionValida(int fila, int col) {
        return fila >= 0 && fila < dimension && col >= 0 && col < dimension && casillas[fila][col] == null;
    }

    /**
     * Verifica si el tablero está completamente lleno (no hay casillas vacías).
     */
    public boolean estaLleno() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (casillas[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Comprueba si la ficha indicada ha completado alguna fila horizontal.
     */
    public boolean comprobarFilas(Ficha ficha) {
        for (int i = 0; i < dimension; i++) {
            boolean filaCompleta = true;
            for (int j = 0; j < dimension; j++) {
                if (casillas[i][j] != ficha) {
                    filaCompleta = false;
                    break;
                }
            }
            if (filaCompleta) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si la ficha indicada ha completado alguna columna vertical.
     */
    public boolean comprobarColumnas(Ficha ficha) {
        for (int j = 0; j < dimension; j++) {
            boolean colCompleta = true;
            for (int i = 0; i < dimension; i++) {
                if (casillas[i][j] != ficha) {
                    colCompleta = false;
                    break;
                }
            }
            if (colCompleta) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si la ficha indicada ha completado la diagonal principal.
     */
    public boolean comprobarDiagonalPrincipal(Ficha ficha) {
        for (int i = 0; i < dimension; i++) {
            if (casillas[i][i] != ficha) {
                return false;
            }
        }
        return true;
    }

    /**
     * Comprueba si la ficha indicada ha completado la diagonal secundaria.
     */
    public boolean comprobarDiagonalSecundaria(Ficha ficha) {
        for (int i = 0; i < dimension; i++) {
            if (casillas[i][dimension - 1 - i] != ficha) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determina si la ficha indicada ha ganado la partida por cualquiera de las líneas posibles.
     */
    public boolean hayGanador(Ficha ficha) {
        return comprobarFilas(ficha)
                || comprobarColumnas(ficha)
                || comprobarDiagonalPrincipal(ficha)
                || comprobarDiagonalSecundaria(ficha);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (casillas[i][j] == null) {
                    sb.append("_");
                } else {
                    sb.append(casillas[i][j]);
                }
                if (j < dimension - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void mostrarTablero() {
        System.out.print(this.toString());
    }
}