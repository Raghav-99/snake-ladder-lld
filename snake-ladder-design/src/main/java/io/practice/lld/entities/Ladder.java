package io.practice.lld.entities;

public class Ladder {
    private final Coordinates leg;
    private final Coordinates head;

    public Ladder(Coordinates leg, Coordinates head) {
        this.leg = leg;
        this.head = head;
    }

    public Coordinates getLeg() {
        return this.leg;
    }

    public Coordinates getHead() {
        return this.head;
    }
}
