package chessPieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
private ChessMatch chessMatch;
 public Pawn(Board board, Color color, ChessMatch chessMatch) {
  super(board, color);
  this.chessMatch = chessMatch;
 }


 @Override
 public String toString() {
  return "P";
 }

 public ChessMatch getChessMatch() {
    return chessMatch;
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
 
    // movimento especial (en passant) para peão branco
    if (this.position.getRow() == 3) {
     Position left = new Position(this.position.getRow(), this.position.getColumn() -1);
     if(getBoard().positionExists(left) && isThereOpponentPiece(left)
       && getBoard().piece(left) == chessMatch.getEnPassantVuneralble())
     {
       mat[left.getRow() -1][left.getColumn()] = true;
     }
 
     Position right = new Position(this.position.getRow(), this.position.getColumn() +1);
     if(getBoard().positionExists(right) && isThereOpponentPiece(right)
       && getBoard().piece(right) == chessMatch.getEnPassantVuneralble())
     {
       mat[right.getRow() -1][right.getColumn()] = true;
     }
    }

   }
   else { // movimentos do peão preto 
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
 
 
     // movimento especial (en passant) para peão preto
    if (this.position.getRow() == 4) {
     Position left = new Position(this.position.getRow(), this.position.getColumn() -1);
     if(getBoard().positionExists(left) && isThereOpponentPiece(left)
       && getBoard().piece(left) == chessMatch.getEnPassantVuneralble())
     {
         mat[left.getRow() +1][left.getColumn()] = true;
     }
 
     Position right = new Position(this.position.getRow(), this.position.getColumn() +1);
     if(getBoard().positionExists(right) && isThereOpponentPiece(right)
       && getBoard().piece(right) == chessMatch.getEnPassantVuneralble())
     {
       mat[right.getRow() +1][right.getColumn()] = true;
     }
 
   }
   }
   return mat;
  }

}
