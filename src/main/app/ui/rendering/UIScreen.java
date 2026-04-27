package app.ui.rendering;

import java.util.List;

import app.InputHandler;
import app.Screen;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.robot.Robot;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UIScreen extends Screen {

    // public UIScreen(String title, double width, double height, InputHandler
    // input) {
    // super(title, width, height, input);
    // }

    public UIScreen(String title, InputHandler input) {
        super(title, input);

        Button button = new Button("Test");
        button.setOnAction(null);
        button.setLayoutX(50);
        button.setLayoutY(50);
        container.getChildren().add(button);

    }

    public int selectGameInstanceDisplay() {

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Select Display");

        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(10));

        ToggleGroup group = new ToggleGroup();

        List<javafx.stage.Screen> screens = javafx.stage.Screen.getScreens();

        final int[] result = { -1 };

        for (int i = 0; i < screens.size(); i++) {

            Rectangle2D bounds = screens.get(i).getBounds();

            Robot robot = new Robot();

            WritableImage capture = robot.getScreenCapture(
                    null,
                    bounds);

            // --- Row container ---
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);

            // --- Radio button ---
            RadioButton rb = new RadioButton();
            rb.setToggleGroup(group);
            rb.setUserData(i);

            if (i == 0) {
                rb.setSelected(true);
                result[0] = 0;
            }

            // --- "Live preview" placeholder ---
            // Rectangle preview = new Rectangle(100, 60);
            // preview.setFill(javafx.scene.paint.Color.BLACK);
            // preview.setStroke(javafx.scene.paint.Color.LIMEGREEN);
            ImageView preview = new ImageView(capture);
            preview.setFitWidth(120);
            preview.setFitHeight(70);
            preview.setPreserveRatio(true);

            // --- Text info ---
            VBox text = new VBox(2);
            javafx.scene.control.Label name = new javafx.scene.control.Label("Display " + i);
            javafx.scene.control.Label res = new javafx.scene.control.Label(
                    (int) bounds.getWidth() + " x " + (int) bounds.getHeight());

            text.getChildren().addAll(name, res);

            // --- Selection sync ---
            rb.setOnAction(e -> result[0] = (int) rb.getUserData());

            row.getChildren().addAll(rb, preview, text);
            layout.getChildren().add(row);
        }

        // --- Submit button ---
        Button submit = new Button("Start");

        submit.setOnAction(e -> {
            Toggle selected = group.getSelectedToggle();
            if (selected != null) {
                result[0] = (int) selected.getUserData();
            }
            dialog.close();
        });

        layout.getChildren().add(submit);

        Scene scene = new Scene(layout, 350, 200);
        dialog.setScene(scene);

        dialog.showAndWait();

        return result[0];
    }

    @Override
    public void render() {
        GraphicsContext gc = gc();
        gc.clearRect(0, 0, getSize().x, getSize().y);
        // Render UI elements here

    }

}
