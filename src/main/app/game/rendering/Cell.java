package app.game.rendering;

public class Cell {

    public char character = ' ';
    public Color fg = Color.WHITE;
    public Color bg = Color.BLACK;

    public Cell() {
    }

    public Cell(char character) {
        this.character = character;
    }

    public Cell(int character) {
        this.character = (char) character;
    }

    public Cell(char character, Color fg, Color bg) {
        this.character = character;
        this.fg = fg;
        this.bg = bg;
    }

    public void clear() {
        character = ' ';
        fg = Color.WHITE;
        bg = Color.BLACK;
    }

    public Cell copy() {
        return new Cell(character, fg, bg);
    }

}
