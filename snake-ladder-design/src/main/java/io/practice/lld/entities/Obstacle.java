package io.practice.lld.entities;

public abstract class Obstacle {
    protected final Cell start;
    protected final Cell end;
    protected Obstacle(Cell start, Cell end) {
        this.start = start;
        this.end = end;
    }

    // public Cell getStart() {
    //     return this.start;
    // }

    // public Cell getEnd() {
    //     return this.end;
    // }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public abstract Cell doAction(Cell curr);
}
