package application;

import chess.ChessMatch;

public class App {
 void main() throws Exception {
  ChessMatch chessMatch = new ChessMatch();
  UI.printBoard(chessMatch.getPieces());
 }
}
