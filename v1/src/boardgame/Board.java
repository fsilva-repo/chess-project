package boardgame;

public class Board {
 private int rows;
 private int columns;
 private Piece[][] pieces;
 public Board(int rows, int columns) {
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

 public Piece[][] getPieces() {
  return pieces;
 }
 
 public Piece getPiece(int row, int col) {
  return pieces[row][col];
 }

 public Piece getPiecePosition(Position p) {
  return pieces[p.getRow()][p.getColumn()];
 }

 /*
  O metodo recebe uma peça e uma posisão e sera repassado
  para a matriz, preenchendo com a peça e sua posição no tabuleiro
 */
 public void placePiece(Piece piece, Position position) {
  this.pieces[position.getRow()][position.getColumn()] = piece;
  // a peça não tera mais o valor null, recebera a posição obtida no parametro do metodo
  piece.position = position;
 }
}
