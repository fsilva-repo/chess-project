import chess.ChessMatch;

public class App {
    // link das aulas
    // https://t.me/+C2jjfBtwgkU4MGRh
    public static void main(String[] args) throws Exception {
        System.out.println();

        ChessMatch match = new ChessMatch();
        UI.printBoard(match.getPieces());
    }
}
