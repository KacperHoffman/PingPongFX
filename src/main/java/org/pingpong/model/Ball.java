package org.pingpong.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ball extends Circle {
    private double speed;
    private int directionX;
    private int directionY;

    public Ball(int radius) {
        super(radius);
        this.speed = 1;
        this.setFill(Color.AQUAMARINE);
        setRandomBallDirections();
    }

    private int getRandomDirection() {
        Random random = new Random();
        boolean randomBool = random.nextBoolean();

        if (randomBool) {
            return 1;
        }
        return -1;
    }

    public void setRandomBallDirections(){
        directionX = getRandomDirection();
        directionY = getRandomDirection();
    }

    public double getSpeed() {
        return speed;
    }

    public void addSpeed(double speedToAdd) {
        speed += speedToAdd;
    }

    public void resetSpeed() {
        speed = 1;
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
