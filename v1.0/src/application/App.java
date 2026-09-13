package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import exception.ChessException;

public class App {
 void main() throws Exception {
  Scanner sc = new Scanner(System.in);
  ChessMatch chessMatch = new ChessMatch();
  List<ChessPiece> captured = new  ArrayList<>();

  while (!chessMatch.getCheckMate()) {
    try {
     UI.clearScreen();
     UI.printMatch(chessMatch, captured);
     System.out.println();
     System.out.print("Source: ");
     ChessPosition source = UI.readChessPosition(sc);


      boolean[][] possibleMoves = chessMatch.possibleMoves(source);
      UI.clearScreen();
      // imprime o tabuleiro com os possiveis  movimentos da peça
      // com o background colorido
      UI.printBoard(chessMatch.getPieces(), possibleMoves);


     System.out.println();
     System.out.print("Target: ");
     ChessPosition target = UI.readChessPosition(sc);
     ChessPiece capturedPiece =
     chessMatch.performChessMove(source, target);
     
     if (capturedPiece != null) captured.add(capturedPiece); 
    }
    catch (ChessException e) {
      System.err.println(e.getMessage());
      sc.nextLine();
    }
    catch (InputMismatchException e) {
      System.err.println(e.getMessage());
      sc.nextLine();
    }

  }
  UI.clearScreen();
  UI.printMatch(chessMatch, captured);
 }
}
