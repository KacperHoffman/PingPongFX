package org.pingpong;

import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {
    private final Game game;


    public GameTimer(Game game) {
        this.game = game;
    }

    @Override
    public void handle(long l) {
        game.updateGame();


    }
}
