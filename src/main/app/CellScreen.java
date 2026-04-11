package app;

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
        super.fullscreen(true);
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

    public void render() {
        Vec2i screenSize = getSize().copy();
        int rows = screenSize.y / CELL_SIZE.y;
        int cols = screenSize.x / CELL_SIZE.x;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].render(new Vec2i(j * CELL_SIZE.x, i * CELL_SIZE.y), CELL_SIZE, super.root);
            }
        }
    }

}
