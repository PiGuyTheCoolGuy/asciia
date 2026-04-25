package app.game;

import app.InputHandler;
import app.game.rendering.CellScreen;

public class GameInstance {
    private MissionPackage missionPackage;

    private CellScreen screen;

    private String missionPackageName;

    private InputHandler input = new InputHandler();

    private Runnable onStop;

    /*
     * If fromFile is true, missionPackageName is treated as a file path. Otherwise,
     * it's treated as a resource name.Q
     */
    public GameInstance(String missionPackageName, boolean fromFile) {
        missionPackage = new MissionPackage(missionPackageName, fromFile);

        this.missionPackageName = missionPackage.getName();

        screen = new CellScreen("Asciia - " + this.missionPackageName);

        screen.configInput(input);

    }

    public void update() {
        if (input.isKeyPressed(javafx.scene.input.KeyCode.ESCAPE)) {
            requestStop();
        }
        if (input.isKeyPressed(javafx.scene.input.KeyCode.TAB)) {
            screen.switchFullscreenMonitor();
            input.resetAll();
        }

        missionPackage.update();
    }

    public void render() {
        screen.clear();

        missionPackage.render();

        screen.render();
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

}
