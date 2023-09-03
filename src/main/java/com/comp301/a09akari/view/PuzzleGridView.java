package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.CellType;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PuzzleGridView implements FXComponent {
  private final AlternateMvcController controller;

  public PuzzleGridView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane format = new GridPane();
    format.setGridLinesVisible(true);
    for (int r = 0; r < controller.getActivePuzzle().getHeight(); r++) {
      for (int c = 0; c < controller.getActivePuzzle().getWidth(); c++) {
        if (controller.getActivePuzzle().getCellType(r, c).equals(CellType.CORRIDOR)) {
          CorridorView corridor = new CorridorView(controller, r, c);
          format.add(corridor.render(), c, r);
        } else if (controller.getActivePuzzle().getCellType(r, c).equals(CellType.WALL)) {
          Rectangle rectangle = new Rectangle(50, 50);
          rectangle.setFill(Color.BLACK);
          format.add(rectangle, c, r);
        } else if (controller.getActivePuzzle().getCellType(r, c) == CellType.CLUE) {
          ClueView clue = new ClueView(controller, r, c);
          format.add(clue.render(), c, r);
        }
      }
    }

    return format;
  }
}
