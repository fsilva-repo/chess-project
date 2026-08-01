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
}
