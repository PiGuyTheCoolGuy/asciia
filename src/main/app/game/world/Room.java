package app.game.world;

import app.Vec2i;
import app.game.rendering.Cell;
import app.game.rendering.CharSet;
import app.game.rendering.Texture;

/**
 * Represents a room in the game world. A room is a type of structure with a
 * specific size and doors.
 */
public class Room extends Structure {

    private Vec2i size;

    private Vec2i[] doors;

    /**
     * Create a new room with the given position, size, and doors.
     * 
     * @param position The position of the room in the game world.
     * @param size     The size of the room.
     * @param doors    The positions of the doors in the room.
     */
    public Room(Vec2i position, Vec2i size, Vec2i[] doors) {
        super();
        this.position = position;
        this.size = size;
        this.doors = doors;

        buildTexture();
    }

    /**
     * Build the texture for the room based on its size and doors.
     */
    private void buildTexture() {
        Cell[][] cells = new Cell[size.x][size.y];

        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                cells[x][y] = new Cell(CharSet.BOX_0000);
            }
        }

        cells[0][0] = new Cell(CharSet.BOX_0220);
        cells[size.x - 1][0] = new Cell(CharSet.BOX_0022);
        cells[0][size.y - 1] = new Cell(CharSet.BOX_2200);
        cells[size.x - 1][size.y - 1] = new Cell(CharSet.BOX_2002);

        for (int x = 1; x < size.x - 1; x++) {
            cells[x][0] = new Cell(CharSet.BOX_0202);
            cells[x][size.y - 1] = new Cell(CharSet.BOX_0202);
        }

        for (int y = 1; y < size.y - 1; y++) {
            cells[0][y] = new Cell(CharSet.BOX_2020);
            cells[size.x - 1][y] = new Cell(CharSet.BOX_2020);
        }

        for (Vec2i door : doors) {
            cells[door.x][door.y] = new Cell(CharSet.BOX_0000);
        }

        this.texture = new Texture(cells);
    }

    /**
     * Update the room's state. This is where the room will decide what to do,
     * and update its state accordingly.
     * 
     * @param deltatime The time since the last update, in seconds.
     */
    public void update(double deltatime) { // XXX
    }

    /**
     * Render the room's texture.
     * 
     * @return The texture of the room.
     */
    public Texture render() { // XXX
        return texture;
    }

}
