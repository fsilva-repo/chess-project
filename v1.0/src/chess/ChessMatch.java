package chess;

import boardGame.Board;
import chessPieces.King;
import chessPieces.Rook;

public class ChessMatch {
 private Board board;

 public ChessMatch() {
  this.board = new Board(8, 8);
  InitialSetup();
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
      chessPieces[i][j] = (ChessPiece) board.piece(i, j);
    }
  }
  return chessPieces;
 }

 private void placeNewPiece(char column, int row, ChessPiece piece) {
  board.palcePiece(piece, new ChessPosition(column, row).toPosition());
 }
 
 private void InitialSetup() {
  placeNewPiece('a', 2, new Rook(board, Color.WHITE));
  placeNewPiece('e', 1, new King(board, Color.WHITE));
  placeNewPiece('h', 2, new Rook(board, Color.WHITE));
 
  placeNewPiece('a', 7, new Rook(board, Color.BLACK));
  placeNewPiece('d', 8, new King(board, Color.BLACK));
  placeNewPiece('h', 7, new Rook(board, Color.BLACK));
}
}
