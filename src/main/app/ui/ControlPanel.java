package app.ui;

import app.InputHandler;
import app.Vec2i;
import app.command.Command;
import app.game.GameInstance;
import app.ui.rendering.UIScreen;

/**
 * Represents the control panel for the game. This panel allows the user to
 * interact with the game instance, including viewing and setting the camera
 * position, and enqueuing commands.
 */
public class ControlPanel {

    private UIScreen screen;

    private Runnable onStop;

    private InputHandler input = new InputHandler();

    private GameInstance gameInstance;

    /**
     * Create a new control panel with the given onStop runnable and game instance.
     * 
     * @param onStop       A runnable to execute when the control panel is closed,
     *                     which will stop
     * @param gameInstance The game instance that the control panel will interact
     *                     with.
     */
    public ControlPanel(Runnable onStop, GameInstance gameInstance) {
        this.screen = new UIScreen("Asciia - Control Panel", input, this);
        this.onStop = onStop;
        this.gameInstance = gameInstance;

    }

    /**
     * Set the game instance for the control panel. This is necessary to allow the
     * control panel to interact with the game instance, such as setting the camera
     * position and enqueuing commands.
     * 
     * @param gameInstance The game instance that the control panel will interact
     *                     with.
     */
    public void setGameInstance(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    /**
     * Render the control panel. This will update the UI elements to reflect the
     * current state of the game instance.
     */
    public void render() {
        screen.render();
    }

    /**
     * Request to stop the control panel. This will execute the onStop runnable,
     * which
     * will stop the control panel and return to the main menu.
     * 
     * @param deltatime The time since the last update, in seconds.
     */
    public void update(double deltatime) {
        if (input.isKeyPressed(javafx.scene.input.KeyCode.ESCAPE)) {
            requestStop();
        }

    }

    /**
     * Stop the control panel. This will close the control panel screen and return
     * to the main menu.
     */
    public void stop() {
        screen.close();
    }

    /**
     * Set the onStop runnable for the control panel. This is necessary to allow the
     * control panel to execute the onStop runnable when the control panel is
     * closed, which will stop the game instance and return to the main menu.
     * 
     * @param onStop A runnable to execute when the control panel is closed, which
     *               will stop the game instance and return to the main menu.
     */
    public void setOnStop(Runnable onStop) {
        this.onStop = onStop;
    }

    /**
     * Request to stop the game instance. This will execute the onStop runnable,
     * which will stop the game instance and return to the main menu.
     */
    public void requestStop() {
        if (onStop != null) {
            onStop.run();
        }
    }

    /**
     * Show the control panel. This will make the control panel screen visible.
     *
     * @return The index of the selected game instance display.
     */
    public int selectGameInstanceDisplay() {
        return screen.selectGameInstanceDisplay();
    }

    /**
     * Get the camera position from the game instance.
     * 
     * @return The camera position as a Vec2i object.
     */
    public Vec2i getCamera() {
        return gameInstance.getCamera();
    }

    /**
     * Set the camera position in the game instance.
     * 
     * @param camera The new camera position as a Vec2i object.
     */
    public void setCamera(Vec2i camera) {
        gameInstance.setCamera(camera);
    }

    /**
     * Enqueue a command to be executed in the game instance. This will add the
     * command to the game instance's command queue, which will be executed in FIFO
     * order.
     * 
     * @param command The command to be executed in the game instance.
     */
    public void enqueueCommand(Command command) {
        gameInstance.enqueue(command);
    }

}
