package app;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import app.game.GameInstance;
import app.ui.ControlPanel;
import javafx.application.Platform;

public class Main extends Application {

    private GameInstance gameInstance;
    private ControlPanel controlPanel;

    private boolean running = true;

    @Override
    public void start(Stage primaryStage) {
        controlPanel = new ControlPanel(() -> running = false, gameInstance);
        int displayIndex = controlPanel.selectGameInstanceDisplay();
        System.out.println("Selected display: " + displayIndex);

        // TODO: figure out where the mission package is coming from
        gameInstance = new GameInstance("mission.json", true, () -> running = false,
                displayIndex, controlPanel);

        controlPanel.setGameInstance(gameInstance);

        run();

    }

    private void run() {

        AnimationTimer gameLoop = new AnimationTimer() {

            private long lastTime = 0;

            @Override
            public void handle(long now) {

                double deltatime = 0;

                if (lastTime >= 0) {
                    deltatime = (now - lastTime) / 1e9;
                }

                lastTime = now;

                gameInstance.update(deltatime);
                gameInstance.render();

                controlPanel.update(deltatime);
                controlPanel.render();

                if (!running) {
                    stop();
                    gameInstance.stop();
                    controlPanel.stop();
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
