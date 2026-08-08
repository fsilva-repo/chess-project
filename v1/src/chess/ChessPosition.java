package chess;

import boardgame.Position;
import exceptions.ChessException;

public class ChessPosition {
 private char column;
 private int row;

 public ChessPosition(char column, int row) {
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

 protected Position toPosition() {
  return new Position(8 - row, column - 'a');
 }
 
 protected ChessPosition toChessPosition() {
  return new ChessPosition(column, row);
 }

 protected static ChessPosition fromPosition(Position position) {
  return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());
 }

 @Override
 public String toString() {
  return "" + column + row;
 }



}