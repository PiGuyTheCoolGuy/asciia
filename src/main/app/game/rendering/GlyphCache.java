package app.game.rendering;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * A cache for rendered glyphs. It generates an image for each unique
 * combination of character, foreground color, and background color.
 */
public class GlyphCache {

    private final BitmapFont font;

    private Image[][][] cache;

    /**
     * Creates a new GlyphCache for the given font. The cache will store images for
     * each character in the font, for all combinations of foreground and background
     * colors.
     * 
     * @param font the bitmap font to use for rendering glyphs
     */
    public GlyphCache(BitmapFont font) {
        this.font = font;
        cache = new Image[font
                .getLength()][Color.values().length][Color.values().length];
    }

    /**
     * Returns the cached image for the given character and colors. If the image
     * does not exist in the cache, it will be generated and stored before being
     * returned.
     * 
     * @param c  the character to get the glyph for
     * @param fg the foreground color
     * @param bg the background color
     * @return the cached image for the given character and colors
     */
    public Image getGlyph(char c, Color fg, Color bg) {
        int index = (int) c;
        int fgIndex = fg.ordinal();
        int bgIndex = bg.ordinal();

        if (cache[index][fgIndex][bgIndex] == null) {
            cache[index][fgIndex][bgIndex] = generateGlyph(c, fg, bg);
        }

        return cache[index][fgIndex][bgIndex];
    }

    /**
     * Generates an image for the given character and colors. The image is created
     * by
     * drawing the glyph from the font onto a canvas, applying the foreground color
     * using a multiply blend mode, and filling the background with the background
     * color.
     * 
     * @param c  the character to generate the glyph for
     * @param fg the foreground color
     * @param bg the background color
     * @return the generated image for the given character and colors
     */
    private Image generateGlyph(char c, Color fg, Color bg) {
        int width = font.getGlyphWidth();
        int height = font.getGlyphHeight();

        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // 1. background
        gc.setFill(Color.getJFXColor(bg));
        gc.fillRect(0, 0, width, height);

        // 2. draw glyph (white from atlas)
        font.drawRaw(gc, c, 0, 0);

        // 3. APPLY foreground color using multiply
        gc.setGlobalBlendMode(javafx.scene.effect.BlendMode.MULTIPLY);
        gc.setFill(Color.getJFXColor(fg));
        gc.fillRect(0, 0, width, height);

        // reset blend mode
        gc.setGlobalBlendMode(javafx.scene.effect.BlendMode.SRC_OVER);

        WritableImage image = new WritableImage(width, height);
        canvas.snapshot(null, image);
        return image;
    }

}
