package io.practice.lld.entities;

public class Board {
    private final Cell[][] cells;
    public final Cell firstCell;
    public final Cell lastCell;
    public final int maxLen;
    public Board(int rows, int columns) {
        assert (rows > 1 && columns > 1 && rows == columns);
        cells = new Cell[rows][columns];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        firstCell = cells[0][0];
        lastCell = cells[rows-1][columns-1];
        maxLen = rows * columns;
    }
    
    public Cell cellAt(int i, int j) {
        if(i >= 0 && i < cells.length && j >= 0 && j < cells[i].length) {
            return cells[i][j];
        }
        return null;
    }
}
