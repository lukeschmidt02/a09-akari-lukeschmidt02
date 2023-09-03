package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileNotFoundException;

public class ClueView implements FXComponent {
  private final AlternateMvcController controller;
  int r;
  int c;

  public ClueView(AlternateMvcController controller, int r, int c) {
    this.controller = controller;
    this.r = r;
    this.c = c;
  }

  @Override
  public Parent render() {
    StackPane format = new StackPane();

    Rectangle block = new Rectangle(50, 50);

    block.setOnMouseClicked(
        event -> {
          try {
            controller.clickCell(r, c);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        });
    if (controller.isClueSatisfied(r, c)) {
      block.setFill(Color.DARKGREEN);
      format.getChildren().add(block);
    } else {
      block.setFill(Color.BLACK);
      format.getChildren().add(block);
    }
    Label requiredClue = new Label(controller.getActivePuzzle().getClue(r, c) + "");
    requiredClue.setTextFill(Color.WHITE);
    requiredClue.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
    format.getChildren().add(requiredClue);

    return format;
  }
}
