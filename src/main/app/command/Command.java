package app.command;

import app.game.GameInstance;

public interface Command {

    void update(GameInstance game, double deltaTime);

    boolean isFinished();
}
