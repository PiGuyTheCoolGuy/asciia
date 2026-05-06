package app.game.world;

/**
 * Represents an entity in the game world, whether player or NPC. Basically
 * anything that's not a structure for now.
 */
public abstract class Entity extends WorldObject {
    /**
     * Update the entity's state. This is where the entity will decide what to do,
     * and update its state accordingly.
     */
    public abstract void update();
}
