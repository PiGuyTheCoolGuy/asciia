package app.ui;

import app.ui.rendering.UIScreen;

public class ControlPanel {
    private UIScreen screen;

    public ControlPanel() {
        this.screen = new UIScreen("Asciia - Control Panel");
    }

    public void render() {
        screen.render();
    }

    public void update() {
    }

}
