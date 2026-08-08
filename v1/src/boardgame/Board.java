package boardgame;

import chess.ChessPiece;
import chess.Color;
import exceptions.BoardException;

public class Board {
 private int rows;
 private int columns;
 private Piece[][] pieces;

 public Board(int rows, int columns) {
  String msg = "Erro tentando criar o tabuleiro: o numero de linhas e colunas não pode ser menor que 1";
  if (rows < 1 || columns < 1) throw new BoardException(msg);
  this.rows = rows;
  this.columns = columns;
  this.pieces = new Piece[rows][columns];
 }
 public int getRows() {
  return rows;
 }

 public int getColumns() {
  return columns;
 }

 public Piece[][] getPieces() {
  return pieces;
 }
 
 public Piece getPiece(int row, int col) {

  // programação defensiva, valida antes e lança erro personalizado
  if (!positionExists(row, col))
   throw new BoardException("Erro: essa posição não existe no tabuleiro");
  return pieces[row][col];
 }

 public Piece getPiecePosition(Position p) {
  String msg = null;
  if (p.getRow() > this.rows) {
    msg = "Erro: essa posição não existe no tabuleiro: linha: " + p.getRow();
  } else  if (p.getColumn() > this.columns) {
    msg = "Erro: essa posição não existe no tabuleiro: coluna: " + p.getColumn();
  }
   
  if (!positionExists(p)) throw new BoardException(msg);
  return pieces[p.getRow()][p.getColumn()];
 }

 /*
  O metodo recebe uma peça e uma posisão e sera repassado
  para a matriz, preenchendo o tabuleiro com a peça na posição indicada
 */
 public void placePiece(Piece piece, Position position) {
  String msg = "Erro: Já existe uma peça nessa posição, linha: ";
  if (thereIsAPiece(position))
   throw new BoardException(msg + position.getRow() + " e coluna: " + position.getColumn());
  
  this.pieces[position.getRow()][position.getColumn()] = piece;
  // a peça não tera mais o valor null, mas recebera a posição indicada no parametro do metodo
  piece.position = position;
 }

 // (Metodo Auxiliar) Verifica se a linha e a coluna estão dentro dos limites do tabuleiro.
 public boolean positionExists(int row, int column) {
  return row >= 0
   && row < rows
   && column >= 0
   && column < columns;
 }


 // metodo para remover uma do tabuleiro
 public Piece removePiece(Position position) {
  // validação
  String msg = "Erro: essa posição não existe no tabuleiro";
  if (!positionExists(position)) throw new BoardException(msg);
  if (getPiecePosition(position) == null) return null;

  // se a pea na determinada posição existe ira receber null
  // assim retirando a peça da posição que estava
  Piece auxiliaryPiece =  getPiecePosition(position);
  auxiliaryPiece.position = null;

  // a matriz de peças tambem recebera null a posição que estava a peça
  pieces[position.getRow()][position.getColumn()] = null;

  return auxiliaryPiece;
 }

 
 // Verifica se a posição existe no tabuleiro.
 public boolean positionExists(Position position) {
  return positionExists(position.getRow(), position.getColumn());
 }

 // Verifica se a peça existe no tabuleiro e se a posição é valida.
 public boolean thereIsAPiece(Position position) {

  String msg = null;
  if (position.getRow() > this.rows) {
    msg = "Erro: essa positionosição não existe no tabuleiro: linha: " + position.getRow();
    throw new BoardException(msg);
  } else  if (position.getColumn() > this.columns) {
    msg = "Erro: essa posição não existe no tabuleiro: coluna: " + position.getColumn();
    throw new BoardException(msg);
  }

  return getPiecePosition(position) != null;
 }
}
