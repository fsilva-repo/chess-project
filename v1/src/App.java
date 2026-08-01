import boardgame.Board;
import boardgame.Position;

public class App {
    public static void main(String[] args) throws Exception {
        Position p = new Position(2, 6);
        System.out.println(p);

        System.out.println();

        Board board = new Board(8, 6);
        System.out.println(board.getRows());
    }
}
