package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create two screens
        Screen dmScreen = new UIScreen("DM Screen", 0, 0, 800, 600);
        Screen playerScreen = new CellScreen("Player Screen");

        // Set basic content (placeholder for now)
        dmScreen.setContent("DM VIEW");
        playerScreen.setContent("PLAYER VIEW");

        // Show both windows
        dmScreen.show();
        playerScreen.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}