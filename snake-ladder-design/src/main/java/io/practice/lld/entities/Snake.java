package io.practice.lld.entities;


public class Snake {
    private final Coordinates head;
    private final Coordinates tail;
    public Snake(Coordinates coordinates, Coordinates tail) {
        this.head = coordinates;
        this.tail = tail;
    }

    public Coordinates getHead() {
        return this.head;
    }

    public Coordinates getTail() {
        return this.tail;
    }
}
