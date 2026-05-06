package app.game.world;

import app.Vec2i;

/**
 * This is a remplate for a real <code>WorldObject</code>. It has all
 * attributes of all implementations of <code>WorldObject</code>, but all
 * attributes are public and there are no methods. It is temporary and only for
 * loading from JSON.
 */
public class RawWorldObject {

    public String type;

    public Vec2i position;

    public Vec2i size;

    public Vec2i[] doors;

}
