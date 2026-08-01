import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;

public class App {
    public static void main(String[] args) throws Exception {
        Position p = new Position(2, 6);
        System.out.println(p);

        System.out.println();

        Board board = new Board(8, 6);
        System.out.println(board.getRows());
        
        System.out.println();

        ChessMatch match = new ChessMatch();
        UI.printBoard(match.getPieces());
    }
}
