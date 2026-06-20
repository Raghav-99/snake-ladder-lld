package io.practice.lld.entities;


public class Snake extends Obstacle {
    public Snake(Cell start, Cell end) {
        super(start, end);
    }

    @Override
    public Cell doAction(Cell curr) {
        return curr == start ? end : curr;
    }

    @Override
    public void throwIfSpawnInvalid(Cell start, Cell end) {
        if(start.compareTo(end) <= 0) throw new IllegalArgumentException(String.format("Invalid snake coordinates at start:(%s) and end:(%s)!", start, end));
    }
}
