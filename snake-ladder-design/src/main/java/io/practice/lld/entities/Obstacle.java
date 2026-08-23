package io.practice.lld.entities;

public abstract class Obstacle {
    protected Obstacle(Cell start, Cell end) {
        throwIfSpawnInvalid(start, end);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    protected abstract void throwIfSpawnInvalid(Cell start, Cell end) throws IllegalArgumentException;
}
