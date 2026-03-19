package org.pingpong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Launcher extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) {

        Game game = new Game(500, 700);
        GameTimer timer = new GameTimer(game);
        timer.start();

        Scene scene = new Scene(game.getRoot(), 700, 500);
        scene.setFill(Color.LIGHTGOLDENRODYELLOW);

        stage.setScene(scene);
        stage.show();
        game.getRoot().requestFocus();
    }
}