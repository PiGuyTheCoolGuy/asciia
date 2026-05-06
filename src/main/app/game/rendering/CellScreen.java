package app.game.rendering;

import app.InputHandler;
import app.Screen;
import app.Vec2i;
import javafx.geometry.Rectangle2D;

/**
 * A <code>Screen</code> that renders a grid of <code>Cell</code>s, which can be
 * used to create a terminal-like display.
 */
public class CellScreen extends Screen {

    private Terminal terminal;

    // TODO: base this off of `Texture`
    private Cell[][] cells;

    private static final Vec2i CELL_SIZE = new Vec2i(16, 16);

    private Vec2i size;

    /**
     * Creates a new <code>CellScreen</code> with the given title, input handler,
     * and display index.
     * 
     * @param title        The title of the screen.
     * @param input        The input handler for the screen.
     * @param displayIndex The index of the display to use.
     */
    public CellScreen(String title, InputHandler input, int displayIndex) {
        super(title,
                javafx.stage.Screen.getScreens().get(displayIndex).getBounds().getWidth(),
                javafx.stage.Screen.getScreens().get(displayIndex).getBounds().getHeight(),
                input);
        setup(displayIndex);

    }

    /**
     * Initializes the cell grid and terminal, and configures the window for
     * (pseudo) full-screen display on the specified monitor.
     * 
     * @param displayIndex The index of the display to use.
     */
    private void setup(int displayIndex) {

        size = new Vec2i((int) javafx.stage.Screen.getScreens().get(displayIndex).getBounds().getWidth(),
                (int) javafx.stage.Screen.getScreens().get(displayIndex).getBounds().getHeight());

        cells = new Cell[size.y / CELL_SIZE.y][size.x / CELL_SIZE.x];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }

        // try to load font
        try {
            BitmapFont testFont = new BitmapFont(
                    new javafx.scene.image.Image(
                            getClass().getResourceAsStream("/app/assets/fonts/vga2_16x16.png")),
                    16,
                    16);
            terminal = new Terminal(gc(), testFont, cells);
        } catch (Exception e) {
            System.err.println("Failed to load font: " + e.getMessage());
            e.printStackTrace();
        }

