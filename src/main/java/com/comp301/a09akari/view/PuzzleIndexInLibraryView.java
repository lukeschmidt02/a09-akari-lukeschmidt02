package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.FileNotFoundException;

public class PuzzleIndexInLibraryView implements FXComponent {
  private final AlternateMvcController controller;

  public PuzzleIndexInLibraryView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    BorderPane format = new BorderPane();
    int index = controller.getActivePuzzleIndex() + 1;
    int puzzleSize = controller.getPuzzleLibrarySize();
    Label puzzleLocation = new Label(index + " of " + puzzleSize);

    format.setLeft(puzzleLocation);

    format.getStyleClass().add("View Reset");

    Button reset = new Button("Reset");
    reset.setOnAction(
        actionEvent -> {
          try {
            controller.clickResetPuzzle();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        });
    format.setRight(reset);

    return format;
  }
}
