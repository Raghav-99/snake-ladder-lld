package io.practice.lld.entities;

public class Player {
    private Cell position;
    private final String name;
    public Player(Cell position, String name) {
        this.position = position;
        this.name = name;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public Cell getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }
}
