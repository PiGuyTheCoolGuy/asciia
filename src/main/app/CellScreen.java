package app;

import javafx.scene.layout.StackPane;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;

public class CellScreen extends Screen {

    // TODO: Instead of a static grid of cells, create camera to render a section

    private Cell[][] cells;

    private static final Vec2i CELL_SIZE = new Vec2i(8, 16);

    public CellScreen(String title, double x, double y, double width, double height) {
        super(title, x, y, width, height);
        setup();
    }

    public CellScreen(String title, double width, double height) {
        super(title, width, height);
        setup();
    }

    public CellScreen(String title) {
        super(title);
        setup();
    }

    private void setup() {
        var screens = javafx.stage.Screen.getScreens();

        System.out.println(screens.size() + " screens");

        for (var screen : screens) {
            Rectangle2D bounds = screen.getBounds();
            System.out
                    .println("Screen: " + bounds.getWidth() + "x" + bounds.getHeight() + " " + screen.toString());
        }

        // super.fullscreen(true);
        show();
        Vec2i screenSize = getSize().copy();
        int rows = screenSize.y / CELL_SIZE.y;
        int cols = screenSize.x / CELL_SIZE.x;
        initCells(rows, cols);
    }

    private void initCells(int rows, int cols) {
        cells = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    @Override
    protected void render(StackPane root) {
        System.out.println("Rendering CellScreen...");
        setContent("PLAYER VIEW..");
        Vec2i screenSize = getSize().copy();
        int rows = screenSize.y / CELL_SIZE.y;
        int cols = screenSize.x / CELL_SIZE.x;

        System.out.println("Screen size: " + screenSize);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // XXX: temp
                cells[i][j].setCharacter('w');

                cells[i][j].render(
                        new Vec2i(j * CELL_SIZE.x - screenSize.x / 2 + CELL_SIZE.x / 2,
                                i * CELL_SIZE.y - screenSize.y / 2 + CELL_SIZE.y / 2),
                        CELL_SIZE, root);
            }
        }
    }

}
