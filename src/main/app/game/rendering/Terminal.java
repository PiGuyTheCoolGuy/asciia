package app.game.rendering;

import javafx.scene.canvas.GraphicsContext;

public class Terminal {

    private final GraphicsContext gc;
    private final BitmapFont font;

    private final Cell[][] cells;

    public Terminal(GraphicsContext gc, BitmapFont font, Cell[][] cells) {
        this.gc = gc;
        this.font = font;
        this.cells = cells;
    }

    public void render(int cols, int rows) {

        gc.clearRect(0, 0,
                cols * font.getGlyphWidth(),
                rows * font.getGlyphHeight());

        // clear entire canvas to black
        gc.setFill(javafx.scene.paint.Color.BLACK);
        int h = (int) gc.getCanvas().getHeight();
        int w = (int) gc.getCanvas().getWidth();
        gc.fillRect(0, 0, w, h);

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {

                Cell c = cells[y][x];

                // background
                gc.setFill(c.bColor);
                gc.fillRect(
                        x * font.getGlyphWidth(),
                        y * font.getGlyphHeight(),
                        font.getGlyphWidth(),
                        font.getGlyphHeight());

                // character
                font.drawChar(gc,
                        c.character,
                        x * font.getGlyphWidth(),
                        y * font.getGlyphHeight());
            }
        }
    }

    public void clear() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.clear();
            }
        }
    }
}
