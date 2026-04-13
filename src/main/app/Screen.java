package app;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public abstract class Screen {

    private Stage stage;
    private Scene scene;
    protected javafx.scene.canvas.Canvas root;

    public Screen(String title, double x, double y, double width, double height) {

        stage = new Stage();
        stage.setTitle(title);

        root = new Canvas(width, height);
        root.setLayoutX(0);
        root.setLayoutY(0);
        javafx.scene.layout.Pane pane = new javafx.scene.layout.Pane();
        pane.getChildren().add(root);
        scene = new Scene(pane, width, height);

        stage.setScene(scene);

        stage.setX(x);
        stage.setY(y);

        stage.show();
    }

    public Screen(String title, double width, double height) {
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
    }

    public Screen(String title) {
        stage = new Stage();
        stage.setTitle(title);

        root = new Canvas(800, 600); // Default size
        root.setLayoutX(0);
        root.setLayoutY(0);
        javafx.scene.layout.Pane pane = new javafx.scene.layout.Pane();
        pane.getChildren().add(root);
        scene = new Scene(pane, 800, 600);

        stage.setScene(scene);
        stage.show();
    }

    protected void render(GraphicsContext gc) {
        System.out.println("Override render(GraphicsContext gc)");
    }

    protected GraphicsContext gc() {
        return root.getGraphicsContext2D();
    }

    public void show() {
        render(gc());
        stage.show();
    }

    public void fullscreen(boolean value) {
        stage.setFullScreen(value);
    }

    public Vec2i getSize() {
        return new Vec2i((int) scene.getWidth(), (int) scene.getHeight());
    }

    protected Stage getStage() {
        return stage;
    }
}