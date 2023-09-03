package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Puzzle;

import java.io.FileNotFoundException;

public interface AlternateMvcController {
  /** Handles the click action to go to the next puzzle */
  void clickNextPuzzle();

  /** Handles the click action to go to the previous puzzle */
  void clickPrevPuzzle();

  /** Handles the click action to go to a random puzzle */
  void clickRandPuzzle() throws FileNotFoundException;

  /** Handles the click action to reset the currently active puzzle */
  void clickResetPuzzle() throws FileNotFoundException;

  /** Handles the click event on the cell at row r, column c */
  void clickCell(int r, int c) throws FileNotFoundException;

  /** Returns true if the CORRIDOR cell at row r, column c is lit */
  boolean isLit(int r, int c);

  /** Returns true if the CORRIDOR cell at row r, column c is a lamp */
  boolean isLamp(int r, int c);

  /** Returns true if the CLUE cell at row r, column c is satisfied by nearby lamps */
  boolean isClueSatisfied(int r, int c);

  /** Returns true if the active puzzle is solved */
  boolean isSolved() throws FileNotFoundException;

  /** Getter method for the active puzzle */
  Puzzle getActivePuzzle();

  /** Getter method for the active puzzle index */
  int getActivePuzzleIndex();

  /** Getter method for the puzzle library size */
  int getPuzzleLibrarySize();

  /** Getter method for the lamp being legal */
  boolean getIsLampLegal(int r, int c);
}
