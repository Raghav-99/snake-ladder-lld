package io.practice.lld.entities;

public class Ladder extends Obstacle {
    public Ladder(Cell start, Cell end) {
        super(start, end);
    }

    @Override
    protected void throwIfSpawnInvalid(Cell start, Cell end) throws IllegalArgumentException {
        if(start.compareTo(end) >= 0) throw new IllegalArgumentException(String.format("Invalid ladder coordinates at start:(%s) and end:(%s)!", start, end));
    }
}
