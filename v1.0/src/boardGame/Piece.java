package boardGame;

public abstract class Piece {
  protected Position position;
  private Board board;
  
  public Piece(Board board) {
    this.position = null;
    this.board = board;
  }

  protected Board getBoard() {
    return board;
  }
  
  // metodo retorna uma matriz de valores booleanos
 // e neste caso valores falso pois a matriz vazia então recebe null.
  public abstract boolean [][] possibleMoves();

  // o metodo concreto que leva nome parecido ao metodo acima
  // retorna os valores verdadeiros 
  public boolean possibleMove(Position p) {
    return possibleMoves()[p.getRow()][p.getColumn()];
  }

  /*
   * o metodo verifica se existe alguma posição na matriz
   * que seja verdadeira
  */
  public boolean istThereAnyPossibleMove() {
    boolean[][] mat = possibleMoves();
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat.length; j++) {
        if (mat[i][j]) {
          return true;
        }
      }
    }
    return false;
  }

}
