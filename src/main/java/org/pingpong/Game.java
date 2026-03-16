package org.pingpong;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game {
    private Group root;
    private Rectangle rectangleLeft;
    private Rectangle rectangleRight;
    private Circle ball;
    private int sceneW;
    private int sceneH;
    private int directionX;

    public Game(int sceneH, int sceneW) {
        this.sceneH = sceneH;
        this.sceneW = sceneW;
        root = new Group();
        rectangleRight = new Rectangle(20, 45);
        rectangleLeft = new Rectangle(20, 45);
        ball = new Circle(15);
        directionX = 1;
        setPieces();
    }

    private void setPieces() {
        setBallPosition(200, 200);
        setRectangleRightPosition(570, 200);
        setRectangleLeftPosition(0, 200);


        rectangleRight.setOnMouseDragged(mouseEvent -> {
            setRectangleRightPosition(570, mouseEvent.getY());
        });

        root.setFocusTraversable(true);

        root.setOnKeyPressed(keyEvent -> {

            if (keyEvent.getCode() == KeyCode.UP) {
                rectangleLeft.setY(getRectangleLeftPositionY() - 5);
            }

            if (keyEvent.getCode() == KeyCode.DOWN) {
                rectangleLeft.setY(getRectangleLeftPositionY() + 5);
            }

        });

        root.getChildren().addAll(ball, rectangleRight, rectangleLeft);
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

    public void setRectangleLeftPosition(double x, double y) {
        rectangleLeft.setX(x);
        rectangleLeft.setY(y);
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
    }

    public int getXDirection() {
        return directionX;
    }


}
