package app;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

public class CellScreen extends Screen {

    private Terminal terminal;

    private Cell[][] cells;

    private static final Vec2i CELL_SIZE = new Vec2i(16, 16);

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

    private void setup() {

        fullscreenOnSecondaryMonitor();

        System.out.println("Screen size: " + size);
        System.out.println(javafx.stage.Screen.getScreens().get(1));

        cells = new Cell[size.y / CELL_SIZE.y][size.x / CELL_SIZE.x];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }

        BitmapFont font = new BitmapFont(
                new javafx.scene.image.Image(
                        getClass().getResourceAsStream("~/asciia/src/main/app/assets/fonts/vga2_16x16.png")),
                16,
                16);
        terminal = new Terminal(gc(), font, cells);
    }

    @Override
    protected void render(GraphicsContext gc) {
        int rows = size.y / CELL_SIZE.y;
        int cols = size.x / CELL_SIZE.x;

        /*
         * for (int i = 0; i < rows; i++) {
         * for (int j = 0; j < cols; j++) {
         * if ((i + j) % 2 == 0) {
         * cells[i][j].setbColor(javafx.scene.paint.Color.RED);
         * cells[i][j].setfColor(javafx.scene.paint.Color.GREEN);
         * } else {
         * cells[i][j].setbColor(javafx.scene.paint.Color.YELLOW);
         * cells[i][j].setfColor(javafx.scene.paint.Color.BLUE);
         * }
         * 
         * }
         * }
         */
        terminal.render(cols, rows);
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