        fullScreen(displayIndex);
        configureWindow();

    }

    /**
     * Renders the cell grid to the window.
     */
    @Override
    public void render() {

        int rows = size.y / CELL_SIZE.y;
        int cols = size.x / CELL_SIZE.x;

        terminal.render(cols, rows);

    }

    /**
     * Configures the window for (pseudo) full-screen display on the specified
     * monitor.
     * 
     * @param displayIndex The index of the display to use.
     */
    private void fullScreen(int displayIndex) {

        stage.setFullScreenExitHint("");

        javafx.stage.Screen tv = javafx.stage.Screen.getScreens().get(displayIndex);
        Rectangle2D bounds = tv.getBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());

        stage.setMaximized(true);
        stage.setResizable(false);
    }

    /**
     * Returns the size of the cell grid in terms of columns and rows.
     * 
     * @return The size of the cell grid.
     */
    @Override
    public Vec2i getSize() {
        return new Vec2i(size.x / CELL_SIZE.x, size.y / CELL_SIZE.y);
    }

    /**
     * Clears all cells in the grid.
     */
    public void clear() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.clear();
            }
        }
    }

    /**
     * Sets the cell at the specified position.
     * 
     * @param pos  The position of the cell.
     * @param cell The cell to set.
     * @return The previous cell at the specified position, or null if the position
     *         is out of bounds.
     */
    public Cell setCell(Vec2i pos, Cell cell) {
        if (pos.y < 0 || pos.y >= cells.length || pos.x < 0 || pos.x >= cells[pos.y].length)
            return null;
        Cell old = cells[pos.y][pos.x];
        cells[pos.y][pos.x] = cell;
        return old;
    }

    /**
     * Sets the cell at the specified (x, y) coordinates.
     * 
     * @param x    The x-coordinate of the cell.
     * @param y    The y-coordinate of the cell.
     * @param cell The cell to set.
     * @return The previous cell at the specified coordinates, or null if the
     *         coordinates are out of bounds.
     */
    public Cell setCell(int x, int y, Cell cell) {
        if (y < 0 || y >= cells.length || x < 0 || x >= cells[y].length)
            return null;
        Cell old = cells[y][x];
        cells[y][x] = cell;
        return old;
    }

    /**
     * Sets a string of characters starting at the specified position, with optional
     * foreground and background colors.
     * 
     * @param pos    The starting position for the string.
     * @param string The string to set.
     */
    public void setString(Vec2i pos, String string) {
        setString(pos, string, Color.WHITE, Color.BLACK);
    }

    /**
     * Sets a string of characters starting at the specified position, with an
     * optional
     * foreground color.
     * 
     * @param pos    The starting position for the string.
     * @param string The string to set.
     * @param fg     The foreground color (default is white).
     */
    public void setString(Vec2i pos, String string, Color fg) {
        setString(pos, string, fg, Color.BLACK);
    }

    /**
     * Sets a string of characters starting at the specified position, with optional
     * foreground and background colors.
     * 
     * @param pos    The starting position for the string.
     * @param string The string to set.
     * @param fg     The foreground color.
     * @param bg     The background color.
     */
    public void setString(Vec2i pos, String string, Color fg, Color bg) {
        int x = pos.x;
        int y = pos.y;

        int xMax = cells[0].length;
        int yMax = cells.length;

        for (char i : string.toCharArray()) {
            if (x >= xMax) {
                x = 0;
                y++;
            }
            if (y >= yMax) {
                break;
            }
            cells[y][x].character = i;
            cells[y][x].fg = fg;
            cells[y][x].bg = bg;
            x++;
        }
    }

    /**
     * Draws a texture to the screen at the specified position. The texture is a
     * grid
     * of cells, and each cell in the texture is drawn to the corresponding position
     * on
     * the screen, offset by the specified position.
     * 
     * @param pos     The position on the screen to start drawing the texture.
     * @param texture The texture to draw.
     */
    public void drawTexture(Vec2i pos, Texture texture) {
        for (int y = 0; y < texture.getRows(); y++) {
            for (int x = 0; x < texture.getCols(); x++) {
                Cell cell = texture.getCell(x, y);
                setCell(pos.x + x, pos.y + y, cell);
            }
        }
    }

    /**
     * Sets a string of characters starting at the specified (x, y) coordinates,
     * with optional foreground and background colors.
     * 
     * @param x      The x-coordinate of the starting position for the string.
     * @param y      The y-coordinate of the starting position for the string.
     * @param string The string to set.
     */
    public void setString(int x, int y, String string) {
        setString(new Vec2i(x, y), string);
    }

    /**
     * Sets a string of characters starting at the specified (x, y) coordinates,
     * with an optional foreground color.
     * 
     * @param x      The x-coordinate of the starting position for the string.
     * @param y      The y-coordinate of the starting position for the string.
     * @param string The string to set.
     * @param fg     The foreground color (default is white).
     */
    public void setString(int x, int y, String string, Color fg) {
        setString(new Vec2i(x, y), string, fg);
    }

    /**
     * Sets a string of characters starting at the specified (x, y) coordinates,
     * with optional foreground and background colors.
     * 
     * @param x      The x-coordinate of the starting position for the string.
     * @param y      The y-coordinate of the starting position for the string.
     * @param string The string to set.
     * @param fg     The foreground color (default is white).
     * @param bg     The background color (default is black).
     */
    public void setString(int x, int y, String string, Color fg, Color bg) {
        setString(new Vec2i(x, y), string, fg, bg);
    }

    /**
     * Returns the number of columns in the cell grid.
     * 
     * @return The number of columns in the cell grid.
     */
    public int getCols() {
        return cells[0].length;
    }

    /**
     * Returns the number of rows in the cell grid.
     * 
     * @return The number of rows in the cell grid.
     */
    public int getRows() {
        return cells.length;
    }

    /**
     * Configures the window settings for the cell screen.
     */
    private void configureWindow() {
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        container.setStyle("-fx-background-color: black;");
    }

    /**
     * Shows the cell screen.
     * 
     * <strong style="color: red;">ONLY RUN THIS ONCE.</strong>
     */
    public void show() {
        stage.show();
        stage.toFront();
        stage.requestFocus();
    }

}
