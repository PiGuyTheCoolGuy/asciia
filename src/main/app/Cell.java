package app;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {

    private char character = '#'; // default
    private Color fColor = Color.WHITE;
    private Color bColor = Color.BLACK;

    private Rectangle background;
    private Label characterLabel;

    private boolean addedToRoot = false;

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

        characterLabel = new Label();
        characterLabel.setAlignment(Pos.CENTER);
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    // --- Rendering ---

    // Position is in PIXELS, not grid coords
    public void render(Vec2i position, Vec2i cellSize, StackPane root) {

        // Add to scene ONLY ONCE
        if (!addedToRoot) {
            root.getChildren().addAll(background, characterLabel);
            addedToRoot = true;
        }

        // --- Background ---
        background.setWidth(cellSize.x);
        background.setHeight(cellSize.y);
        background.setFill(bColor);
        background.setLayoutX(position.x);
        background.setLayoutY(position.y);

        // --- Character ---
        characterLabel.setText(String.valueOf(character));
        characterLabel.setTextFill(fColor);

        // Match cell size
        characterLabel.setMinSize(cellSize.x, cellSize.y);
        characterLabel.setPrefSize(cellSize.x, cellSize.y);

        // Position
        characterLabel.setLayoutX(position.x);
        characterLabel.setLayoutY(position.y);

        // Scale font to actually fit the cell
        double fontSize = cellSize.y * 0.8;

        characterLabel.setStyle(
                "-fx-font-family: 'Consolas'; " +
                        "-fx-font-size: " + fontSize + "px;");
    }

    public void setfColor(Color fColor) {
        this.fColor = fColor;
    }

    public void setbColor(Color bColor) {
        this.bColor = bColor;
    }
}