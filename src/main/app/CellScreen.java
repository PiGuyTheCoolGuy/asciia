package app;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

public class CellScreen extends Screen {

    // TODO: Instead of a static grid of cells, create camera to render a section

    private Cell[][] cells;

    private static final Vec2i CELL_SIZE = new Vec2i(8, 16);

    private final Vec2i size = new Vec2i((int) javafx.stage.Screen.getScreens().get(1).getVisualBounds().getWidth(),
            (int) javafx.stage.Screen.getScreens().get(1).getVisualBounds().getHeight());

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

        System.out.println("Screen size: " + size);
        System.out.println(javafx.stage.Screen.getScreens().get(1));

        int rows = size.y / CELL_SIZE.y;
        int cols = size.x / CELL_SIZE.x;
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
        int rows = size.y / CELL_SIZE.y;
        int cols = size.x / CELL_SIZE.x;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                /*
                 * if ((i + j) % 2 == 0) {
                 * cells[i][j].setbColor(javafx.scene.paint.Color.BLACK);
                 * cells[i][j].setfColor(javafx.scene.paint.Color.WHITE);
                 * } else {
                 * cells[i][j].setbColor(javafx.scene.paint.Color.WHITE);
                 * cells[i][j].setfColor(javafx.scene.paint.Color.BLACK);
                 * }
                 */

                cells[i][j].render(new Vec2i(j * CELL_SIZE.x - size.x / 2 + 5, i * CELL_SIZE.y - size.y / 2 - 6),
                        CELL_SIZE,
                        root);
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
