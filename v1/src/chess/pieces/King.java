package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

 public King(Board board, Color color) {
  super(board, color);
 }

 /*
  Ao chamar a instancia dessa classe para ser impressa no tabuleiro
  ela sera representada pela letra 'K'
 */
 @Override
 public String toString() {
  return "K";
 }

}
