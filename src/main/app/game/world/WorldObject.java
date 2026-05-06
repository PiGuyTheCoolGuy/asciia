package app.game.world;

import app.Vec2i;
import app.game.rendering.Texture;

/**
 * Represents a generic object in the game world. A world object can be
 * anything that exists in the game world, such as a rover, a structure, or
 * any other entity.
 */
public abstract class WorldObject {

    /**
     * Update the world object's state. This is where the object will decide what to
     * do, and update its state accordingly.
     * 
     * @param deltatime The time since the last update, in seconds.
     */
    public abstract void update(double deltatime);

    /**
     * Render the world object's texture.
     * 
     * @return The texture of the world object.
     */
    public abstract Texture render();

    protected Texture texture;

    protected Vec2i position = new Vec2i(0, 0);

    /**
     * Get the position of the world object.
     * 
     * @return The position of the world object.
     */
    public Vec2i getPosition() {
        return position;
    }
}
