package org.pingpong.controller;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import org.pingpong.model.Ball;
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
        setBallPosition(HALF * gameWindowWidth, HALF * gameWindowHeight);
        setRectanglePosition(rectangleRight, gameWindowWidth - 25, gameWindowHeight * HALF);
        setRectanglePosition(rectangleLeft, 10, gameWindowHeight * HALF);

        rectangleRight.setOnMouseDragged(mouseEvent -> {
            setRectanglePosition(rectangleRight, gameWindowWidth - 25, mouseEvent.getY());
        });

        root.setFocusTraversable(true);
        root.setOnKeyPressed(keyEvent -> {

            if (keyEvent.getCode() == KeyCode.UP) {
                rectangleLeft.setY(rectangleLeft.getY() - LEFT_RECTANGLE_VELOCITY);
            }

            if (keyEvent.getCode() == KeyCode.DOWN) {
                rectangleLeft.setY(rectangleLeft.getY() + LEFT_RECTANGLE_VELOCITY);
            }

        });
        gameScore.setLayoutX(gameWindowWidth * HALF);

        root.getChildren().addAll(ball, rectangleRight, rectangleLeft, gameScore);
    }

    public void updateGame() {
        if (ball.getCenterX() >= rectangleRight.getX()
                && ball.getCenterY() < rectangleRight.getY() + RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight
                && ball.getCenterY() > rectangleRight.getY() - RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight) {
            rectangleCollision();
        }

        if (ball.getCenterX() <= rectangleLeft.getX() + 15
                && ball.getCenterY() < rectangleRight.getY() + RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight
                && ball.getCenterY() > rectangleRight.getY() - RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight) {
            rectangleCollision();
        }

        if (ball.getCenterY() >= gameWindowHeight || ball.getCenterY() <= 0) {
            borderCollision();
        }

        checkOut();

        gameScore.setText(leftPlayerScore + " | " + rightPlayerScore);

        setBallPosition(ball.getCenterX() + (ball.getSpeed() * ball.getDirectionX()), ball.getCenterY() + (ball.getDirectionY() * ball.getSpeed() * 0.1));
    }

    public void setBallPosition(double x, double y) {
        ball.setCenterX(x);
        ball.setCenterY(y);
    }

    public void setRectanglePosition(Rectangle rectangle, double x, double y) {
        rectangle.setX(x);
        rectangle.setY(y);
    }

    public void rectangleCollision() {
        ball.switchDirectionX();

        Random random = new Random();
        int randomNum = random.nextInt(1, 3);

        if (randomNum % 2 == 0) {
            ball.switchDirectionY();
        }
    }

    private void borderCollision() {
        ball.switchDirectionY();
    }

    private void checkOut() {

        boolean isOut = false;

        if (ball.getCenterX() <= 0) {
            rightPlayerScore += 1;
            isOut = true;
        }

        if (ball.getCenterX() >= gameWindowWidth) {
            leftPlayerScore += 1;
            isOut = true;
        }

        if (isOut) {
            resetBall();
        }
    }

    private void resetBall() {
        setBallPosition(HALF * gameWindowWidth, HALF * gameWindowHeight);
    }

    public Group getRoot() {
        return root;
    }

}
