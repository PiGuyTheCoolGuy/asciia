package app;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import app.game.GameInstance;
import app.ui.ControlPanel;
import javafx.application.Platform;

/**
 * The main class of the application. This class is responsible for launching
 * the application, creating the game instance and control panel, and managing
 * the game loop.
 */
public class Main extends Application {

    private GameInstance gameInstance;
    private ControlPanel controlPanel;

    private boolean running = true;

    /**
     * Start the application. This method will create the game instance and control
     * panel, and start the game loop.
     * 
     * @param primaryStage The primary stage for the application, which will be used
     *                     to display the game instance and control panel.
     */
    @Override
    public void start(Stage primaryStage) {
        controlPanel = new ControlPanel(() -> running = false, gameInstance);
        int displayIndex = controlPanel.selectGameInstanceDisplay();

        // TODO: figure out where the mission package is coming from
        gameInstance = new GameInstance("mission.json", () -> running = false,
                displayIndex, controlPanel);

        controlPanel.setGameInstance(gameInstance);

        gameInstance.show();

        run();

    }

    /**
     * The main game loop. This loop will run until the game is stopped, and will
     * update and render the game instance and control panel every frame. The loop
     * will also calculate the deltatime for each frame, which can be used for
     * time-based movement and animations.
     */
    private void run() {

        AnimationTimer gameLoop = new AnimationTimer() {

            private long lastTime = 0;

            /**
             * The main loop method, which is called every frame. This method will calculate
             * the deltatime, update and render the game instance and control panel, and
             * check if the game is still running. If the game is no longer running, it will
             * stop the loop and exit the application.
             */
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

    /**
     * Kills the application by setting the running flag to false and exiting the
     * platform.
     */
    public void kill() {
        running = false;
        Platform.exit();
    }

    /**
     * The main method, which is the entry point of the application. This method
     * will launch the JavaFX application, which will call the start method to
     * initialize the game instance and control panel, and start the game loop.
     * 
     * @param args The command line arguments, which are not used in this
     *             application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
