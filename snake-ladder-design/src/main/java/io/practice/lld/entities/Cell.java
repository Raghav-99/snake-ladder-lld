package io.practice.lld.entities;

public class Cell {
    private final Snake snake;
    private final Ladder ladder;
    public Cell(Snake snake, Ladder ladder)  {
        assert (snake == null || ladder == null);
        this.snake = snake;
        this.ladder = ladder;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Ladder getLadder() {
        return this.ladder;
    }
}
