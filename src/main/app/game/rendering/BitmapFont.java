package app.game.rendering;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class BitmapFont {

    private final Image atlas;

    private final int glyphWidth;
    private final int glyphHeight;

    Color fg = Color.WHITE;

    private final int cols;
    private final int rows;

    public BitmapFont(Image atlas, int glyphWidth, int glyphHeight) {
        this.atlas = atlas;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;

        this.cols = (int) (atlas.getWidth() / glyphWidth);
        this.rows = (int) (atlas.getHeight() / glyphHeight);
    }

    public void drawRaw(GraphicsContext gc, char c, int x, int y) {
        int index = (int) c;

        int srcX = (index % cols) * glyphWidth;
        int srcY = (index / cols) * glyphHeight;

        gc.drawImage(
                atlas,
                srcX, srcY, glyphWidth, glyphHeight,
                x, y, glyphWidth, glyphHeight);
    }

    public int getGlyphWidth() {
        return glyphWidth;
    }

    public int getGlyphHeight() {
        return glyphHeight;
    }

    public int getLength() {
        return cols * rows;
    }
}