package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
 private Board board;

 public ChessMatch() {
  board = new Board(8, 8);
  initialSetup();
 }

 public Board getBoard() {
  return board;
 }
 
 public ChessPiece[][] getPieces() {
  ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
  for (int i = 0; i < board.getRows(); i++) {
   for (int j = 0; j < board.getColumns(); j++) {
    mat[i][j] = (ChessPiece) board.getPiece(i, j);
   }
  }
  return mat;
 }

 /*
  O metodo cria duas instancia, uma do tipo Rook (peça torre do xadrez)
  que recebe a instancia do tabuleiro e uma cor
  e tambem uma instancia Position que recebe uma posição na matriz onde sera  impresso junto com o tabuleiro,
  os dois sera passados para o metodo da instancia boarde
 */
 // private void initialSetup() {
 //  board.placePiece(new Rook(board, Color.WHITE), new Position(0, 0));
 //  board.placePiece(new Rook(board, Color.WHITE), new Position(0, 7));
 //  board.placePiece(new King(board, Color.WHITE), new Position(0, 3));

 //  // black
 //  board.placePiece(new Rook(board, Color.BLACK), new Position(7, 0));
 //  board.placePiece(new Rook(board, Color.BLACK), new Position(7, 7));
 //  board.placePiece(new King(board, Color.BLACK), new Position(7, 3));
 // }

  private void placeNewPiece(char column, int row, ChessPiece piece) {
  this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
 }
 private void initialSetup() {
  placeNewPiece('a', 1, new Rook(board, Color.WHITE));
  placeNewPiece('h', 1, new Rook(board, Color.WHITE));
  placeNewPiece('e', 1, new King(board, Color.WHITE));

  // black
 placeNewPiece('a', 8, new Rook(board, Color.BLACK));
 placeNewPiece('h', 8, new Rook(board, Color.BLACK));
 placeNewPiece('d', 8, new King(board, Color.BLACK));

 }
}
