package chessPieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
  public Rook(Board board, Color color) {
    super(board, color);
  }
  
  @Override
  public String toString() {
    return "R";
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    Position p = new Position(0, 0);
    


    // movimentos para cima
    p.setValues(this.position.getRow() - 1, this.position.getColumn());
    /* enquanto o destino existir e na posição de destino não existe peça
     * a posição na matriz recebera true,
     * indicando que a peça podera ser movida nesta direção */
    while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setRow(p.getRow() -1);
    }
    // se a posição de destino exite e ela contem uma peça adversaria
    // tambem sera marcada como true, assim a peça pode ser movida ate o destino
    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }



    // movimentos para esquerda
    p.setValues(this.position.getRow(), this.position.getColumn() -1);
    /* enquanto o destino existir e na posição de destino não existe peça
     * a posição na matriz recebera true,
     * indicando que a peça podera ser movida nesta direção */
    while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setColumn(p.getColumn() -1);
    }
    // se a posição de destino exite e ela contem uma peça adversaria
    // tambem sera marcada como true, assim a peça pode ser movida ate o destino
    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }



    // movimentos para direita
    p.setValues(this.position.getRow(), this.position.getColumn() +1);
    /* enquanto o destino existir e na posição de destino não existe peça
     * a posição na matriz recebera true,
     * indicando que a peça podera ser movida nesta direção */
    while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setColumn(p.getColumn() +1);
    }
    // se a posição de destino exite e ela contem uma peça adversaria
    // tambem sera marcada como true, assim a peça pode ser movida ate o destino
    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }




    // movimentos para baixo
    p.setValues(this.position.getRow() + 1, this.position.getColumn());
    /* enquanto o destino existir e na posição de destino não existe peça
     * a posição na matriz recebera true,
     * indicando que a peça podera ser movida nesta direção */
    while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setRow(p.getRow() +1);
    }
    // se a posição de destino exite e ela contem uma peça adversaria
    // tambem sera marcada como true, assim a peça pode ser movida ate o destino
    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    

    return mat;
  }
}
