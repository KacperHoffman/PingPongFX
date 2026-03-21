package org.pingpong.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    private double speed;
    private int directionX;
    private int directionY;

    public Ball(int radius) {
        super(radius);
        this.speed = 3;
        this.setFill(Color.AQUAMARINE);
        directionX = 1;
        directionY = 1;
    }

    public double getSpeed() {
        return speed;
    }

    public void addSpeed(double speedToAdd) {
        speed += speedToAdd;
    }

    public void resetSpeed() {
        speed = 3;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void switchDirectionX() {
        directionX *= -1;
    }

    public void switchDirectionY() {
        directionY *= -1;
    }
}
