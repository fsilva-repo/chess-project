package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.Rook;

public class ChessMatch {
 private Board board;

 public ChessMatch() {
  this.board = new Board(8, 8);
  initialSetup();
 }

 public Board getBoard() {
  return board;
 }
 
 public ChessPiece[][] getPieces() {
  ChessPiece[][] mat = new ChessPiece[this.board.getRows()][this.board.getColumns()];
  for (int i = 0; i < this.board.getRows(); i++) {
   for (int j = 0; j < this.board.getColumns(); j++) {
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
 private void initialSetup() {
  this.board.placePiece(new Rook(this.board, Color.WHITE), new Position(2, 4));
 }

}
