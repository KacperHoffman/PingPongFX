package org.pingpong.controller;

import javafx.animation.AnimationTimer;
import org.pingpong.model.GameState;

import java.sql.SQLOutput;

public class GameTimer extends AnimationTimer {
    private final Game game;
    private Runnable runnable;

    public GameTimer(Game game, Runnable runnable) {
        this.game = game;
        this.runnable = runnable;
    }

    @Override
    public void handle(long l) {
        game.updateGame();

        if (game.getGameState().equals(GameState.FINISHED)){
            runnable.run();
        }
    }
}
