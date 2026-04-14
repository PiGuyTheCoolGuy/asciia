package app;

public class Texture {
    private Cell[][] cells;

    public Texture(int width, int height) {
        cells = new Cell[height][width];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (y < 0 || y >= cells.length || x < 0 || x >= cells[y].length) {
            throw new IndexOutOfBoundsException("Cell coordinates out of bounds: (" + x + ", " + y + ")");
        }
        return cells[y][x];
    }

    public Texture(String filename) {

    }
}
