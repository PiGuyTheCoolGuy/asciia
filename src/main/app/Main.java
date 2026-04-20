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
        UIScreen dmScreen = new UIScreen("DM Screen", 0, 0, 800, 600);
        CellScreen playerScreen = new CellScreen("Player Screen");

        int row = 0;
        int col = 0;
        for (int i = 0; i < 256; i++) {
            if (row >= playerScreen.getRows()) {
                row = 0;
                col += 8;
            }
            if (col >= playerScreen.getCols()) {
                break;
            }
            char c = (char) i;
            playerScreen.setString(col, row, c + " " + Integer.toHexString(i));
            row += 2;
        }

        // Texture testTexture = new Texture("logo.txr");
        // playerScreen.drawTexture(new Vec2i(10, 10), testTexture);

        // Show both windows
        dmScreen.show();
        playerScreen.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}