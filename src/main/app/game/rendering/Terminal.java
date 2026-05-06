package app.game.rendering;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A terminal for rendering a grid of cells using a bitmap font.
 */
public class Terminal {

    private final GraphicsContext gc;
    private final BitmapFont font;

    private final Cell[][] cells;

    private final GlyphCache glyphCache;

    /**
     * Creates a new Terminal for rendering a grid of cells using the given graphics
     * context and bitmap font.
     * 
     * @param gc    the graphics context to use for rendering
     * @param font  the bitmap font to use for rendering glyphs
     * @param cells the grid of cells to render
     */
    public Terminal(GraphicsContext gc, BitmapFont font, Cell[][] cells) {
        this.gc = gc;
        this.font = font;
        this.cells = cells;
        this.glyphCache = new GlyphCache(font);
    }

    /**
     * Renders the terminal by drawing each cell's character using the appropriate
     * foreground and background colors. The glyphs are retrieved from the glyph
     * cache to improve performance.
     * 
     * @param cols the number of columns to render
     * @param rows the number of rows to render
     */
    public void render(int cols, int rows) {

        gc.clearRect(0, 0,
                cols * font.getGlyphWidth(),
                rows * font.getGlyphHeight());

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {

                Cell c = cells[y][x];

                Image img = glyphCache.getGlyph(
                        c.character,
                        c.fg,
                        c.bg);

                gc.drawImage(
                        img,
                        x * font.getGlyphWidth(),
                        y * font.getGlyphHeight());
            }
        }
    }

    /**
     * Clears the terminal by resetting all cells to their default state.
     */
    public void clear() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.clear();
            }
        }
    }
}
