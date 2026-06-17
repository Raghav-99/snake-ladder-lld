package io.practice.lld.entities;

public class Obstacle {
    private final Cell start;
    private final Cell end;
    public Obstacle(Cell start, Cell end) {
        this.start = start;
        this.end = end;
    }

    public Cell getStart() {
        return this.start;
    }

    public Cell getEnd() {
        return this.end;
    }
}
