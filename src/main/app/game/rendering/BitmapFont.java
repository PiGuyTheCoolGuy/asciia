package app.game.rendering;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 * A simple bitmap font renderer. It assumes that the font atlas is arranged in
 * a grid, where each cell corresponds to a character. The characters are
 * indexed by their ASCII values, starting from the top-left corner of the
 * atlas.
 */
public class BitmapFont {

    private final Image atlas;

    private final int glyphWidth;
    private final int glyphHeight;

    Color fg = Color.WHITE;

    private final int cols;
    private final int rows;

    /**
     * Creates a new BitmapFont instance.
     * 
     * @param atlas       The image containing the font atlas.
     * @param glyphWidth  The width of each glyph in pixels.
     * @param glyphHeight The height of each glyph in pixels.
     */
    public BitmapFont(Image atlas, int glyphWidth, int glyphHeight) {
        this.atlas = atlas;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;

        this.cols = (int) (atlas.getWidth() / glyphWidth);
        this.rows = (int) (atlas.getHeight() / glyphHeight);
    }

    /**
     * Draws a character at the specified position.
     * 
     * @param gc The graphics context to draw on.
     * @param c  The character to draw.
     * @param x  The x-coordinate of the top-left corner of the character.
     * @param y  The y-coordinate of the top-left corner of the character.
     */
    public void drawRaw(GraphicsContext gc, char c, int x, int y) {
        int index = (int) c;

        int srcX = (index % cols) * glyphWidth;
        int srcY = (index / cols) * glyphHeight;

        gc.drawImage(
                atlas,
                srcX, srcY, glyphWidth, glyphHeight,
                x, y, glyphWidth, glyphHeight);
    }

    /**
     * Gets the width of each glyph in pixels.
     * 
     * @return The width of each glyph in pixels.
     */
    public int getGlyphWidth() {
        return glyphWidth;
    }

    /**
     * Gets the height of each glyph in pixels.
     * 
     * @return The height of each glyph in pixels.
     */
    public int getGlyphHeight() {
        return glyphHeight;
    }

    /**
     * Gets the total number of glyphs in the font atlas.
     * 
     * @return The total number of glyphs.
     */
    public int getLength() {
        return cols * rows;
    }
}