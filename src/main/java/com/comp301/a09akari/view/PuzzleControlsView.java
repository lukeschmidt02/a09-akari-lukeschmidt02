package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.FileNotFoundException;

public class PuzzleControlsView implements FXComponent {
  private final AlternateMvcController controller;

  public PuzzleControlsView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    BorderPane format = new BorderPane();
    format.getStyleClass().add("controls-layout");

    Button prevButton = new Button("Previous");
    prevButton.setOnAction(
        actionEvent -> {
          controller.clickPrevPuzzle();
          try {
            controller.clickResetPuzzle();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        });
    format.setLeft(prevButton);

    Button randButton = new Button("Random");
    randButton.setOnAction(
        actionEvent -> {
          try {
            controller.clickRandPuzzle();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          try {
            controller.clickResetPuzzle();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        });
    format.setCenter(randButton);

    Button nextButton = new Button("Next");
    nextButton.setOnAction(
        actionEvent -> {
          controller.clickNextPuzzle();
          try {
            controller.clickResetPuzzle();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        });
    format.setRight(nextButton);

    return format;
  }
}
