package app.game;

import java.util.LinkedList;
import java.util.Queue;

import app.InputHandler;
import app.Vec2i;
import app.game.rendering.CellScreen;
import app.ui.ControlPanel;
import app.command.Command;

public class GameInstance {
    private MissionPackage missionPackage;

    private CellScreen screen;

    private String missionPackageName;

    private InputHandler input = new InputHandler();

    private Runnable onStop;

    private final Queue<Command> commandQueue = new LinkedList<>();
    private Command currentCommand;

    private Vec2i camera = new Vec2i(0, 0);

    // private ControlPanel controlPanel;

    /*
     * If fromFile is true, missionPackageName is treated as a file path. Otherwise,
     * it's treated as a resource name.
     */
    public GameInstance(String missionPackageName, Runnable onStop, int displayIndex,
            ControlPanel controlPanel) {
        missionPackage = new MissionPackage(missionPackageName);

        this.missionPackageName = missionPackage.getName();

        screen = new CellScreen("Asciia - " + this.missionPackageName, input, displayIndex);
        // this.controlPanel = controlPanel;

        this.onStop = onStop;

    }

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

    public void render() {
        screen.clear();

        missionPackage.render(screen, camera, screen.getSize().divide(2));

        screen.render();
    }

    public void show() {
        screen.show();
    }

    public void stop() {
        // Close window
        screen.close();

        // Save game state
        missionPackage.save();
    }

    public void setOnStop(Runnable onStop) {
        this.onStop = onStop;
    }

    public void requestStop() {
        if (onStop != null) {
            onStop.run();
        }
    }

    public Vec2i getCamera() {
        return camera;
    }

    public void setCamera(Vec2i camera) {
        this.camera = camera;
    }

    public void enqueue(Command command) {
        commandQueue.add(command);
    }

}
