package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        System.out.println(getClass().getResource("/"));
        // list all files and directoriesin directory above
        System.out.println("Available resources:");
        try {
            java.nio.file.Files.walk(java.nio.file.Paths.get(getClass().getResource("/").toURI()))
                    .filter(java.nio.file.Files::isRegularFile)
                    .forEach(path -> System.out.println(" - " + path.getFileName()));
        } catch (Exception e) {
            System.err.println("Error listing resources: " + e.getMessage());
        }

        // Create two screens
        Screen dmScreen = new UIScreen("DM Screen", 0, 0, 800, 600);
        Screen playerScreen = new CellScreen("Player Screen");

        // Show both windows
        dmScreen.show();
        playerScreen.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}