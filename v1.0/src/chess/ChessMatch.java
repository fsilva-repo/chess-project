package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chessPieces.King;
import chessPieces.Pawn;
import chessPieces.Queen;
import chessPieces.Rook;
import chessPieces.Bishop;
import chessPieces.Knight;
import exception.ChessException;

public class ChessMatch {
 private Board board;
 private boolean check;
 private boolean checkMate;
 private int turn;
 private Color currentPlayer;
 private List<Piece> piecesOnTheBoard = new ArrayList<>();
 private List<Piece> capturedPieces = new ArrayList<>();

 public ChessMatch() {
  turn = 1;
  currentPlayer = Color.WHITE;
  this.board = new Board(8, 8);
  InitialSetup();
 }

 public int getTurn() {
  return turn;
 }

 public Color getCurrentPlayer() {
  return currentPlayer;
 }

 public boolean getCheckMate() {
   return checkMate;
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


 // validando e entregando os possiveis movimentos da peça
 // apartir de uma posição de origem.
 // metodo auxiliar para colorir o background da movimentação da peça
 public boolean[][] possibleMoves(ChessPosition sourcePosition) {
  Position position = sourcePosition.toPosition();
  validateSourcePosition(position);
  return board.piece(position).possibleMoves();
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
  // passa a jogada para o proximo jogador
  
  if (testChesk(currentPlayer)) {
    undoMove(source, target, capturedPiece);
    throw new ChessException("Você não pode se colocar em check");
  }
  
  check = (testChesk(opponent(currentPlayer))) ? true : false;
  
  if (testCheskMate(opponent(currentPlayer))) {
    checkMate = true;
  } else {
    nextTurn();
  }

  return (ChessPiece)capturedPiece;
 }

 /*
  * movimentação da peça.
  *
  * retira a peça de origem no tabuleiro
  * retira  do tabuleiro uma possivel peça capturada no destino
  * e coloca na posição de destino a peça retirada do local de origem
  * se houver uma peça adversaria na posição de destino entra para
  * a lista de peças capturadas
 */
  private Piece makeMove(Position source, Position target) {
    ChessPiece p = (ChessPiece)board.removePiece(source);
    p.inscreaseMoveCount();
    Piece capturedPiece = board.removePiece(target);
    // Up casting natural de tipo, p vai de ChessPiece para Piece
    board.placePiece(p, target);

    if (capturedPiece != null) {
      piecesOnTheBoard.remove(capturedPiece);
      capturedPieces.add(capturedPiece);
    }
    return capturedPiece;
  }

  // desfazendo uma jogada
  private void undoMove(Position source, Position target, Piece capturedPiece) {
        
    ChessPiece p = (ChessPiece)board.removePiece(target);
    p.descreaseMoveCount();
    board.placePiece(p, source);

    if (capturedPiece != null) {
      board.placePiece(capturedPiece, target);
      capturedPieces.remove(capturedPiece);
      piecesOnTheBoard.add(capturedPiece);
    }
  }

 // importante validação da posição informada se existe possiveis movimentação 
 private void validateSourcePosition(Position p) {
  // verifica posição valida e se à alguma peça na posição de destino
  if (!board.thereIsAPiece(p))
    throw new ChessException("Não existe uma peça na posição de origem");

  // valida se a peça a ser movimantada pertence ao jogador atual 
  if (currentPlayer != ((ChessPiece)board.piece(p)).getColor())
    throw new ChessException("Não pode movimentar a peça oponente");

  // verifica se à posibilidade de movimento
  if (!board.piece(p).istThereAnyPossibleMove())
    throw new ChessException("Não existe movimentos possiveis para essa peça");
 }

 private void validateTargetPosition(Position source, Position target) {
  if (!board.piece(source).possibleMove(target))
    throw new ChessException("A peça não pode ser movida para a posição de destino");
 }
 /*
  * metodo para construir a peça e definir sua posição no tabuleiro
  * tambem coloca a mesma na lista de peças do tabuleiro 
  * onde podera ser calculado quando movimentar uma peça a adversaria foi capturada
 */
 private void placeNewPiece(char column, int row, ChessPiece piece) {
  board.placePiece(piece, new ChessPosition(column, row).toPosition());
  piecesOnTheBoard.add(piece);
 }
 

 // proximo a jogador
 private void nextTurn() {
  turn++;
  currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
 }

 private Color opponent(Color color) {
  return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
 }

 // procurando a peça Rei no tabuleiro
 private ChessPiece king(Color color) {
  // forma uma lista de peças da mesma cor que estiverem no tabuleiro
  List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() ==
  color).collect(Collectors.toList());
  // procura na lista uma instancia da classe King e a retorna
  for (Piece p : list) {
    if (p instanceof King) {
      return (ChessPiece)p;
    }
  }
  throw new IllegalStateException("Não existe Rei com a cor " + color + ", no tabuleiro");
 }

