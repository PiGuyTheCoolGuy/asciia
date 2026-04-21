package app.game.rendering;

import javafx.scene.paint.Color;

public class Cell {

    public char character = ' ';
    public Color fColor = Color.WHITE;
    public Color bColor = Color.BLACK;

    Cell() {
    }

    Cell(char character) {
        this.character = character;
    }

    Cell(char character, Color fColor, Color bColor) {
        this.character = character;
        this.fColor = fColor;
        this.bColor = bColor;
    }

}
