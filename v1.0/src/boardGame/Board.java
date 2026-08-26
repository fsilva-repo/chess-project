package boardGame;

import exception.BoardException;

public class Board {
  private int rows;
  private int columns;

  // uma matriz do tipo Piece.
  // o tamanho do tabuleiro tera as dimensões
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
    * se nao existe a posicao lanca exception
    * se nao existe peca na posicao retorna null
    * se existe peca na posicao indicada
    * a matriz de pecas na posicao alvo recebe null
    * e o metodo retorna a peca com o valor null. 
  */
  public Piece removePiece(Position p) {
    if (!positionExists(p)) {
      throw new BoardException("Essa posição não existe no tabuleiro");
    }
    if (piece(p) == null) {
      return  null;
    }
    Piece aux = piece(p);
    pieces[p.getRow()][p.getColumn()] = null;
    return aux;
  }


  /*
   * metodo recebe uma peça e uma posição e as define na matriz
   * de peças do tabuleiro
  */
  public void placePiece(Piece piece, Position position) {
    if (thereIsAPiece(position))
      throw new BoardException("Já existe uma peça na posição " + position);
    pieces[position.getRow()][position.getColumn()] = piece;
    piece.position = position;
  }

  // verifica se a posição existe atavez dos valores da coluna e linha
  private boolean validation(int row, int column) {
    return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
  }
  
  // verifica se a posição existe atavez de uma Position 
  public boolean positionExists(Position position) {
    return validation(position.getRow(), position.getColumn());
  }

 // verifica se a posição exite e se contem alguma peça
  public boolean thereIsAPiece(Position position) {
    if (!positionExists(position))
      throw new BoardException("Essa posição não existe no tabuleiro");
    return piece(position) != null;
  }

}
