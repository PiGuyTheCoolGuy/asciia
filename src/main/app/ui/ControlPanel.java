package app.ui;

import app.InputHandler;
import app.ui.rendering.UIScreen;

public class ControlPanel {

    private UIScreen screen;

    private Runnable onStop;

    private InputHandler input = new InputHandler();

    public ControlPanel(Runnable onStop) {
        this.screen = new UIScreen("Asciia - Control Panel", input);
        this.onStop = onStop;
    }

    public void render() {
        screen.render();
    }

    public void update() {
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

}
