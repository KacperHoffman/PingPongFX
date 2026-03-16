package org.pingpong;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Launcher extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {


        //Setting the color of the circle


        //Setting the node for the transition




//        rectangle.setOnMouseDragged(mouseEvent -> {
//            System.out.println(mouseEvent.getSceneX());
//            System.out.println(mouseEvent.getSceneY());
//            rectangle.setY(mouseEvent.getSceneY() - 15);
//
//        });




        Game game = new Game(600, 600);

        GameTimer timer = new GameTimer(game);

        timer.start();

        //Creating a scene object
        Scene scene = new Scene(game.getRoot(), 600, 600);

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

        game.getRoot().requestFocus();
    }
}