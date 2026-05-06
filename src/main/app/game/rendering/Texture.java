package app.game.rendering;

import app.Vec2i;

/**
 * A texture is a grid of cells that can be rendered on a screen.
 */
public class Texture {
    private Cell[][] cells;

    /**
     * Creates a new Texture with the given width and height. The texture will be
     * initialized with default cells (space character, white foreground, black
     * background).
     * 
     * @param width  the width of the texture
     * @param height the height of the texture
     */
    public Texture(int width, int height) {
        cells = new Cell[height][width];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    /**
     * Creates a new Texture with a single cell containing the specified character
     * and default colors.
     * 
     * @param character the character to display in the single cell
     */
    public Texture(int character) {
        cells = new Cell[1][1];
        cells[0][0] = new Cell(character);
    }

    /**
     * Creates a new Texture with the given grid of cells. The cells will be copied
     * to ensure that the texture has its own independent state.
     * 
     * @param cells the grid of cells to use for the texture
     */
    public Texture(Cell[][] cells) {
        this.cells = cells;
    }

    /**
     * Creates a new Texture by loading it from a file. The file should be a grid of
     * 2-digit hex values representing ASCII codes, separated by spaces, with one
     * row per line. For example:
     * 
     * <pre>
     * 20 20 20 20 20
     * 20 41 42 43 20
     * 20 44 45 46 20
     * 20 47 48 49 20
     * 20 20 20 20 20
     * </pre>
     * 
     * This would create a texture with a border of spaces and the characters 'A' to
     * 'I' in the center.
     * 
     * @param filename the name of the file to load the texture from (relative to
     *                 /app/assets/textures/)
     */
    public Texture(String filename) { // TODO: Handle exceptions properly
        java.io.File file;
        // load from /app/assets/textures/filename
        try {
            java.net.URL url = getClass().getResource("/app/assets/textures/" + filename);
            if (url == null) {
                throw new RuntimeException("Texture file not found: " + filename);
            }
            file = new java.io.File(url.toURI());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load texture: " + filename, e);
        }

        // file will be a grid of 2 digit hex values representing ASCII codes, separated
        // by spaces, one row per line
        try (java.util.Scanner scanner = new java.util.Scanner(file)) {
            java.util.List<Cell[]> rows = new java.util.ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(" ");
                Cell[] row = new Cell[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    int asciiCode = Integer.parseInt(tokens[i], 16);
                    row[i] = new Cell((char) asciiCode); // default white color
                }
                rows.add(row);
            }
            cells = rows.toArray(new Cell[0][]);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse texture file: " + filename, e);
        }
    }

    /**
     * Returns the cell at the specified coordinates. The coordinates are
     * zero-based, with (0, 0) representing the top-left cell. If the coordinates
     * are out of bounds, an IndexOutOfBoundsException will be thrown.
     * 
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     * @return the cell at the specified coordinates
     */
    public Cell getCell(int x, int y) {
        if (y < 0 || y >= cells.length || x < 0 || x >= cells[y].length) {
            throw new IndexOutOfBoundsException("Cell coordinates out of bounds: (" + x + ", " + y + ")");
        }
        return cells[y][x];
    }

    /**
     * Returns the number of columns in the texture. This is determined by the
     * length of the first row of cells. If the texture has no rows, this will
     * return 0.
     * 
     * @return the number of columns in the texture
     */
    public int getCols() {
        return cells.length > 0 ? cells[0].length : 0;
    }

    /**
     * Returns the number of rows in the texture. This is determined by the length
     * of the cells array.
     * 
     * @return the number of rows in the texture
     */
    public int getRows() {
        return cells.length;
    }

    /**
     * Returns the cell at the specified position. This is a convenience method that
     * allows you to use a Vec2i to specify the coordinates instead of separate x
     * and y values.
     * 
     * @param pos the position of the cell
     * @return the cell at the specified position
     */
    public Cell getCell(Vec2i pos) {
        return getCell(pos.x, pos.y);
    }

    /**
     * Renders the texture onto the given CellScreen at the specified position. The
     * position represents the top-left corner of the texture on the screen. The
     * texture will be drawn row by row, column by column.
     * 
     * @param screen the CellScreen to render the texture onto
     * @param pos    the position of the top-left corner of the texture on the
     *               screen
     */
    public void render(CellScreen screen, Vec2i pos) {
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                screen.setCell(pos.add(new Vec2i(y, x)), cells[y][x].copy());
            }
        }
    }
}
