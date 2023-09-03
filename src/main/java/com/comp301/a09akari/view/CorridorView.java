package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CorridorView implements FXComponent {
  private final AlternateMvcController controller;
  int r;
  int c;

  public CorridorView(AlternateMvcController controller, int r, int c) {
    this.controller = controller;
    this.r = r;
    this.c = c;
  }

  @Override
  public Parent render() {
    StackPane format = new StackPane();

    Rectangle block = new Rectangle(50, 50);
    ImageView lamp = null;

    block.setOnMouseClicked(
        event -> {
          try {
            controller.clickCell(r, c);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          // .out.println("Clicked");
        });
    if (controller.isLit(r, c)) {
      block.setFill(Color.YELLOW);
      format.getChildren().add(block);
      if (controller.isLamp(r, c)) {
        if (controller.getIsLampLegal(r, c)) {
          try {
            // Fix Image Search
            FileInputStream file = new FileInputStream("src/main/resources/illegal-bulb.png");
            Image image = new Image(file);
            lamp = new ImageView(image);
            lamp.setFitHeight(40);
            lamp.setFitWidth(40);
          } catch (FileNotFoundException ignore) {

          }
        } else {
          try {
            Image image = new Image(new FileInputStream("src/main/resources/light-bulb.png"));
            lamp = new ImageView(image);
            lamp.setFitHeight(40);
            lamp.setFitWidth(40);
          } catch (FileNotFoundException ignore) {

          }
        }
        assert lamp != null;
        lamp.setOnMouseClicked(
            event -> {
              try {
                controller.clickCell(r, c);
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
              // System.out.println("Clicked");
            });
        format.getChildren().add(lamp);
      }
    } else {
      block.setFill(Color.WHITE);
      format.getChildren().add(block);
    }

    return format;
  }
}
