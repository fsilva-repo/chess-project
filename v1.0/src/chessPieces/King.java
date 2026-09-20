package chessPieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
  private ChessMatch chessMatch;
  public King(Board board, Color color, ChessMatch chessMatch) {
    super(board, color);
    this.chessMatch = chessMatch;
  }

  @Override
  public String toString() {
    return "K";
  }

  // antes de mover a peça é preciso saber
  // se a posição tem o valor nulo e se
  // existe alguma peça aliada
  private boolean canMove(Position position) {
    ChessPiece p = (ChessPiece)getBoard().piece(position);
    return p == null || p.getColor() != this.getColor();
  }

  // testando se a torre esta apta para a jogada rock
  private boolean testRookCastling(Position position) {
    ChessPiece p = (ChessPiece)getBoard().piece(position);
    return  p != null 
      && p instanceof Rook
      && p.getColor() == this.getColor()
      && p.getMoveCount() == 0;
  }


  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    Position p = new Position(0, 0);

    // movimento para cima (norte N)
    p.setValues(this.position.getRow() -1, this.position.getColumn());
    if (getBoard().positionExists(p) && canMove(p))
      mat[p.getRow()][p.getColumn()] = true;
    
    
    // movimento para baixo (sul S)
    p.setValues(this.position.getRow() +1, this.position.getColumn());
    if (getBoard().positionExists(p) && canMove(p))
      mat[p.getRow()][p.getColumn()] = true;
    
    
        
    // movimento para a esquerda (oeste W)
    p.setValues(this.position.getRow(), this.position.getColumn() -1);
    if (getBoard().positionExists(p) && canMove(p))
      mat[p.getRow()][p.getColumn()] = true;
    
    
    // movimento para a direita (leste E)
    p.setValues(this.position.getRow(), this.position.getColumn() +1);
    if (getBoard().positionExists(p) && canMove(p))
      mat[p.getRow()][p.getColumn()] = true;
    
    
    // movimento para diagonal esquerda (noroeste NW)
    p.setValues(this.position.getRow() -1, this.position.getColumn() -1);
    if (getBoard().positionExists(p) && canMove(p))
      mat[p.getRow()][p.getColumn()] = true;
    
    
    // movimento para diagonal direita (nordeste NE)
    p.setValues(this.position.getRow() -1, this.position.getColumn() +1);
    if (getBoard().positionExists(p) && canMove(p))
      mat[p.getRow()][p.getColumn()] = true;


    // movimento para diagonal esquerda (sudoeste SW)
    p.setValues(this.position.getRow() +1, this.position.getColumn() -1);
    if (getBoard().positionExists(p) && canMove(p))
      mat[p.getRow()][p.getColumn()] = true;


    // movimento para diagonal diretita (sudeste SE)
    p.setValues(this.position.getRow() +1, this.position.getColumn() +1);
    if (getBoard().positionExists(p) && canMove(p))
      mat[p.getRow()][p.getColumn()] = true;


    if (getMoveCount() == 0 && !chessMatch.getCheck()) {
      
      // movimento especial rock pequeno
      Position positionRookRight = new Position(position.getRow(), position.getColumn() +3);
      if(testRookCastling(positionRookRight)) {
        Position positionRighttKing1 = new Position(position.getRow(), position.getColumn() +1);
        Position positionRighttKing2 = new Position(position.getRow(), position.getColumn() +2);
        if (getBoard().piece(positionRighttKing1) == null
          && getBoard().piece(positionRighttKing2) == null)
        {
          mat[position.getRow()][position.getColumn() +2] = true;
        }
      }

      // movimento do rock grande
      Position positionRookLeft = new Position(position.getRow(), position.getColumn() -4);
      if(testRookCastling(positionRookLeft)) {
        Position positionLeftKing1 = new Position(position.getRow(), position.getColumn() -1);
        Position positionLeftKing2 = new Position(position.getRow(), position.getColumn() -2);
        Position positionLeftKing3 = new Position(position.getRow(), position.getColumn() -3);
        if (getBoard().piece(positionLeftKing1) == null
         && getBoard().piece(positionLeftKing2) == null
         && getBoard().piece(positionLeftKing3) == null)
        {
          mat[position.getRow()][position.getColumn() -2] = true;
        }
      }

    }
    return mat;
  
  }

} 












