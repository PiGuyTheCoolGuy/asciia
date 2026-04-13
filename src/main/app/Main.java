package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        System.out.println(getClass().getResource("/"));
        // list resources in the root directory try {
        try {
            java.net.URL url = getClass().getResource("/app");
            System.out.println("URL: " + url);
            java.io.File dir = new java.io.File(url.toURI());
            System.out.println("Files in resource directory:");
            for (java.io.File file : dir.listFiles()) {
                System.out.println(file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
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