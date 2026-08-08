package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
import exceptions.ChessException;

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

 public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetposition) {
  Position source = sourcePosition.toPosition();
  Position target = targetposition.toPosition();
  validateSourcePosition(source);
  Piece capturedPiece = makeMove(source, target);
  return (ChessPiece)capturedPiece; 
 }

 private Piece makeMove(Position source, Position target) {
  Piece p = board.removePiece(source);
  Piece capturedPiece = board.removePiece(target);
  board.placePiece(p, target);
  return capturedPiece;
 }

 private void validateSourcePosition(Position position) {
  if (!board.thereIsAPiece(position)) {
   throw new ChessException("Não existe peça nessa posição");
  }
 }
 /*
  O metodo cria duas instancia, uma do tipo Rook (peça torre do xadrez)
  que recebe a instancia do tabuleiro e uma cor
  e tambem uma instancia Position que recebe uma posição na matriz onde sera  impresso junto com o tabuleiro,
  os dois sera passados para o metodo da instancia boarde
 */
 
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
