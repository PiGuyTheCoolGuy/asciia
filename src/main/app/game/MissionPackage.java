package app.game;

import java.util.Vector;

import app.game.world.Bot;
import app.game.world.Entity;
import app.game.world.Room;
import app.game.world.Rover;
import app.game.world.Structure;
import app.game.world.WorldObject;

/**
 * - A mission package is the Asciia equivalent of a campaign.
 * - It is played by ground control (the players in real life) talking to a
 * relay space station (the person acting as DM).
 * - Ground control must guide the rovers (players in the game) to carry out a
 * mission.
 * - A mission is broken into objectives.
 * - Objectives are small tasks that must be completed in order to complete the
 * overall mission and return home.
 * - A mission package contains a map, rovers, NPCs, items, and various other
 * elements that can be interacted with.
 * - A mission begins at a drop point (starting location on the map), and the
 * rovers will go to different places to collect materials and complete
 * objectives.
 * - Some objectives will be harder than others, so it is important that ground
 * control strategizes and plans out the best way to complete the mission.
 * - Objectives will become easier as more materials are collected, so it is
 * important to prioritize objectives and collect materials as early as
 * possible.
 * - Once all objectives are completed, the rovers must return to the drop point
 * to repair the ship and return home.
 */
public class MissionPackage {
    private String name;

    private Vector<WorldObject> worldObjects;

    public MissionPackage(String name, boolean fromFile) {
        if (fromFile) {
            loadFromFile(name);
        } else {
            this.name = name;
        }
        worldObjects = new Vector<>();
    }

    private void loadFromFile(String fileName) {
        // TODO: Implement loading `MissionPackage` from file
        name = "IMPLEMENT FILE STUFF!!";
    }

    public void addWorldObject(WorldObject worldObject) {
        worldObjects.add(worldObject);
    }

    public void update() {
        for (WorldObject worldObject : worldObjects) {
            if (worldObject instanceof Entity) {
                if (worldObject instanceof Rover)
                    ((Rover) worldObject).update();
                else if (worldObject instanceof Bot)
                    ((Bot) worldObject).update();
            } else if (worldObject instanceof Structure) {
                if (worldObject instanceof Room)
                    ((Room) worldObject).update();
            }
        }
    }

    public void render() { // XXX
        for (WorldObject worldObject : worldObjects)
            worldObject.render();

    }

    public void save() { // XXX
    }

    public String getName() {
        return name;
    }
}
