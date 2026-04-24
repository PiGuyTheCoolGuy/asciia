package app;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import app.game.GameInstance;
import javafx.application.Platform;

public class Main extends Application {

    private GameInstance gameInstance;

    private boolean running = true;

    @Override
    public void start(Stage primaryStage) {
        // TODO: figure out where the mission package is coming from
        gameInstance = new GameInstance("Test", false);
        gameInstance.setOnStop(() -> running = false);

        run();

    }

    private void run() {

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                gameInstance.update();
                gameInstance.render();

                if (!running) {
                    stop();
                    gameInstance.stop();
                    Platform.exit();
                }

            }
        };

        gameLoop.start();
    }

    public void kill() {
        running = false;
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
