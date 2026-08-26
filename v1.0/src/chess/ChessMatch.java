package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chessPieces.King;
import chessPieces.Rook;
import exception.ChessException;

public class ChessMatch {
 private Board board;

 public ChessMatch() {
  this.board = new Board(8, 8);
  InitialSetup();
 }

 /* metodo para converter os tipos Piece em
  * ChessPiece os tipos que serão postas
  * no tabuleiro
  */
 public ChessPiece[][] getPieces() {
  ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];

  for (int i = 0; i < board.getRows(); i++) {
    for (int j = 0; j < board.getColumns(); j++) {
      // down cast necessario para devolver uma matriz de ChessPiece
      chessPieces[i][j] = (ChessPiece) board.piece(i, j);
    }
  }
  return chessPieces;
 }

 /**
  * o metodo recebe as posições origem e destino
  * confirma se são validas
  * e utiliza o metodo makeMove para fazer a retirada da peca
  * da posição de origem e dar a ela a posição de destino
  */
 public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
  // conversao para coordenadas aceitas pelo tabuleiro de xadrez
  Position source = sourcePosition.toPosition();
  Position target = targetPosition.toPosition();
  // valida se as posições atual e destino existem no tabuleiro
  validateSourcePosition(source);
  validateTargetPosition(source, target);
  // por fim sera retornada uma tipo ChessPiece com uma nova posição no tabuleiro 
  Piece capturedPiece = makeMove(source, target);
  return (ChessPiece) capturedPiece;
 }

 /*
  * movimentação da peça.
  * o metedo remove a posição atual de uma determinada
  * peça e define sua nova posição no tabuleiro
 */
  private Piece makeMove(Position source, Position target) {
    Piece piece = board.removePiece(source);
    Piece capturedPiece = board.removePiece(target);
    board.placePiece(piece, target);
    return capturedPiece;
  }


 // importante validação da posição informada se existe possiveis movimentação 
 private void validateSourcePosition(Position p) {
  // verifica posição valida e se à alguma peça na posição de destino
  if (!board.thereIsAPiece(p))
    throw new ChessException("Não existe uma peça na posição de origem");
  // verifica se à posibilidade de movimento
  if (!board.piece(p).istThereAnyPossibleMove())
    throw new ChessException("Não existe movimentos possiveis para essa peça");
 }

 private void validateTargetPosition(Position source, Position target) {
  // if (!board.positionExists(p))
  // throw new ChessException("A posição de destino não existe no tabuleiro");

  if (!board.piece(source).possibleMove(target)) {
    throw new ChessException("A peça não pode ser movida para a posição de destino");
  }

 }


 // metodo para constroir a peça define sua posição no tabuleiro 
 private void placeNewPiece(char column, int row, ChessPiece piece) {
  board.placePiece(piece, new ChessPosition(column, row).toPosition());
 }
 
 // instancia as peça que serão utilizadas
 private void InitialSetup() {
  placeNewPiece('a', 2, new Rook(board, Color.WHITE));
  placeNewPiece('e', 1, new King(board, Color.WHITE));
  placeNewPiece('h', 2, new Rook(board, Color.WHITE));
 
  placeNewPiece('a', 7, new Rook(board, Color.BLACK));
  placeNewPiece('d', 8, new King(board, Color.BLACK));
  placeNewPiece('h', 7, new Rook(board, Color.BLACK));
 }

}
