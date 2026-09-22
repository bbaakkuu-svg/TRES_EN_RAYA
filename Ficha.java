public enum Ficha{

    X,
    O;

   /**
     * Devuelve la ficha del siguiente turno.
     */
    public Ficha siguiente() {
        if (this == X) {
            return O;
        } else {
            return X;
        }
    }
}