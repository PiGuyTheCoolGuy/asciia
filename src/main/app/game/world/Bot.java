package app.game.world;

import app.game.rendering.Texture;

/**
 * I'm not super sure what this will become yet, but right now I think it is
 * going to be any kind of NPC.
 * 
 * TODO: Come up with this.
 */
public class Bot extends WorldObject {
    /**
     * Update every frame. This is where the bot will decide what to do, and update
     * its state accordingly.
     * 
     * @param deltatime The time since the last update, in seconds.
     */
    public void update(double deltatime) { // XXX
    }

    /**
     * Render the bot. This is where the bot's appearance will be determined.
     * Eventually, this will select a texture from the aniamation or something. Idk
     * yet but it'll be pretty epic.
     * 
     * @return The texture to render for this bot.
     */
    public Texture render() { // XXX
        return texture;
    }
}
