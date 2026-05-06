package app.game.rendering;

/**
 * Represents a single cell in the game's rendering grid. Each cell contains a
 * character, a foreground color, and a background color.
 */
public class Cell {

    public char character = ' ';
    public Color fg = Color.WHITE;
    public Color bg = Color.BLACK;

    /**
     * Creates a new Cell with default values.
     * 
     * <pre>
     * character = ' '
     * fg = Color.WHITE
     * bg = Color.BLACK
     * </pre>
     */
    public Cell() {
    }

    /**
     * Creates a new Cell with the specified character and default colors.
     * 
     * <pre>
     * fg = Color.WHITE
     * bg = Color.BLACK
     * </pre>
     * 
     * @param character Character to display
     */
    public Cell(char character) {
        this.character = character;
    }

    /**
     * Creates a new Cell with the specified character (as an integer) and default
     * colors.
     * 
     * <pre>
     * fg = Color.WHITE
     * bg = Color.BLACK
     * </pre>
     * 
     * @param character Character to display (as an integer, will be cast to char)
     */
    public Cell(int character) {
        this.character = (char) character;
    }

    /**
     * Creates a new Cell with the specified character and colors.
     * 
     * @param character Character to display
     * @param fg        Foreground color
     * @param bg        Background color
     */
    public Cell(char character, Color fg, Color bg) {
        this.character = character;
        this.fg = fg;
        this.bg = bg;
    }

    /**
     * Clears the cell, resetting it to default values.
     * 
     * <pre>
     * character = ' '
     * fg = Color.WHITE
     * bg = Color.BLACK
     * </pre>
     */
    public void clear() {
        character = ' ';
        fg = Color.WHITE;
        bg = Color.BLACK;
    }

    /**
     * Creates a copy of the cell.
     * 
     * @return A new Cell with the same character and colors
     */
    public Cell copy() {
        return new Cell(character, fg, bg);
    }

}
