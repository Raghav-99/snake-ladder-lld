package io.practice.lld.entities;

public class Player {
    private Coordinates position;
    private final String name;
    private boolean hasWon = false;
    public Player(Coordinates position, String name) {
        this.position = position;
        this.name = name;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public Coordinates getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    public void setHasWon(boolean val) {
        hasWon = true;
    }

    public boolean getHasWon() {
        return this.hasWon;
    }
}
