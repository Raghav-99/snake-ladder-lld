package io.practice.lld.entities;
import java.util.List;

public class Board {
    private final Cell[][] cells;
    public Board(int rows, int columns) {
        assert (rows > 1 && columns > 1 && rows == columns);
        this.cells = new Cell[rows][columns];
    }

    public void setGameEntities(List<Snake> snakes, List<Ladder> ladders) {
        for (Ladder ladder : ladders) {
            Coordinates leg = ladder.getLeg();
            cells[leg.x()][leg.y()] = new Cell(null, ladder);
        }
        for (Snake snake : snakes) {
            Coordinates head = snake.getHead();
            cells[head.x()][head.y()] = new Cell(snake, null);
        }
    }
}
