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

  public Piece pice(int row, int col) {
    return this.pieces[row][col];
  }
    public Piece pice(Position p) {
    return this.pieces[p.getRow()][p.getColumn()];
  }
}
