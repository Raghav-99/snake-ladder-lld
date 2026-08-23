package io.practice.lld.service;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Obstacle;

public class TurnData {
    private final Cell oldPos;
    private final Cell newPos;
    private final Cell mutatedPos;
    private final Obstacle obstacle;
    public Cell getOldPos() {
        return oldPos;
    }
    public Cell getNewPos() {
        return newPos;
    }
    public Cell getMutatedPos() {
        return mutatedPos;
    }
    public Obstacle getObstacle() {
        return obstacle;
    }
    public TurnData(Cell oldPos, Cell newPos, Cell mutatedPos, Obstacle obstacle) {
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.mutatedPos = mutatedPos;
        this.obstacle = obstacle;
    }

    @Override
    public String toString() {
        String beforeDetail = String.format("before roll: (%s) ",oldPos);
        String currentDetail = String.format("after roll: (%s), obstacle: (%s), mutated position: (%s)", newPos, obstacle, mutatedPos);
        return beforeDetail.concat(currentDetail);
    }
}