 // testando se o rei esta em check
 private boolean testChesk(Color color) {
  Position kingPosition = king(color).getChessPosition().toPosition();
  List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() ==
  opponent(color)).collect(Collectors.toList());
  
  for (Piece p : opponentPieces) {
    boolean[][] mat = p.possibleMoves();
    if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
      return  true;
    }
  }
  return false;
 }

 private boolean testCheskMate(Color color) {
  if (!testChesk(color)) {
    return false;
  }

  List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() ==
  color).collect(Collectors.toList());
  /* logica central para saber se o rei esta em check-mate
   * iterando a lista de peças da mesma cor capturadas com o filtro a cima
   * primeiro obteremos uma lista de movimentos possiveis das peças
   * com os dois (FORs) que percorrem toda a matriz do tabuleiro
   * testando para cada elemento se existe movimento possivel
   * se todas retornam verdadeiro resta testar se algum desses movimentos
   * retira a peça do (CHECK),
   * para verificar isso as peças (p) do FOR e devera ser levada ate as posições
   * de movimentos possiveis e testada se nessa movimentação a peça é retirara do CHECK*/
  for (Piece p : list) {
    // matriz de movimentos possiveis
    boolean[][] mat = p.possibleMoves();
    // varredura na matriz do tabuleiro
    for (int i = 0; i < board.getRows(); i++) {
      for (int j = 0; j < board.getColumns(); j++) {
        // filtrando onde a movimentação seja verdadeira
        if (mat[i][j]) {
          // verifica se cada peça do tabuleiro o seu movimento retira do CHECK
          Position source = ((ChessPiece)p).getChessPosition().toPosition();
          Position target = new Position(i, j);
          Piece capturedPiece = makeMove(source, target);
          boolean testCheck = testChesk(color);
          undoMove(source, target, capturedPiece);
          if (!testCheck) {
            return false;
          }
        }
      }
    }


  }

  return true;
 }

 // instancia as peça que serão utilizadas
 private void InitialSetup() {

  placeNewPiece('a', 1, new Rook(board, Color.WHITE));
  placeNewPiece('b', 1, new Knight(board, Color.WHITE));
  placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
  placeNewPiece('d', 1, new Queen(board, Color.WHITE));
  placeNewPiece('e', 1, new King(board, Color.WHITE));
  placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
  placeNewPiece('g', 1, new Knight(board, Color.WHITE));
  placeNewPiece('h', 1, new Rook(board, Color.WHITE));
  placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
  placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
  placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
  placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
  placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
  placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
  placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
  placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

  placeNewPiece('a', 8, new Rook(board, Color.BLACK));
  placeNewPiece('b', 8, new Knight(board, Color.BLACK));
  placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
  placeNewPiece('d', 8, new Queen(board, Color.BLACK));
  placeNewPiece('e', 8, new King(board, Color.BLACK));
  placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
  placeNewPiece('g', 8, new Knight(board, Color.BLACK));
  placeNewPiece('h', 8, new Rook(board, Color.BLACK));
  placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
  placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
  placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
  placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
  placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
  placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
  placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
  placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
 }
 
 public boolean getCheck() {
  return check;
 }
}
