public class Tablero{

private int dimension;
private Ficha[][] casillas;

public Tablero(int dimension){
    this.dimension=dimension;
    casillas=new Ficha[dimension][dimension];
    inicializarTablero();
}

public int getDimension(){
    return dimension;
}


private void inicializarTablero(){
    for(int i = 0; i < dimension; i++){
        for(int j = 0; j < dimension; j++){
            casillas[i][j] = null;
        }
    }
}


public boolean colocarFicha(int fila, int col, Ficha ficha){
    if (posicionValida(fila, col)) {
        casillas[fila][col] = ficha;
        return true;
    }
    return false;
}

private boolean posicionValida(int fila, int col){
    return fila >= 0 && fila < dimension && col >= 0 && col < dimension && casillas[fila][col] == null;
}

public void mostrarTablero() {
   
    for(int i = 0; i < dimension; i++){
        for(int j = 0; j < dimension; j++){
            if(casillas[i][j] == null){
                System.out.print("_");
            }else{
                System.out.print(casillas[i][j]+ " ");
            }
        }
        System.out.println();
    }
}

}