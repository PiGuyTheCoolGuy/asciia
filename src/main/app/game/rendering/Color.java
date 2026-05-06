package app.game.rendering;

/**
 * An enumeration representing a set of colors that can be used for rendering in
 * the game. Each color can be converted to a corresponding JavaFX color for use
 * in rendering operations.
 */
public enum Color {
    BLACK,
    RED,
    GREEN,
    YELLOW,
    BLUE,
    MAGENTA,
    CYAN,
    WHITE,
    BRIGHT_BLACK,
    BRIGHT_RED,
    BRIGHT_GREEN,
    BRIGHT_YELLOW,
    BRIGHT_BLUE,
    BRIGHT_MAGENTA,
    BRIGHT_CYAN,
    BRIGHT_WHITE;

    /**
     * Converts the given Color enum value to its corresponding JavaFX color.
     * 
     * @param color The Color enum value to convert.
     * @return The corresponding JavaFX color.
     */
    public static javafx.scene.paint.Color getJFXColor(Color color) {
        switch (color) {
            case BLACK:
                return javafx.scene.paint.Color.BLACK;
            case RED:
                return javafx.scene.paint.Color.RED;
            case GREEN:
                return javafx.scene.paint.Color.GREEN;
            case YELLOW:
                return javafx.scene.paint.Color.YELLOW;
            case BLUE:
                return javafx.scene.paint.Color.BLUE;
            case MAGENTA:
                return javafx.scene.paint.Color.MAGENTA;
            case CYAN:
                return javafx.scene.paint.Color.CYAN;
            case WHITE:
                return javafx.scene.paint.Color.WHITE;
            case BRIGHT_BLACK:
                return javafx.scene.paint.Color.GRAY;
            case BRIGHT_RED:
                return javafx.scene.paint.Color.ORANGE;
            case BRIGHT_GREEN:
                return javafx.scene.paint.Color.LIME;
            case BRIGHT_YELLOW:
                return javafx.scene.paint.Color.GOLD;
            case BRIGHT_BLUE:
                return javafx.scene.paint.Color.DEEPSKYBLUE;
            case BRIGHT_MAGENTA:
                return javafx.scene.paint.Color.HOTPINK;
            case BRIGHT_CYAN:
                return javafx.scene.paint.Color.AQUA;
            case BRIGHT_WHITE:
                return javafx.scene.paint.Color.WHITE.brighter();
            default:
                throw new IllegalArgumentException("Unknown color: " + color);
        }
    }
}