package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class AllTogetherView implements FXComponent {
  private final AlternateMvcController controller;

  public AllTogetherView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() throws FileNotFoundException {
    VBox format = new VBox();

    PuzzleIndexInLibraryView topBar = new PuzzleIndexInLibraryView(controller);
    format.getChildren().add(topBar.render());

    PuzzleGridView puzzleGrid = new PuzzleGridView(controller);
    format.getChildren().add(puzzleGrid.render());

    StackPane backdrop = new StackPane();
    backdrop.setMinWidth(25);
    backdrop.setMaxHeight(25);
    format.getChildren().add(backdrop);

    PuzzleControlsView puzzleControls = new PuzzleControlsView(controller);
    format.getChildren().add(puzzleControls.render());

    return format;
  }
}
