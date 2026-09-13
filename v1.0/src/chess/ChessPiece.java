package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

public abstract class ChessPiece extends Piece{
  private Color color;
  private int moveCount;

  public ChessPiece(Board board, Color color) {
    super(board);
    this.color = color;
  }
  public Color getColor() {
   return color;
  }

  public void inscreaseMoveCount() {
    moveCount++;
  }
  
  public void descreaseMoveCount() {
    moveCount--;
  }

  public int getMoveCount() {
    return moveCount;
  }

  // obtendo a posição da peça e convertendo para posição de xadrez
  public ChessPosition getChessPosition() {
    return ChessPosition.fromPosition(position);  
  }

  // verifica se a peça é uma openente
  protected boolean isThereOpponentPiece(Position position) {
    // resgata a posição da ChessPiece (peça) do tabuleiro
    ChessPiece piece = (ChessPiece)getBoard().piece(position);
    return piece != null && piece.getColor() != this.color;
  }
  
}
