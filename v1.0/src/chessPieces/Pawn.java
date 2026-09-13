package chessPieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

 public Pawn(Board board, Color color) {
  super(board, color);
 }


 @Override
 public String toString() {
  return "P";
 }

 
 @Override
 public boolean[][] possibleMoves() {
  boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

  Position p = new Position(0, 0);
  /* se a peça é de cor branca a logica sera a de decrementar
   * as posições da matriz para que a peça branca se mova para frente
  */
  if (getColor() == Color.WHITE) { // movimentos do peão branco
   // a peça anda uma casas para frente
   p.setValues(position.getRow() -1, position.getColumn());
   /* verifica se a posição existe é se esta vazia,
    * se positivo a matriz de booleans marca a posição como true
   */
   if (getBoard().positionExists(p) && ! getBoard().thereIsAPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
   }

  // a peça anda duas casas para frente
   p.setValues(position.getRow() -2, position.getColumn());
   Position p2 = new Position(position.getRow() -1, position.getColumn());
   if (getBoard().positionExists(p) && ! getBoard().thereIsAPiece(p)
       && getBoard().positionExists(p2) && ! getBoard().thereIsAPiece(p2)
   ) {
    mat[p.getRow()][p.getColumn()] = true;
   }
   
   // movimentação de captura na diagonal esquerda
   p.setValues(position.getRow() -1, position.getColumn() -1);
   if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
   }

   // movimentação de captura na diagonal direita
   p.setValues(position.getRow() -1, position.getColumn() +1);
   if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
   }

  }
  else { // movimentos do peão preto
// a peça anda uma casas para frente
   p.setValues(position.getRow() +1, position.getColumn());
   /* verifica se a posição existe é se esta vazia,
    * se positivo a matriz de booleans marca a posição como true
   */
   if (getBoard().positionExists(p) && ! getBoard().thereIsAPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
   }

  // a peça anda duas casas para frente
   p.setValues(position.getRow() +2, position.getColumn());
   Position p2 = new Position(position.getRow() +1, position.getColumn());
   if (getBoard().positionExists(p) && ! getBoard().thereIsAPiece(p)
       && getBoard().positionExists(p2) && ! getBoard().thereIsAPiece(p2)
   ) {
    mat[p.getRow()][p.getColumn()] = true;
   }
   
   // movimentação de captura na diagonal esquerda
   p.setValues(position.getRow() +1, position.getColumn() -1);
   if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
   }

   // movimentação de captura na diagonal direita
   p.setValues(position.getRow() +1, position.getColumn() +1);
   if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
   }

  }
  return mat;
 }

}
