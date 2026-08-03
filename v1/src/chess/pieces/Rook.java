package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

 public Rook(Board board, Color color) {
  super(board, color);
 }

 /*
  Ao chamar a instancia dessa classe para ser impressa no tabuleiro
  ela sera representada pela letra 'R'
 */
 @Override
 public String toString() {
  return "R";
 }

}
