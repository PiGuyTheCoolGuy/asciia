package app.ui.rendering;

import app.InputHandler;
import app.Screen;

public class UIScreen extends Screen {

    public UIScreen(String title, double width, double height, InputHandler input) {
        super(title, width, height, input);
    }

    public UIScreen(String title, InputHandler input) {
        super(title, input);
    }

    @Override
    public void render() {

    }

}
