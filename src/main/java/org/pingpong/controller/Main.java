package org.pingpong.controller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    private Game game;
    private GameTimer gameTimer;
    private Scene gameScene;

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Ping Pong FX");
        stage.setWidth(700);
        stage.setHeight(500);

        BorderPane startScreenContainer = new BorderPane();

        Label startScreenLabel = new Label("Welcome to Ping Pong FX!");
        Button startScreenButton = new Button("Play");


        HBox startScreenElements = new HBox(startScreenLabel, startScreenButton);
        startScreenElements.setSpacing(20);
        startScreenElements.setAlignment(Pos.CENTER);

        startScreenContainer.setCenter(startScreenElements);

        game = new Game(500, 700);
        gameTimer = new GameTimer(game, () -> {
            showEndGameScreen(stage);
        });

        gameScene = new Scene(startScreenContainer, 500, 700);
        gameScene.setFill(Color.LIGHTGOLDENRODYELLOW);

        startScreenButton.setOnAction((click) -> {
            gameTimer.start();
            gameScene.setRoot(game.getRoot());
            game.getRoot().requestFocus();
        });

        stage.setScene(gameScene);
        stage.show();
    }

    private void showEndGameScreen(Stage stage) {
        System.out.println("show end game");
        gameTimer.stop();

        BorderPane endGameScreenContainer = new BorderPane();
        Button restartGameButton = new Button("Play again");

        VBox endGameElements = new VBox(new Label("Game finished"), new Label(game.getWinner()), restartGameButton);
        endGameElements.setSpacing(20);
        endGameElements.setAlignment(Pos.CENTER);
        endGameScreenContainer.setCenter(endGameElements);

        Scene endScene = new Scene(endGameScreenContainer, 700, 500);

        restartGameButton.setOnAction((click) -> {
            System.out.println("restart game");
            game.resetGame();
            stage.setScene(gameScene);
            gameTimer.start();
            game.getRoot().requestFocus();
        });

        stage.setScene(endScene);
    }

}