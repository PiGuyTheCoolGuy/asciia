package app.game.world;

import app.Vec2i;
import app.game.rendering.CharSet;
import app.game.rendering.Texture;

public class Rover extends WorldObject {
    public Rover(Vec2i position) {
        this.position = position;
        // texture = new Texture(new Cell[][] { new Cell[] { new
        // Cell(CharSet.SMILEY_BLACK) } });
        texture = new Texture(CharSet.SMILEY_BLACK);
    }

    public Texture render() {
        return texture;
    }

    public void update(double deltatime) { // XXX
    }
}
