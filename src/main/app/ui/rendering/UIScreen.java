package app.ui.rendering;

import java.util.List;

import app.InputHandler;
import app.Screen;
import app.Vec2i;
import app.command.MoveCameraCommand;
import app.ui.ControlPanel;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

    private Label cameraLabel;
    private VBox cameraPanel;
    private TextField cameraXField;
    private TextField cameraYField;

    private ControlPanel controlPanel;

    public UIScreen(String title, InputHandler input, ControlPanel controlPanel) {
        super(title, input);
        this.controlPanel = controlPanel;

        //

        cameraPanel = new VBox(5);
        cameraPanel.setStyle(
                "-fx-border-color: lime;" +
                        "-fx-border-width: 1;" +
                        "-fx-padding: 8;" +
                        "-fx-background-color: black;");

        // --- Label (live camera position)
        cameraLabel = new Label("Camera: (0, 0)");
        cameraLabel.setStyle("-fx-text-fill: white; -fx-font-family: monospace;");

        // --- Input fields
        cameraXField = new TextField();
        cameraXField.setPromptText("X");

        cameraYField = new TextField();
        cameraYField.setPromptText("Y");

        // Optional: make them smaller
        cameraXField.setPrefWidth(60);
        cameraYField.setPrefWidth(60);

        // Put inputs side-by-side
        HBox inputRow = new HBox(5, cameraXField, cameraYField);

        // --- Submit button
        Button submit = new Button("Set Camera");

        submit.setOnAction(e -> {
            /*
             * try {
             * int x = Integer.parseInt(cameraXField.getText());
             * int y = Integer.parseInt(cameraYField.getText());
             * 
             * controlPanel.setCamera(new Vec2i(x, y)); // adjust to your API
             * } catch (NumberFormatException ex) {
             * System.out.println("Invalid camera input");
             * }
             */

            int x = Integer.parseInt(cameraXField.getText());
            int y = Integer.parseInt(cameraYField.getText());

            controlPanel.enqueueCommand(new MoveCameraCommand(controlPanel.getCamera(), new Vec2i(x, y), 1.5));

        });

        // --- Assemble panel
        cameraPanel.getChildren().addAll(cameraLabel, inputRow, submit);

        // Add to your existing container
        container.getChildren().add(cameraPanel);

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

        Vec2i cam = controlPanel.getCamera();

        cameraLabel.setText("Camera: (" + cam.x + ", " + cam.y + ")");

    }

}
