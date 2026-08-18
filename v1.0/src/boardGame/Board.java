package boardGame;
// o tabuleiro contem apenas uma
// matriz de peças e os atributos
// rows e columns que dira qual sera o
// tamanho dessa matriz
public class Board {
  private int rows;
  private int columns;

  // uma matriz de tipo Piece
  // o tamanho do tabuleiro sera o tamanho
  // dessa matriz de peças
  private Piece[][] pieces;
  public Board(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    this.pieces = new Piece[rows][columns];
  }

  public int getRows() {
    return rows;
  }
  public void setRows(int rows) {
    this.rows = rows;
  }
  public int getColumns() {
    return columns;
  }
  public void setColumns(int columns) {
    this.columns = columns;
  }

  public Piece piece(int row, int col) {
    return pieces[row][col];
  }
  
  public Piece piece(Position p) {
    return pieces[p.getRow()][p.getColumn()];
  }

  /*
   * metodo recebe uma peça e uma posição e as define na matriz
   * de peças do tabuleiro
  */
  public void palcePiece(Piece piece, Position position) {
    pieces[position.getRow()][position.getColumn()] = piece;
    piece.position = position;
  }
}
