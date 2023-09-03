package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    PuzzleLibrary puzzleLibrary = new PuzzleLibraryImpl();
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));

    Model model = new ModelImpl(puzzleLibrary);

    AlternateMvcController controller = new ControllerImpl(model);

    Solved unitedAkariWindow = new Solved(controller);

    Scene scene = new Scene(unitedAkariWindow.render());
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);

    model.addObserver(
        modelM -> {
          scene.setRoot(unitedAkariWindow.render());
          stage.sizeToScene();
        });

    stage.setTitle("Akari");
    stage.setResizable(false);
    stage.show();
  }
}
