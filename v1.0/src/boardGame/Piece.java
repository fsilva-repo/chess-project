package boardGame;

public class Piece {
  protected Position position;
  private Board board;
  public Piece(Position position, Board board) {
    this.position = null;
    this.board = board;
  }
  public Position getPosition() {
    return position;
  }
  protected Board getBoard() {
    return board;
  }
  
}
