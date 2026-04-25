package app.game.rendering;

public class Cell {

    public char character = ' ';
    public Color fg = Color.WHITE;
    public Color bg = Color.BLACK;

    Cell() {
    }

    Cell(char character) {
        this.character = character;
    }

    Cell(char character, Color fg, Color bg) {
        this.character = character;
        this.fg = fg;
        this.bg = bg;
    }

    public void clear() {
        character = ' ';
        fg = Color.WHITE;
        bg = Color.BLACK;
    }

}
