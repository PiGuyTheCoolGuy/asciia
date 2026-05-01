package app.command;

import app.Vec2i;
import app.game.GameInstance;

public class MoveCameraCommand implements Command {

    private final Vec2i start;
    private final Vec2i target;
    private final double duration;

    private double elapsed = 0;

    public MoveCameraCommand(Vec2i start, Vec2i target, double duration) {
        this.start = start;
        this.target = target;
        this.duration = duration;
    }

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

    @Override
    public boolean isFinished() {
        return elapsed >= duration;
    }

}
