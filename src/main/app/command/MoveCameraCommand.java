package app.command;

import app.Vec2i;
import app.game.GameInstance;

/**
 * A command that moves the camera from a start position to a target position
 * over a specified duration.
 */
public class MoveCameraCommand implements Command {

    private final Vec2i start;
    private final Vec2i target;
    private final double duration;

    private double elapsed = 0;

    /**
     * Creates a new MoveCameraCommand.
     * 
     * @param start    The starting position of the camera.
     * @param target   The target position of the camera.
     * @param duration The duration of the camera movement in seconds.
     */
    public MoveCameraCommand(Vec2i start, Vec2i target, double duration) {
        this.start = start;
        this.target = target;
        this.duration = duration;
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

}
