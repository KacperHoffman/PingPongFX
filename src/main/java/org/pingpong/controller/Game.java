package org.pingpong.controller;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import org.pingpong.model.Ball;
import org.pingpong.model.GameState;
import org.pingpong.model.Launcher;

import java.util.Random;

public class Game {
    private final double RECTANGLE_HEIGHT_TO_WINDOW_RATIO = 0.15;
    private final double RECTANGLE_WIDTH = 15;
    private final double HALF = 0.5;
    private final double LEFT_RECTANGLE_VELOCITY = 10;
    private final Group root;
    private final Rectangle rectangleLeft;
    private final Rectangle rectangleRight;
    private final Ball ball;
    private final Label gameScore;
    private final int gameWindowWidth;
    private final int gameWindowHeight;
    private int leftPlayerScore;
    private int rightPlayerScore;
    private GameState gameState;

    public Game(int sceneH, int sceneW) {
        this.gameWindowHeight = sceneH;
        this.gameWindowWidth = sceneW;
        root = new Group();
        rectangleRight = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight);
        rectangleLeft = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight);
        ball = new Ball(10);
        leftPlayerScore = 0;
        rightPlayerScore = 0;
        gameScore = new Label(leftPlayerScore + " | " + rightPlayerScore);
        setPieces();
    }

    private void setPieces() {
        gameState = GameState.RUNNING;
        setBallPosition(HALF * gameWindowWidth, HALF * gameWindowHeight);
        setRectanglePosition(rectangleRight, gameWindowWidth - 35, (gameWindowHeight * HALF) - (rectangleRight.getHeight() / 2));
        setRectanglePosition(rectangleLeft, 5, (gameWindowHeight * HALF) - (rectangleLeft.getHeight() / 2));

        rectangleRight.setOnMouseDragged(mouseEvent -> {
            setRectanglePosition(rectangleRight, rectangleRight.getX(), mouseEvent.getY());
        });

        root.setFocusTraversable(true);
        root.setOnKeyPressed(keyEvent -> {

            if (keyEvent.getCode() == KeyCode.UP && rectangleLeft.getY() + 3 >= 0) {
                rectangleLeft.setY(rectangleLeft.getY() - LEFT_RECTANGLE_VELOCITY);
            }

            if (keyEvent.getCode() == KeyCode.DOWN && rectangleLeft.getY() + 3 <= gameWindowHeight - RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight) {
                rectangleLeft.setY(rectangleLeft.getY() + LEFT_RECTANGLE_VELOCITY);
            }

        });
        gameScore.setLayoutX(gameWindowWidth * HALF);

        root.getChildren().addAll(ball, rectangleRight, rectangleLeft, gameScore);
    }

    public void updateGame() {

        if (ballReturned()) {
            rectangleCollision();
            ball.addSpeed(0.5);
        }

        if (ballBouncedOnBorder()) {
            borderCollision();
        }

        if (isOut()) {
            updateGameScore();
            resetBall();
        }

        if (leftPlayerScore == 2 || rightPlayerScore == 2) {
            endGame();
        }

        setBallPosition(ball.getCenterX() + (ball.getSpeed() * ball.getDirectionX()), ball.getCenterY() + (ball.getDirectionY() * ball.getSpeed() * 0.1));
    }

    private void setBallPosition(double x, double y) {
        ball.setCenterX(x);
        ball.setCenterY(y);
    }

    private void setRectanglePosition(Rectangle rectangle, double x, double y) {
        rectangle.setX(x);
        rectangle.setY(y);
    }

    private void rectangleCollision() {
        ball.switchDirectionX();

        Random random = new Random();
        boolean switchY = random.nextBoolean();

        if (switchY) {
            ball.switchDirectionY();
        }
    }

    private void borderCollision() {
        ball.switchDirectionY();
    }

    private boolean ballReturned() {
        return (ball.getCenterX() >= rectangleRight.getX() - ball.getRadius()
                && ball.getCenterY() >= rectangleRight.getY()
                && ball.getCenterY() <= rectangleRight.getY() + RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight)
                || (ball.getCenterX() <= rectangleLeft.getX() + RECTANGLE_WIDTH + ball.getRadius()
                && ball.getCenterY() >= rectangleLeft.getY()
                && ball.getCenterY() <= rectangleLeft.getY() + RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight);

    }

    private boolean ballBouncedOnBorder() {
        return ball.getCenterY() + ball.getRadius() >= gameWindowHeight || ball.getCenterY() + ball.getRadius() <= 15;
    }

    private boolean isOut() {

        boolean isOut = false;

        if (ball.getCenterX() <= 0) {
            rightPlayerScore += 1;
            isOut = true;
        }

        if (ball.getCenterX() >= gameWindowWidth) {
            leftPlayerScore += 1;
            isOut = true;
        }

        return isOut;
    }

    private void updateGameScore() {
        gameScore.setText(leftPlayerScore + " | " + rightPlayerScore);
    }

    private void resetBall() {
        setBallPosition(HALF * gameWindowWidth, HALF * gameWindowHeight);
        ball.setRandomBallDirections();
        ball.resetSpeed();
    }

    private void endGame() {
        gameState = GameState.FINISHED;
    }

    public GameState getGameState() {
        return gameState;
    }

    public String getWinner() {
        if (rightPlayerScore > leftPlayerScore) {
            return "Right player wins";
        } else {
            return "Left player wins";
        }
    }

    public void resetGame() {
        leftPlayerScore = 0;
        rightPlayerScore = 0;
        gameState = GameState.RUNNING;
        resetBall();
        setRectanglePosition(rectangleRight, rectangleRight.getX(), (gameWindowHeight * HALF) - (rectangleRight.getHeight() / 2));
        setRectanglePosition(rectangleLeft, 10, (gameWindowHeight * HALF) - (rectangleLeft.getHeight() / 2));

    }

    public Group getRoot() {
        return root;
    }
}
