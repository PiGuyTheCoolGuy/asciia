package app.command;

import app.Vec2i;
import app.game.GameInstance;
import javafx.scene.layout.VBox;

/**
 * A command that moves the camera from a start position to a target position
 * over a specified duration.
 */
public class MoveCameraCommand extends Command {

    private Vec2i start;
    private final Vec2i target;
    private final double duration;

    private double elapsed = 0;

    private boolean started = false;

    // * @param start The starting position of the camera.
    /**
     * Creates a new MoveCameraCommand.
     * 
     * @param target      The target position of the camera.
     * @param duration    The duration of the camera movement in seconds.
     * @param uiContainer The UI container to which the command's UI elements should
     *                    be added.
     */
    // public MoveCameraCommand(Vec2i start, Vec2i target, double duration, VBox
    // uiContainer) {
    public MoveCameraCommand(Vec2i target, double duration, VBox uiContainer) {
        super(uiContainer);

        // this.start = start;
        this.target = target;
        this.duration = duration;

        type.setText("Camera position update");
        createdTimestamp.setText(String.valueOf(System.currentTimeMillis()));
        startedTimestamp.setText("---");
        status.setText("Pending execution");
        description.setText(String.format("Start: (---, ---)\nTarget: (%d, %d)\nCurrent: (---, ---)",
                target.x, target.y));

        panel.getChildren().addAll(type, createdTimestamp, startedTimestamp, status, description);
        uiContainer.getChildren().add(panel);
    }

    /**
     * Updates the command, moving the camera towards the target position based on
     * the elapsed time and the specified duration. The movement is eased using a
     * smoothstep function for a smoother transition.
     * 
     * @param game      The game instance.
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(GameInstance game, double deltaTime) {
        elapsed += deltaTime;

        if (!started) {
            start = game.getCamera();
            started = true;
        }

        if (startedTimestamp.getText().equals("---")) {
            startedTimestamp.setText(String.valueOf(System.currentTimeMillis()));
            status.setText("Executing");
        }

        description.setText(String.format("Start: (%d, %d)\n Target: (%d, %d)\n Current: (%d, %d)",
                start.x, start.y, target.x, target.y, game.getCamera().x, game.getCamera().y));

        // normalize time (0 → 1)
        double t = Math.min(elapsed / duration, 1.0);

        // smoothstep easing (ease in-out)
        double smoothT = t * t * (3 - 2 * t);

        // lerp using eased value
        int x = (int) (start.x + (target.x - start.x) * smoothT);
        int y = (int) (start.y + (target.y - start.y) * smoothT);

        game.setCamera(new Vec2i(x, y));
    }

    /**
     * Checks if the command has finished executing.
     * 
     * @return true if the command is finished, false otherwise.
     */
    @Override
    public boolean isFinished() {
        return elapsed >= duration;
    }

    /**
     * Called when the command has finished executing.
     * 
     * @param game The game instance.
     */
    @Override
    public void finish(GameInstance game) {
        uiContainer.getChildren().remove(panel);
        status.setText("Finished");
    }

}
