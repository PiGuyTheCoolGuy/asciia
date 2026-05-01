package app.game.world;

import app.Vec2i;
import app.game.rendering.Texture;

public abstract class WorldObject {
    public abstract void update(double deltatime);

    protected Texture texture;

    protected Vec2i position = new Vec2i(0, 0);

    public Texture getTexture() {
        return texture;
    }

    public Vec2i getPosition() {
        return position;
    }
}
