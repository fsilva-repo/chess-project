package boardGame;

import exception.BoardException;

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
    if (rows < 1 || columns < 1) {
      throw new BoardException("Erro tentando criar um tabuleiro, o mesmo não pode ter a linha ou coluna menores que 1");
    }
    this.rows = rows;
    this.columns = columns;
    this.pieces = new Piece[rows][columns];
  }

  public int getRows() {
    return rows;
  }
  
  public int getColumns() {
    return columns;
  }
  public Piece piece(int row, int col) {
    if (!validation(row, col))
      throw new BoardException("Essa posição não existe no tabuleiro");
    return pieces[row][col];
  }
  
  public Piece piece(Position p) {
    if (!positionExists(p))
      throw new BoardException("Essa posição não existe no tabuleiro");
    return pieces[p.getRow()][p.getColumn()];
  }

  /*
   * metodo recebe uma peça e uma posição e as define na matriz
   * de peças do tabuleiro
  */
  public void palcePiece(Piece piece, Position position) {
    if (thereIsAPiece(position))
      throw new BoardException("Já existe uma peça na posição " + position);
    pieces[position.getRow()][position.getColumn()] = piece;
    piece.position = position;
  }

  private boolean validation(int row, int column) {
    return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
  }
  
  public boolean positionExists(Position position) {
    return validation(position.getRow(), position.getColumn());
  }


  public boolean thereIsAPiece(Position position) {
    if (!positionExists(position))
      throw new BoardException("Essa posição não existe no tabuleiro");
    return piece(position) != null;
  }
}
