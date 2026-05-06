package app.game;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import app.Vec2i;
import app.game.rendering.Cell;
import app.game.rendering.CellScreen;
import app.game.rendering.CharSet;
import app.game.world.Bot;
import app.game.world.Entity;
import app.game.world.RawMissionPackage;
import app.game.world.Room;
import app.game.world.Rover;
import app.game.world.Structure;
import app.game.world.WorldObject;
import app.game.world.RawWorldObject;

// import com.google.gson.Gson;

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
    private String name = "Mission";

    private String description = "A mission.";

    private List<WorldObject> worldObjects;

    /**
     * Constructor for a mission package. Loads the mission package from a JSON
     * file.
     * 
     * @param filename
     *                 Must be a JSON file located in /app/mission_packages/
     */
    public MissionPackage(String filename) {
        worldObjects = new ArrayList<WorldObject>();

        try {
            loadFromFile("/app/mission_packages/" + filename);
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Loaded mission package: " + this.name + ". Description: " + this.description);
    }

    /**
     * Loads the mission package from a JSON file.
     * 
     * @param filename
     * @throws JsonSyntaxException
     * @throws JsonIOException
     * @throws FileNotFoundException
     */
    private void loadFromFile(String filename) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        Gson gson = new Gson();

        InputStream is = getClass().getResourceAsStream(filename);

        // TODO: assign appropriate exceptions
        if (is == null) {
            throw new FileNotFoundException("Mission package file not found: " + filename);
        }

        Reader reader = new InputStreamReader(is);

        RawMissionPackage rawMissionPackage = gson.fromJson(reader, RawMissionPackage.class);

        this.name = rawMissionPackage.name;
        this.description = rawMissionPackage.description;

        for (RawWorldObject r : rawMissionPackage.worldObjects) {
            switch (r.type) {

                case "rover":
                    Rover rover = new Rover(r.position);
                    this.addWorldObject(rover);
                    break;

                case "room":
                    Room room = new Room(r.position, r.size, r.doors);
                    this.addWorldObject(room);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * Adds a world object to the mission package.
     * 
     * @param worldObject The world object to add.
     */
    public void addWorldObject(WorldObject worldObject) {
        worldObjects.add(worldObject);
    }

    /**
     * Updates all `WorldObject`s in the mission package.
     * 
     * @param deltatime The time elapsed since the last update.
     */
    public void update(double deltatime) {
        for (WorldObject worldObject : worldObjects) {
            if (worldObject instanceof Entity) {
                if (worldObject instanceof Rover)
                    ((Rover) worldObject).update(deltatime);
                else if (worldObject instanceof Bot)
                    ((Bot) worldObject).update(deltatime);
            } else if (worldObject instanceof Structure) {
                if (worldObject instanceof Room)
                    ((Room) worldObject).update(deltatime);
            }
        }
    }

    /**
     * Renders all <code>WorldObject</code>s in the mission package.
     * 
     * @param screen The screen to render to.
     * @param camera The camera position.
     */
    public void render(CellScreen screen, Vec2i camera) {
        // TODO: Render in appropriate order
        Vec2i screenCenter = screen.getSize().divide(2);
        for (WorldObject worldObject : worldObjects) {
            Vec2i pos = worldObject.getPosition().subtract(camera).add(screenCenter);
            worldObject.render().render(screen, pos);
        }

        renderBorder(screen);
    }

    /**
     * Renders a border around the screen with the mission name at the top.
     * 
     * @param screen The screen to render to.
     */
    private void renderBorder(CellScreen screen) {
        Vec2i bounds = screen.getSize();
        String hor = new String(new char[bounds.x - 2]).replace('\0', (char) CharSet.BOX_0101);

        screen.setCell(
                new Vec2i(0, 0),
                new Cell(CharSet.BOX_0110));
        screen.setCell(
                new Vec2i(bounds.x - 1, 0),
                new Cell(CharSet.BOX_0011));
        screen.setCell(
                new Vec2i(bounds.x - 1, bounds.y - 1),
                new Cell(CharSet.BOX_1001));
        screen.setCell(
                new Vec2i(0, bounds.y - 1),
                new Cell(CharSet.BOX_1100));

        screen.setString(new Vec2i(1, 0), hor);
        screen.setString(new Vec2i(1, bounds.y - 1), hor);

        for (int i = 1; i < bounds.y - 1; i++) {
            screen.setCell(
                    new Vec2i(0, i),
                    new Cell(CharSet.BOX_1010));
            screen.setCell(
                    new Vec2i(bounds.x - 1, i),
                    new Cell(CharSet.BOX_1010));
        }

        screen.setString(new Vec2i(1, 0), name);

    }

    /**
     * Saves the current state of the mission package. Saves to <code>/saves/</code>
     * with the name of the mission package and a timestamp. Filename will be in the
     * format <code>missionPackageName_timestamp.json</code>.
     */
    public void save() {
        // TODO: Write save() method
    }

    /**
     * Gets the name of the mission package.
     * 
     * @return The name of the mission package.
     */
    public String getName() {
        return name;
    }
}
