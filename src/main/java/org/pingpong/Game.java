package org.pingpong;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Game {
    private final double RECTANGLE_HEIGHT_TO_WINDOW_RATIO = 0.15;
    private final double RECTANGLE_WIDTH = 15;
    private final double HALF = 0.5;
    private final double LEFT_RECTANGLE_VELOCITY = 10;
    private final Group root;
    private final Rectangle rectangleLeft;
    private final Rectangle rectangleRight;
    private final Circle ball;
    private final int gameWindowWidth;
    private final int gameWindowHeight;
    private double ballVelocity;
    private double directionX;
    private double directionY;
    private double deviation;

    public Game(int sceneH, int sceneW) {
        this.gameWindowHeight = sceneH;
        this.gameWindowWidth = sceneW;
        root = new Group();
        rectangleRight = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight);
        rectangleLeft = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight);
        ball = new Circle(10);
        directionX = 1;
        directionY = 1;
        ballVelocity = 3;
        deviation = 0;
        setPieces();
    }

    private void setPieces() {
        setBallPosition(HALF * gameWindowWidth, HALF * gameWindowHeight);
        setRectanglePosition(rectangleRight, gameWindowWidth - 25, gameWindowHeight * HALF);
        setRectanglePosition(rectangleLeft, 10, gameWindowHeight * HALF);

        rectangleRight.setOnMouseDragged(mouseEvent -> {
            setRectangleRightPosition(gameWindowWidth - 25, mouseEvent.getY());
        });

        root.setFocusTraversable(true);
        root.setOnKeyPressed(keyEvent -> {

            if (keyEvent.getCode() == KeyCode.UP) {
                rectangleLeft.setY(getRectangleLeftPositionY() - LEFT_RECTANGLE_VELOCITY);
            }

            if (keyEvent.getCode() == KeyCode.DOWN) {
                rectangleLeft.setY(getRectangleLeftPositionY() + LEFT_RECTANGLE_VELOCITY);
            }

        });

        root.getChildren().addAll(ball, rectangleRight, rectangleLeft);
    }

    public void updateGame() {
        if (getBallPositionX() >= getRectangleRightPositionX()
                && getBallPositionY() < getRectangleRightPositionY() + RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight
                && getBallPositionY() > getRectangleRightPositionY() - RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight) {
            switchXDirection();
        }

        if (getBallPositionX() <= getRectangleLeftPositionX() + 15
                && getBallPositionY() < getRectangleLeftPositionY() + RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight
                && getBallPositionY() > getRectangleLeftPositionY() - RECTANGLE_HEIGHT_TO_WINDOW_RATIO * gameWindowHeight) {
            switchXDirection();
        }

        if(getBallPositionY() >= gameWindowHeight){
            directionY *= -1;
        }

        if(getBallPositionY() <= 0){
            directionY *= -1;
        }

        setBallPosition(getBallPositionX() + (ballVelocity * directionX), getBallPositionY() + (directionY * ballVelocity * 0.1));

    }

    public void setBallPosition(double x, double y) {
        ball.setCenterX(x);
        ball.setCenterY(y);
    }

    public double getBallPositionX() {
        return ball.getCenterX();
    }

    public double getBallPositionY() {
        return ball.getCenterY();
    }

    public void setRectangleRightPosition(double x, double y) {
        rectangleRight.setX(x);
        rectangleRight.setY(y);
    }

    public double getRectangleRightPositionX() {
        return rectangleRight.getX();
    }

    public double getRectangleRightPositionY() {
        return rectangleRight.getY();
    }

    public void setRectanglePosition(Rectangle rectangle, double x, double y) {
        rectangle.setX(x);
        rectangle.setY(y);
    }

    public double getRectangleLeftPositionX() {
        return rectangleLeft.getX();
    }

    public double getRectangleLeftPositionY() {
        return rectangleLeft.getY();
    }

    public Group getRoot() {
        return root;
    }

    public void switchXDirection() {
        directionX *= -1;

        Random random = new Random();

        int randomNum = random.nextInt(1, 3);
        if (randomNum % 2 == 0){
            directionY *= 1;
        }
        else {
            directionY *= -1;
        }
    }
}
