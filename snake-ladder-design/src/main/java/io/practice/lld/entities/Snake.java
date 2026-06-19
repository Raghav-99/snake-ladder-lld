package io.practice.lld.entities;


public class Snake extends Obstacle {
    public Snake(Cell start, Cell end) {
        super(start, end);
    }

    @Override
    public Cell doAction(Cell curr) {
        return curr == start ? end : curr;
    }
}
