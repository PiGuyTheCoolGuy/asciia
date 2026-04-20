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

    public Texture(String filename) { // TODO: Handle exceptions properly
        java.io.File file;
        // load from /app/assets/textures/filename
        try {
            java.net.URL url = getClass().getResource("/app/assets/textures/" + filename);
            if (url == null) {
                throw new RuntimeException("Texture file not found: " + filename);
            }
            file = new java.io.File(url.toURI());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load texture: " + filename, e);
        }

        // file will be a grid of 2 digit hex values representing ASCII codes, separated
        // by spaces, one row per line
        try (java.util.Scanner scanner = new java.util.Scanner(file)) {
            java.util.List<Cell[]> rows = new java.util.ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(" ");
                Cell[] row = new Cell[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    int asciiCode = Integer.parseInt(tokens[i], 16);
                    row[i] = new Cell((char) asciiCode); // default white color
                }
                rows.add(row);
            }
            cells = rows.toArray(new Cell[0][]);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse texture file: " + filename, e);
        }
    }

    public int getCols() {
        return cells[0].length;
    }

    public int getRows() {
        return cells.length;
    }

    public Cell getCell(Vec2i pos) {
        return getCell(pos.x, pos.y);
    }
}
