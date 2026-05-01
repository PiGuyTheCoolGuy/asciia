package app.game.world;

import app.game.rendering.Cell;
import app.game.rendering.CharSet;
import app.game.rendering.Texture;

public class Rover extends WorldObject {
    public Rover() {
        texture = new Texture(new Cell[][] { new Cell[] { new Cell(CharSet.SMILEY_BLACK) } });
    }

    public void update(double deltatime) { // XXX
    }
}
