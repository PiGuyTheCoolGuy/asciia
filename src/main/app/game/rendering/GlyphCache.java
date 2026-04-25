package app.game.rendering;

import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class GlyphCache {

    private final BitmapFont font;

    private Image[][][] cache;

    // private final Map<Character, Image> cache = new HashMap<>();

    public GlyphCache(BitmapFont font) {
        this.font = font;
        cache = new Image[font
                .getLength()][Color.values().length][Color.values().length];

        // test one glyph and save it to file
        Image testGlyph = generateGlyph('A', Color.GREEN, Color.BLUE);
        // save to file
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(testGlyph, null), "png", new java.io.File("test_glyph.png"));
            System.out.println("Test glyph saved to test_glyph.png");
        } catch (IOException e) {
            System.out.println("no");
            e.printStackTrace();
        }
        // generateCache();
    }

    public Image getGlyph(char c, Color fg, Color bg) {
        int index = (int) c;
        int fgIndex = fg.ordinal();
        int bgIndex = bg.ordinal();

        if (cache[index][fgIndex][bgIndex] == null) {
            cache[index][fgIndex][bgIndex] = generateGlyph(c, fg, bg);
        }

        return cache[index][fgIndex][bgIndex];
    }

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
