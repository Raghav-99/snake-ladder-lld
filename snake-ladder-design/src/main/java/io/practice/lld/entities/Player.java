package io.practice.lld.entities;

public class Player {
    private Cell position;
    private final String name;
    public Player(Cell position, String name) {
        modifyPosition(position, null);
        this.name = name;
    }

    public void modifyPosition(Cell position, Cell oldPosition) {
        if(oldPosition != null) oldPosition.decreasePlayerCount();
        this.position = position;
        this.position.increasePlayerCount();
    }

    public Cell getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }
}
