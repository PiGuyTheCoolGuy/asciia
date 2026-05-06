package app.command;

import app.game.GameInstance;

/**
 * Represents a command that can be executed in the game.
 */
public interface Command {

    /**
     * Updates the command.
     * 
     * @param game      The game instance.
     * @param deltaTime The time elapsed since the last update.
     */
    void update(GameInstance game, double deltaTime);

    /**
     * Checks if the command has finished executing.
     * 
     * @return true if the command is finished, false otherwise.
     */
    boolean isFinished();
}
