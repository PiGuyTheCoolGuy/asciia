package app;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public abstract class Screen {

    protected Stage stage;
    private Scene scene;
    protected javafx.scene.canvas.Canvas root;

    public Screen(String title, double width, double height, InputHandler input) {
        stage = new Stage();
        stage.setTitle(title);

        root = new Canvas(width, height);
        root.setLayoutX(0);
        root.setLayoutY(0);
        javafx.scene.layout.Pane pane = new javafx.scene.layout.Pane();
        pane.getChildren().add(root);
        scene = new Scene(pane, width, height);

        stage.setScene(scene);
        stage.show();

        configInput(input);

    }

    public Screen(String title, InputHandler input) {
        this(title, 800, 600, input);
    }

    public abstract void render();

    protected GraphicsContext gc() {
        return root.getGraphicsContext2D();
    }

    /*
     * public void show() {
     * render(gc());
     * stage.show();
     * }
     */

    public void fullscreen(boolean value) {
        stage.setFullScreen(value);
    }

    public Vec2i getSize() {
        return new Vec2i((int) scene.getWidth(), (int) scene.getHeight());
    }

    protected Stage getStage() {
        return stage;
    }

    private void configInput(InputHandler input) {
        scene.setOnKeyPressed(e -> input.keyPressed(e.getCode()));
        scene.setOnKeyReleased(e -> input.keyReleased(e.getCode()));
    }

    public void close() {
        stage.close();
    }
}
