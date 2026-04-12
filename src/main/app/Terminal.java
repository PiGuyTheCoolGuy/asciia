package app;

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

        gc.setFill(javafx.scene.paint.Color.BLACK);

        int i = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {

                i = (y * cols + x) % 256; // Just for testing, cycle through ASCII chars

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
                        (char) i,
                        x * font.getGlyphWidth(),
                        y * font.getGlyphHeight());
                i++;
            }
        }
    }
}
