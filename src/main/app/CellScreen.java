package app;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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

    private void setup() { // TODO: make a ui for this or something

        fullscreenOnSecondaryMonitor();

        Rectangle2D bounds = javafx.stage.Screen.getScreens().get(1).getVisualBounds();
        Vec2i screenSize = new Vec2i((int) bounds.getWidth(), (int) bounds.getHeight());

        System.out.println("Screen size: " + screenSize);
        System.out.println(javafx.stage.Screen.getScreens().get(1));

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

    private void fullscreenOnSecondaryMonitor() {

        var screens = javafx.stage.Screen.getScreens();
        Stage stage = getStage();

        if (screens.size() > 1) {
            javafx.stage.Screen tv = screens.get(1);
            Rectangle2D bounds = tv.getVisualBounds();

            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());

            stage.setFullScreen(true);
        } else {
            stage.setFullScreen(true); // fallback
        }
    }

}
