package org.pingpong;

import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {
    private Game game;


    public GameTimer(Game game) {
        this.game = game;
    }

    @Override
    public void handle(long l) {

        if (game.getBallPositionX() >= game.getRectangleRightPositionX()
                && game.getBallPositionY() < game.getRectangleRightPositionY() + 20
                && game.getBallPositionY() > game.getRectangleRightPositionY() - 20) {
            game.switchXDirection();
        }

        if (game.getBallPositionX() <= game.getRectangleLeftPositionX()+30
                && game.getBallPositionY() < game.getRectangleLeftPositionY() + 20
                && game.getBallPositionY() > game.getRectangleLeftPositionY() - 20) {
            System.out.println("switch");
            game.switchXDirection();
        }

        game.setBallPosition(game.getBallPositionX() + (3 * game.getXDirection()), game.getBallPositionY());

    }
}
