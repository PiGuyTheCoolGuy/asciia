package app.command;

import app.game.GameInstance;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Represents a command that can be executed in the game.
 */
public abstract class Command {

    protected VBox uiContainer;

    protected VBox panel;
    protected Label type;
    protected Label createdTimestamp;
    protected Label startedTimestamp;
    protected Label status;
    protected Label description;

    /**
     * Creates a new command with the given UI container. The UI container is where
     * the command's UI elements will be added, such as the type, timestamps,
     * status, and description.
     * 
     * @param uiContainer The UI container to which the command's UI elements should
     *                    be added.
     * @param uiContainer
     */
    public Command(VBox uiContainer) {
        this.uiContainer = uiContainer;
        panel = new VBox(5);
        panel.setStyle("-fx-border-color: cyan; -fx-border-width: 1; -fx-padding: 5;");

        type = new Label();
        createdTimestamp = new Label();
        startedTimestamp = new Label();
        status = new Label();
        description = new Label();
    }

    /**
     * Updates the command.
     * 
     * @param game      The game instance.
     * @param deltaTime The time elapsed since the last update.
     */
    public abstract void update(GameInstance game, double deltaTime);

    /**
     * Checks if the command has finished executing.
     * 
     * @return true if the command is finished, false otherwise.
     */
    public abstract boolean isFinished();

    /**
     * This will be called by the queue manager when the command is finished
     * executing. It will remove the UI elements associated with it.
     * 
     * @param game The game instance.
     */
    public abstract void finish(GameInstance game);
}
