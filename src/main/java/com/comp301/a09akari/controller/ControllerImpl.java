package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;

import java.io.FileNotFoundException;
import java.util.Random;

public class ControllerImpl implements AlternateMvcController {

  private final Model model;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    int nextPuzzleIndex = model.getActivePuzzleIndex() + 1;
    try {
      model.setActivePuzzleIndex(nextPuzzleIndex);
    } catch (IndexOutOfBoundsException | FileNotFoundException ignore) {

    }
  }

  @Override
  public void clickPrevPuzzle() {
    int prevPuzzleIndex = model.getActivePuzzleIndex() - 1;
    try {
      model.setActivePuzzleIndex(prevPuzzleIndex);
    } catch (IndexOutOfBoundsException | FileNotFoundException ignore) {

    }
  }

  @Override
  public void clickRandPuzzle() throws FileNotFoundException {
    if (model.getPuzzleLibrarySize() == 1) {
      return;
    }
    Random random = new Random();
    int randPuzzleIndex = model.getActivePuzzleIndex();

    while (randPuzzleIndex == model.getActivePuzzleIndex()) {
      randPuzzleIndex = random.nextInt(model.getPuzzleLibrarySize());
    }
    model.setActivePuzzleIndex(randPuzzleIndex);
  }

  @Override
  public void clickResetPuzzle() throws FileNotFoundException {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) throws FileNotFoundException {
    if (!(model.getActivePuzzle().getCellType(r, c).equals(CellType.CORRIDOR))) {
      return;
    }

    if (isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  @Override
  public boolean isLamp(int r, int c) {
    return model.isLamp(r, c);
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  @Override
  public boolean isSolved() throws FileNotFoundException {
    return model.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }

  @Override
  public int getActivePuzzleIndex() {
    return model.getActivePuzzleIndex();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return model.getPuzzleLibrarySize();
  }

  @Override
  public boolean getIsLampLegal(int r, int c) {
    return model.isLampIllegal(r, c);
  }
}
