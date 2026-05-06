package app.game;

import java.util.LinkedList;
import java.util.Queue;

import app.InputHandler;
import app.Vec2i;
import app.game.rendering.CellScreen;
import app.ui.ControlPanel;
import app.command.Command;

/**
 * Represents an instance of the game. A game instance manages the game state,
 * including the mission package, input handling, rendering, and command
 * execution.
 */
public class GameInstance {
    private MissionPackage missionPackage;

    private CellScreen screen;

    private String missionPackageName;

    private InputHandler input = new InputHandler();

    private Runnable onStop;

    private final Queue<Command> commandQueue = new LinkedList<>();
    private Command currentCommand;

    private Vec2i camera = new Vec2i(0, 0);

    /**
     * Create a new game instance.
     * 
     * @param missionPackageName The name of the mission package to load.
     * @param onStop             A runnable to execute when the game instance is
     *                           stopped.
     * @param displayIndex       The index of the display to use for rendering.
     * @param controlPanel       The control panel for the game instance.
     */
    public GameInstance(String missionPackageName, Runnable onStop, int displayIndex,
            ControlPanel controlPanel) {
        missionPackage = new MissionPackage(missionPackageName);

        this.missionPackageName = missionPackage.getName();
        screen = new CellScreen("Asciia - " + this.missionPackageName, input, displayIndex);
        // this.controlPanel = controlPanel;

        this.onStop = onStop;

    }

    /**
     * Update the game instance. This is where the game logic will be executed, such
     * as processing input, updating the mission package, and executing commands.
     * 
     * @param deltatime The time since the last update, in seconds.
     */
    public void update(double deltatime) {
        if (input.isKeyPressed(javafx.scene.input.KeyCode.ESCAPE)) {
            requestStop();
        }

        if (currentCommand == null && !commandQueue.isEmpty()) {
            currentCommand = commandQueue.poll();
        }

        if (currentCommand != null) {
            currentCommand.update(this, deltatime);

            if (currentCommand.isFinished()) {
                currentCommand = null;
            }
        }

        missionPackage.update(deltatime);
    }

    /**
     * Render the game instance. This will draw the current state of the mission
     * package to the screen.
     */
    public void render() {
        screen.clear();

        missionPackage.render(screen, camera);

        screen.render();
    }

    /**
     * Show the game instance. This will make the game window visible.
     * 
     * <strong>THIS WILL ONLY BE RUN ONCE.</strong>
     */
    // TODO: remove this and place <code>screen.show()</code> in its constructor
    public void show() {
        screen.show();
    }

    /**
     * Stop the game instance. This will close the game window and save the game
     * state.
     */
    public void stop() {
        // Close window
        screen.close();

        // Save game state
        missionPackage.save();
    }

    /**
     * Set the runnable to execute when the game instance is stopped.
     * 
     * @param onStop The runnable to execute when the game instance is stopped.
     */
    // TODO: Find a better placement for this
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
     * Get the camera position.
     * 
     * @return The current position of the camera.
     */
    public Vec2i getCamera() {
        return camera;
    }

    /**
     * Set the camera position.
     * 
     * @param camera The new position of the camera.
     */
    public void setCamera(Vec2i camera) {
        this.camera = camera;
    }

    /**
     * Enqueue a command to be executed. Commands will be executed in FIFO order
     * 
     * @param command
     */
    public void enqueue(Command command) {
        commandQueue.add(command);
    }

}
