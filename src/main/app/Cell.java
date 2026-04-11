package app;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {

    private char character = '#'; // XXX: temp
    private Color fColor = Color.WHITE;
    private Color bColor = Color.BLACK;

    private Rectangle background;
    private Label characterLabel;

    public Cell() {
        setup();
    }

    public Cell(char character, Color fColor, Color bColor) {
        this.character = character;
        this.fColor = fColor;
        this.bColor = bColor;
        setup();
    }

    public Cell(char character) {
        this.character = character;
        setup();
    }

    private void setup() {
        background = new Rectangle();

        characterLabel = new Label(String.valueOf(character));
    }

    public char getCharacter() {
        return character;
    }

    public char setCharacter(char character) {
        this.character = character;

        return character;
    }

    public Color getfColor() {
        return fColor;
    }

    public Color setfColor(Color fColor) {
        this.fColor = fColor;

        return fColor;
    }

    public Color getbColor() {
        return bColor;
    }

    public Color setbColor(Color bColor) {
        this.bColor = bColor;

        return bColor;
    }

    // Position here is in pixel coordinates, not cell coordinates
    public void render(Vec2i position, Vec2i cellSize, StackPane root) {
        background.setWidth(cellSize.x);
        background.setHeight(cellSize.y);
        background.setFill(bColor);
        background.setTranslateX(position.x);
        background.setTranslateY(position.y);

        characterLabel.setText(String.valueOf(character));
        characterLabel.setTextFill(fColor);
        characterLabel.setTranslateX(position.x);
        characterLabel.setTranslateY(position.y);
        characterLabel.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: " + cellSize.y * 0.7 + "px;");

        root.getChildren().addAll(background, characterLabel);
    }

}
