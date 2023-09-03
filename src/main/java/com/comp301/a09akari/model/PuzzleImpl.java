package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  // Fields
  private final int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r >= getHeight() || c >= getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (board[r][c] >= 0 && board[r][c] <= 4) {
      return CellType.CLUE;
    } else if (board[r][c] == 5) {
      return CellType.WALL;
    } else if (board[r][c] == 6) {
      return CellType.CORRIDOR;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r >= getHeight() || c >= getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (!(getCellType(r, c).equals(CellType.CLUE))) {
      throw new IllegalArgumentException();
    }

    return board[r][c];
  }
}
