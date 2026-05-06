package app.game.world;

import app.Vec2i;
import app.game.rendering.CharSet;
import app.game.rendering.Texture;

/**
 * Represents a rover in the game world. A rover is a type of world object
 * that can move around and interact with the environment.
 */
public class Rover extends WorldObject {
    /**
     * Create a new rover at the given position.
     * 
     * @param position The position of the rover in the game world.
     */
    public Rover(Vec2i position) {
        this.position = position;
        // texture = new Texture(new Cell[][] { new Cell[] { new
        // Cell(CharSet.SMILEY_BLACK) } });
        texture = new Texture(CharSet.SMILEY_BLACK);
    }

    /**
     * Render the rover's texture.
     * 
     * @return The texture of the rover.
     */
    public Texture render() {
        return texture;
    }

    /**
     * Update the rover's state. This is where the rover will decide what to do,
     * and update its state accordingly.
     * 
     * @param deltatime The time since the last update, in seconds.
     */
    public void update(double deltatime) { // XXX
    }
}
