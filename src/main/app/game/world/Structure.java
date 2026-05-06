package app.game.world;

/**
 * Represents a structure in the game world, such as a room or a building. A
 * structure is a type of world object that has a specific shape and can be
 * interacted with by entities.
 */
public abstract class Structure extends WorldObject {
    /**
     * Update the structure's state. This is where the structure will decide what to
     * do, and update its state accordingly.
     * 
     * @param deltatime The time since the last update, in seconds.
     */
    public abstract void update(double deltatime);
}
