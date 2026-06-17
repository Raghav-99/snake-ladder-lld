package io.practice.lld.entities;
import java.util.Arrays;
import java.util.List;

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
        maxLen = rows+1;
    }
    
    public void setGameEntities(List<Snake> snakes, List<Ladder> ladders) {
        for (Ladder ladder : ladders) {
            Cell leg = ladder.getLeg();
            cells[leg.x()][leg.y()] = new Cell(null, ladder);
        }
        for (Snake snake : snakes) {
            Coordinates head = snake.getStart();
            cells[head.x()][head.y()] = new Cell(snake, null);
        }
    }

    public void placeSnakes(List<Snakes)

    public Cell cellAt(int i, int j) {
        if((i+1)*(j+1) <= cells.length) {
            return cells[i][j];
        }
        return null;
    }
}
