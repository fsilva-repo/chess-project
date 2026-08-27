package chessPieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

  public King(Board board, Color color) {
    super(board, color);
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


    return mat;
  
  }

}












