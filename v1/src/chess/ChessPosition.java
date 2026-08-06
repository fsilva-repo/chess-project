package chess;

import exceptions.ChessException;

public class ChessPosition {
 private int row;
 private char column;

 public ChessPosition(int row, char column) {
  String msg = "Erro ao criar uma posição: valores validos entre a1 ate h8";
  if (column < 'a' || column > 'h' || row < 1 || row > 8) 
   throw new ChessException(msg); 
  this.row = row;
  this.column = column;
 }

 public int getRow() {
  return row;
 }

 public char getColumn() {
  return column;
 }
 

}
