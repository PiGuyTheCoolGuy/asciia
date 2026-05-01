package app.ui;

import app.InputHandler;
import app.Vec2i;
import app.command.Command;
import app.game.GameInstance;
import app.ui.rendering.UIScreen;

public class ControlPanel {

    private UIScreen screen;

    private Runnable onStop;

    private InputHandler input = new InputHandler();

    private GameInstance gameInstance;

    public ControlPanel(Runnable onStop, GameInstance gameInstance) {
        this.screen = new UIScreen("Asciia - Control Panel", input, this);
        this.onStop = onStop;
        this.gameInstance = gameInstance;

    }

    public void setGameInstance(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    public void render() {
        screen.render();
    }

    public void update(double deltatime) {
        if (input.isKeyPressed(javafx.scene.input.KeyCode.ESCAPE)) {
            requestStop();
        }

    }

    public void stop() {
        screen.close();
    }

    public void setOnStop(Runnable onStop) {
        this.onStop = onStop;
    }

    public void requestStop() {
        if (onStop != null) {
            onStop.run();
        }
    }

    public int selectGameInstanceDisplay() {
        return screen.selectGameInstanceDisplay();
    }

    public Vec2i getCamera() {
        return gameInstance.getCamera();
    }

    public void setCamera(Vec2i camera) {
        gameInstance.setCamera(camera);
    }

    public void enqueueCommand(Command command) {
        gameInstance.enqueue(command);
    }

}
