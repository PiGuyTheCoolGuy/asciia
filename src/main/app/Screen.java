package app;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Represents a screen in the application. This is a base class for all screens
 * in the application, such as the game screen, the main menu, etc. A screen is
 * responsible for rendering its contents and handling input. It also provides
 * methods for managing the screen, such as showing, hiding, and closing the
 * screen.
 */
public abstract class Screen {

    protected Stage stage;
    private Scene scene;
    protected Canvas root;

    protected javafx.scene.layout.Pane container;

    /**
     * Create a new screen with the given title, width, height, and input handler.
     * 
     * @param title  The title of the screen, which will be displayed in the window
     *               title bar.
     * @param width  The width of the screen, in pixels.
     * @param height The height of the screen, in pixels.
     * @param input  The input handler for the screen, which will be used to handle
     *               user input.
     */
    public Screen(String title, double width, double height, InputHandler input) {
        stage = new Stage();
        stage.setTitle(title);

        root = new Canvas(width, height);
        root.setLayoutX(0);
        root.setLayoutY(0);
        container = new javafx.scene.layout.Pane();
        container.getChildren().add(root);
        scene = new Scene(container, width, height);

        // configureWindow();

        stage.setScene(scene);
        // stage.show();

        configInput(input);

    }

    /**
     * Create a new screen with the given title and input handler. This constructor
     * will use a default width of 800 pixels and a default height of 600 pixels.
     * 
     * @param title The title of the screen, which will be displayed in the window
     *              title bar.
     * @param input The input handler for the screen, which will be used to handle
     *              user input.
     */
    public Screen(String title, InputHandler input) {
        this(title, 800, 600, input);
    }

    /**
     * Render the screen. This method will be called every frame to render the
     * contents of the screen. Subclasses should override this method to provide
     * their own
     * implementation.
     */
    public abstract void render();

    /**
     * Get the graphics context for the screen. This method will return the graphics
     * context for the root canvas, which can be used to draw on the screen. This
     * method is protected, as it is intended to be used by subclasses when
     * rendering the screen.
     * 
     * @return The graphics context for the root canvas.
     */
    protected GraphicsContext gc() {
        return root.getGraphicsContext2D();
    }

    /**
     * Set the screen to fullscreen mode. This will make the screen take up the
     * entire screen, hiding the taskbar and other windows. This can be useful for
     * immersive gameplay, but may not be suitable for all applications.
     * 
     * @param value True to set the screen to fullscreen mode, false to exit
     *              fullscreen mode.
     */
    public void fullscreen(boolean value) {
        stage.setFullScreen(value);
    }

    /**
     * Get the size of the screen, in pixels. This method will return a Vec2i
     * containing the width and height of the screen. This can be useful for layout
     * and rendering purposes.
     * 
     * @return A Vec2i containing the width and height of the screen.
     */
    public Vec2i getSize() {
        return new Vec2i((int) scene.getWidth(), (int) scene.getHeight());
    }

    /**
     * Get the stage for the screen. This method will return the JavaFX stage that
     * the screen is using. This can be useful for managing the screen, such as
     * showing, hiding, or closing the screen.
     * 
     * @return The JavaFX stage for the screen.
     */
    protected Stage getStage() {
        return stage;
    }

    /**
     * Show the screen. This will make the screen visible on the stage. This method
     * can be overridden by subclasses to provide additional functionality when
     * showing the screen, such as initializing certain elements or starting
     * animations.
     * 
     * @param input The input handler for the screen, which can be used to handle
     *              user input when
     */
    private void configInput(InputHandler input) {
        scene.setOnKeyPressed(e -> input.keyPressed(e.getCode()));
        scene.setOnKeyReleased(e -> input.keyReleased(e.getCode()));
    }

    /**
     * Close the screen. This will close the stage and hide the screen. This method
     * can be overridden by subclasses to provide additional functionality when
     * closing the screen, such as stopping animations or saving game state.
     */
    public void close() {
        stage.close();
    }
}
