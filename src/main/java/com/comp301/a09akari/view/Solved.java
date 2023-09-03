package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileNotFoundException;

public class Solved implements FXComponent {
  private final AlternateMvcController controller;

  public Solved(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() throws FileNotFoundException {
    StackPane format = new StackPane();
    Label solved = new Label("Winner");
    solved.setFont(Font.font("impact", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 60));
    solved.setTextFill(Color.GREEN);

    AllTogetherView allTogether = new AllTogetherView(controller);
    format.getChildren().add(allTogether.render());

    if (controller.isSolved()) {
      format.getChildren().add(solved);
    }
    return format;
  }
}
