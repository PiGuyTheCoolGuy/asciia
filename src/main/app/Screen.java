package app;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public abstract class Screen {

    private Stage stage;
    private Scene scene;
    protected StackPane root; // TODO: make this private

    public Screen(String title, double x, double y, double width, double height) {
        stage = new Stage();
        stage.setTitle(title);

        root = new StackPane();
        scene = new Scene(root, width, height);

        stage.setScene(scene);

        stage.setX(x);
        stage.setY(y);

        stage.show();
    }

    public Screen(String title, double width, double height) {
        stage = new Stage();
        stage.setTitle(title);

        root = new StackPane();
        scene = new Scene(root, width, height);

        stage.setScene(scene);
        stage.show();
    }

    public Screen(String title) {
        stage = new Stage();
        stage.setTitle(title);

        root = new StackPane();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void setContent(String text) {
        root.getChildren().clear();
        root.getChildren().add(new Label(text));
    }

    protected void render(StackPane root) {
        // Override in subclasses for custom rendering
        System.out.println("Rendering " + stage.getTitle() + "...");
    }

    public void show() {
        render(root);
        stage.show();
    }

    public void fullscreen(boolean value) {
        stage.setFullScreen(value);
    }

    public Vec2i getSize() {
        System.out.println(root.getWidth() + "x" + root.getHeight());
        return new Vec2i((int) scene.getWidth(), (int) scene.getHeight());
    }

    protected Stage getStage() {
        return stage;
    }
}