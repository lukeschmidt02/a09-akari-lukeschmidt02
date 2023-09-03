package com.comp301.a09akari.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelImpl implements Model {
  // Fields
  private final PuzzleLibrary library;
  private Puzzle activePuzzle;
  private int activePuzzleIndex;
  private final ArrayList<int[]> lampCoordinates = new ArrayList<>();
  private final List<ModelObserver> modelObservers = new ArrayList<>();

  public ModelImpl(PuzzleLibrary library) {
    this.library = library;
    activePuzzleIndex = 0;
    activePuzzle = library.getPuzzle(activePuzzleIndex);
  }

  @Override
  public void addLamp(int r, int c) throws FileNotFoundException {
    if (!(activePuzzle.getCellType(r, c).equals(CellType.CORRIDOR))) {
      throw new IllegalArgumentException();
    }
    if (r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }

    if (!(isLamp(r, c))) {
      int[] lampCoordinate = {r, c};
      lampCoordinates.add(lampCoordinate);
    }
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) throws FileNotFoundException {
    if (!(activePuzzle.getCellType(r, c).equals(CellType.CORRIDOR))) {
      throw new IllegalArgumentException();
    }
    if (r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }

    int[] lampCoordinate = {r, c};
    for (int[] iCoordinate : lampCoordinates) {
      if (Arrays.equals(lampCoordinate, iCoordinate)) {
        lampCoordinates.remove(iCoordinate);
        break;
      }
    }
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (!(activePuzzle.getCellType(r, c).equals(CellType.CORRIDOR))) {
      throw new IllegalArgumentException();
    }
    if (r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (isLamp(r, c)) {
      return true;
    }

    // Row ascending
    for (int i = r;
        i < activePuzzle.getHeight() && activePuzzle.getCellType(i, c).equals(CellType.CORRIDOR);
        i++) {
      if (isLamp(i, c)) {
        return true;
      }
    }

    // Row descending
    for (int i = r; i >= 0 && activePuzzle.getCellType(i, c).equals(CellType.CORRIDOR); i--) {
      if (isLamp(i, c)) {
        return true;
      }
    }

    // Column ascending
    for (int j = c;
        j < activePuzzle.getWidth() && activePuzzle.getCellType(r, j).equals(CellType.CORRIDOR);
        j++) {
      if (isLamp(r, j)) {
        return true;
      }
    }

    // Column descending
    for (int j = c; j >= 0 && activePuzzle.getCellType(r, j).equals(CellType.CORRIDOR); j--) {
      if (isLamp(r, j)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (!(activePuzzle.getCellType(r, c).equals(CellType.CORRIDOR))) {
      throw new IllegalArgumentException();
    }
    if (r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }

    int[] lampCoordinate = {r, c};
    return lampCoordinatesContains(lampCoordinate);
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    int[] lampCoordinate = {r, c};
    if (!(lampCoordinatesContains(lampCoordinate))) {
      throw new IllegalArgumentException();
    }

    // Row ascending
    for (int i = r + 1;
        i < activePuzzle.getHeight() && activePuzzle.getCellType(i, c).equals(CellType.CORRIDOR);
        i++) {
      if (isLamp(i, c)) {
        return true;
      }
    }

    // Row descending
    for (int i = r - 1; i >= 0 && activePuzzle.getCellType(i, c).equals(CellType.CORRIDOR); i--) {
      if (isLamp(i, c)) {
        return true;
      }
    }

    // Column ascending
    for (int j = c + 1;
        j < activePuzzle.getWidth() && activePuzzle.getCellType(r, j).equals(CellType.CORRIDOR);
        j++) {
      if (isLamp(r, j)) {
        return true;
      }
    }

    // Column descending
    for (int j = c - 1; j >= 0 && activePuzzle.getCellType(r, j).equals(CellType.CORRIDOR); j--) {
      if (isLamp(r, j)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return activePuzzle;
  }

  @Override
  public int getActivePuzzleIndex() {
    return activePuzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) throws FileNotFoundException {
    if (index >= library.size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    activePuzzleIndex = index;
    activePuzzle = library.getPuzzle(activePuzzleIndex);
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() throws FileNotFoundException {
    lampCoordinates.clear();
    notifyObservers();
  }

  @Override
  public boolean isSolved() throws FileNotFoundException {
    List<int[]> lampCoordinatesCopy = new ArrayList<>(lampCoordinates);

    if (activePuzzle.getWidth() == 1 && activePuzzle.getHeight() == 1) {
      if (lampCoordinates.size() > 0) {
        return true;
      }
    }

    for (int[] iLampCoordinate : lampCoordinatesCopy) {
      if (isLampIllegal(iLampCoordinate[0], iLampCoordinate[1])) {
        return false;
      }
    }
    for (int j = 0; j < activePuzzle.getWidth(); j++) {
      for (int i = 0; i < activePuzzle.getHeight(); i++) {
        if (activePuzzle.getCellType(i, j).equals(CellType.CLUE)) {
          if (!(isClueSatisfied(i, j))) {
            return false;
          }
        } else if (activePuzzle.getCellType(i, j).equals((CellType.CORRIDOR))) {
          if (!(isLit(i, j))) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (!(activePuzzle.getCellType(r, c).equals(CellType.CLUE))) {
      throw new IllegalArgumentException();
    }
    int necessaryAdjacentLamps = activePuzzle.getClue(r, c);
    int adjacentLamps = 0;

    // checks if corridor
    if (c < activePuzzle.getWidth() - 1
        && activePuzzle.getCellType(r, c + 1) == CellType.CORRIDOR) {
      if (isLamp(r, c + 1)) {
        adjacentLamps++;
      }
    }
    if (c > 0 && activePuzzle.getCellType(r, c - 1) == CellType.CORRIDOR) {
      if (isLamp(r, c - 1)) {
        adjacentLamps++;
      }
    }
    if (r < activePuzzle.getHeight() - 1
        && activePuzzle.getCellType(r + 1, c) == CellType.CORRIDOR) {
      if (isLamp(r + 1, c)) {
        adjacentLamps++;
      }
    }
    if (r > 0 && activePuzzle.getCellType(r - 1, c) == CellType.CORRIDOR) {
      if (isLamp(r - 1, c)) {
        adjacentLamps++;
      }
    }

    return adjacentLamps == necessaryAdjacentLamps;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    modelObservers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    modelObservers.remove(observer);
  }

  private boolean lampCoordinatesContains(int[] coordinate) {
    boolean check = false;
    for (int[] iCoordinate : lampCoordinates) {
      if (Arrays.equals(coordinate, iCoordinate)) {
        check = true;
        break;
      }
    }
    return check;
  }

  private void notifyObservers() throws FileNotFoundException {
    for (ModelObserver o : modelObservers) {
      o.update(this);
    }
  }
}
