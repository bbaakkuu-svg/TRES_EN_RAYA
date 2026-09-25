public class Partida {

    private Tablero tablero;
    private Ficha turno;

    public Partida(int dimension) {
        this.tablero = new Tablero(dimension);
        this.turno = Ficha.X;
    }

    public Partida() {
        this(3);
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Ficha getTurno() {
        return turno;
    }

    /**
     * Intenta realizar una jugada en la posición (fila, col) con la ficha del turno actual.
     * Si la partida ya ha terminado o la casilla no es válida, devuelve false.
     * Si la jugada es válida y la partida aún no termina, cambia al siguiente turno.
     */
    public boolean jugar(int fila, int col) {
        if (terminada()) {
            return false;
        }

        boolean jugadaExitosa = tablero.jugar(turno, fila, col);
        if (jugadaExitosa) {
            if (!terminada()) {
                turno = turno.siguiente();
            }
            return true;
        }
        return false;
    }

    /**
     * Determina el ganador de la partida.
     * Devuelve la Ficha ganadora (X u O), o null si no hay ganador o hay empate.
     */
    public Ficha ganador() {
        if (tablero.hayGanador(Ficha.X)) {
            return Ficha.X;
        }
        if (tablero.hayGanador(Ficha.O)) {
            return Ficha.O;
        }
        return null;
    }

    /**
     * Comprueba si la partida ha finalizado, bien porque hay un ganador o por empate (tablero lleno).
     */
    public boolean terminada() {
        return ganador() != null || tablero.estaLleno();
    }

    /**
     * Reinicia la partida conservando la misma dimensión y estableciendo el turno en X.
     */
    public void reiniciar() {
        this.tablero = new Tablero(tablero.getDimension());
        this.turno = Ficha.X;
    }
}
