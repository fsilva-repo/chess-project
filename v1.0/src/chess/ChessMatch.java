package chess;

import boardGame.Board;

public class ChessMatch {
 private Board board;

 public ChessMatch() {
  this.board = new Board(8, 8);
 }

 /* 
  * O metodo resgata o tamanho da matriz
  * de peças do tabuleiro mas retorna
  * uma matriz do tipo ChessPiece pois
  * a camada do pacote chess não mantem
  * contado direto com as classes do
  * pacote boardGames, mas apenas
  * atraves de instancias e extenções 
  */
 public ChessPiece[][] getPieces() {
  ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];

  for (int i = 0; i < board.getRows(); i++) {
    for (int j = 0; j < board.getColumns(); j++) {
      /* faremos o downCast para converter a matriz de Piece
       * para ChessPiece
      */
      chessPieces[i][j] = (ChessPiece) board.pice(i, j);
    }
  }
  return chessPieces;
 }
 
}
