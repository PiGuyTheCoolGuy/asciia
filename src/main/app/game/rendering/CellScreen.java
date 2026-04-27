package app.game.rendering;

import javafx.stage.Stage;
import app.InputHandler;
import app.Screen;
import app.Vec2i;
import javafx.geometry.Rectangle2D;

public class CellScreen extends Screen {

    private Terminal terminal;

    private Cell[][] cells;

    private static final Vec2i CELL_SIZE = new Vec2i(16, 16);

    private final Vec2i size = new Vec2i((javafx.stage.Screen.getScreens().size() > 1)
            ? (int) javafx.stage.Screen.getScreens().get(1).getBounds().getWidth()
            : (int) javafx.stage.Screen.getScreens().get(0).getBounds().getWidth(),
            (javafx.stage.Screen.getScreens().size() > 1)
                    ? (int) javafx.stage.Screen.getScreens().get(1).getBounds().getHeight()
                    : (int) javafx.stage.Screen.getScreens().get(0).getBounds().getHeight());

    public CellScreen(String title, InputHandler input, int displayIndex) {
        super(title,
                javafx.stage.Screen.getScreens().get(displayIndex).getBounds().getWidth(),
                javafx.stage.Screen.getScreens().get(displayIndex).getBounds().getHeight(),
                input);
        setup(displayIndex);
    }

    private void setup(int displayIndex) {

        fullScreen(displayIndex);

        cells = new Cell[size.y / CELL_SIZE.y][size.x / CELL_SIZE.x];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }

        System.out.println("Initialized cell grid: " + cells.length + " rows x " + cells[0].length + " cols");
        System.out.println("Cell size: " + CELL_SIZE.x + "x" + CELL_SIZE.y);
        System.out.println("Screen size: " + size.x + "x" + size.y);

        // try to load font
        try {
            BitmapFont testFont = new BitmapFont(
                    new javafx.scene.image.Image(
                            getClass().getResourceAsStream("/app/assets/fonts/vga2_16x16.png")),
                    16,
                    16);
            terminal = new Terminal(gc(), testFont, cells);
        } catch (Exception e) {
            System.err.println("Failed to load font: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        int rows = size.y / CELL_SIZE.y;
        int cols = size.x / CELL_SIZE.x;

        terminal.render(cols, rows);

        stage.show();
    }

    private void fullScreen(int displayIndex) {

        Stage stage = getStage();
        stage.setFullScreenExitHint("");

        javafx.stage.Screen tv = javafx.stage.Screen.getScreens().get(displayIndex);
        // Rectangle2D bounds = tv.getVisualBounds();
        Rectangle2D bounds = tv.getBounds();

        stage.setFullScreen(false);

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        stage.setMaximized(true);
        stage.setResizable(false);
    }

    // public void switchFullscreenMonitor() {
    // var screens = javafx.stage.Screen.getScreens();
    // Stage stage = getStage();

    // if (screens.size() > 1) {
    // javafx.stage.Screen currentScreen = null;
    // for (javafx.stage.Screen s : screens) {
    // if (s.getBounds().contains(stage.getX(), stage.getY())) {
    // currentScreen = s;
    // break;
    // }
    // }

    // if (currentScreen != null) {
    // javafx.stage.Screen targetScreen = (currentScreen == screens.get(0)) ?
    // screens.get(1) : screens.get(0);
    // Rectangle2D bounds = targetScreen.getVisualBounds();

    // stage.setX(bounds.getMinX());
    // stage.setY(bounds.getMinY());
    // stage.setWidth(bounds.getWidth());
    // stage.setHeight(bounds.getHeight());

    // stage.setFullScreen(true);
    // }
    // } else {
    // stage.setFullScreen(true); // fallback
    // }
    // }

    public void clear() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.clear();
            }
        }
    }

    public Cell setCell(Vec2i pos, Cell cell) {
        if (pos.y < 0 || pos.y >= cells.length || pos.x < 0 || pos.x >= cells[pos.y].length)
            return null;
        Cell old = cells[pos.y][pos.x];
        cells[pos.y][pos.x] = cell;
        return old;
    }

    public Cell setCell(int x, int y, Cell cell) {
        if (y < 0 || y >= cells.length || x < 0 || x >= cells[y].length)
            return null;
        Cell old = cells[y][x];
        cells[y][x] = cell;
        return old;
    }

    public void setString(Vec2i pos, String string) {
        setString(pos, string, Color.WHITE, Color.BLACK);
    }

    public void setString(Vec2i pos, String string, Color fg) {
        setString(pos, string, fg, Color.BLACK);
    }

    public void setString(Vec2i pos, String string, Color fg, Color bg) {
        int x = pos.x;
        int y = pos.y;

        int xMax = cells[0].length;
        int yMax = cells.length;

        for (char i : string.toCharArray()) {
            if (x >= xMax) {
                x = 0;
                y++;
            }
            if (y >= yMax) {
                break; // stop if we exceed the screen
            }
            cells[y][x].character = i;
            cells[y][x].fg = fg;
            cells[y][x].bg = bg;
            x++;
        }
    }

    public void drawTexture(Vec2i pos, Texture texture) {
        for (int y = 0; y < texture.getRows(); y++) {
            for (int x = 0; x < texture.getCols(); x++) {
                Cell cell = texture.getCell(x, y);
                setCell(pos.x + x, pos.y + y, cell);
            }
        }
    }

    public void setString(int x, int y, String string) {
        setString(new Vec2i(x, y), string);
    }

    public void setString(int x, int y, String string, Color fg) {
        setString(new Vec2i(x, y), string, fg);
    }

    public void setString(int x, int y, String string, Color fg, Color bg) {
        setString(new Vec2i(x, y), string, fg, bg);
    }

    public int getCols() {
        return cells[0].length;
    }

    public int getRows() {
        return cells.length;
    }

    // TODO: Set up resizing listener to adjust cell grid when window size changes

}
