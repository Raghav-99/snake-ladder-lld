package io.practice.lld.entities;

public class Ladder extends Obstacle {
    public Ladder(Cell start, Cell end) {
        super(start, end);
    }

    @Override
    public Cell doAction(Cell curr) {
        return curr == start ? end : curr;
    }
}
